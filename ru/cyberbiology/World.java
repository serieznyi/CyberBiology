package cyberbiology;

import cyberbiology.record.v0.RecordManager;
import cyberbiology.prototype.IWindow;
import cyberbiology.prototype.IWorld;
import cyberbiology.prototype.record.IRecordManager;
import cyberbiology.util.ProjectProperties;

public class World implements IWorld
{
	public World world;
	public IWindow window;
	IRecordManager recorder;
	public static final int BOTW = 4;
	public static final int BOTH = 4;
	public int width;
	public int height;
	public Bot[][] matrix; // Матрица мира
	public int generation;
	public int population;
	public int organic;
	boolean started;
	Worker thread;

	public World(IWindow win)
	{
		window = win;
		population = 0;
		generation = 0;
		organic = 0;
		recorder = new RecordManager(this);
	}

	public World(IWindow win, int width, int height)
	{
		this(win);
		this.setSize(width, height);
	}

	public void setSize(int width, int height)
	{
		this.width = width;
		this.height = height;
		this.matrix = new Bot[width][height];
	}

	public void addBot(Bot bot)
	{
		this.matrix[bot.x][bot.y] = bot;
	}

	public void paint()
	{
		window.paint();
	}

	public ProjectProperties getProperties()
	{
		return window.getProperties();
	}

	class Worker extends Thread
	{
		public void run()
		{
			started = true;// Флаг работы потока, если установить в false поток
							// заканчивает работу
			while (started)
			{

				boolean rec = recorder.isRecording(); // запоминаем флаг
														// "записывать" на
														// полную итерацию кадра
				if (rec)// вызываем обработчика "старт кадра"
					recorder.startFrame();
				// обновляем матрицу
				for (int y = 0; y < height; y++)
				{
					for (int x = 0; x < width; x++)
					{
						if (matrix[x][y] != null)
						{
							// if (matrix[x][y].alive == 3)
							{
								matrix[x][y].step(); // выполняем шаг бота
								if (rec)
								{
									// вызываем обработчика записи бота
									recorder.writeBot(matrix[x][y], x, y);
								}
							}
						}
					}
				}
				if (rec)// вызываем обработчика "конец кадра"
					recorder.stopFrame();
				generation = generation + 1;
				if (generation % 10 == 0)
				{ // отрисовка на экран через каждые ... шагов
					paint(); // отображаем текущее состояние симуляции на экран
				}
				// sleep(); // пауза между ходами, если надо уменьшить скорость
			}

			paint();// если запаузили рисуем актуальную картинку

			started = false;// Закончили работу
		}
	}

	public void generateAdam()
	{
		// ========== 1 ==============
		// бот номер 1 - это уже реальный бот
		Bot bot = new Bot(this);

		bot.adr = 0;
		bot.x = width / 2; // координаты бота
		bot.y = height / 2;
		bot.health = 990; // энергия
		bot.mineral = 0; // минералы
		bot.alive = 3; // отмечаем, что бот живой
		bot.c_red = 170; // задаем цвет бота
		bot.c_blue = 170;
		bot.c_green = 170;
		bot.direction = 5; // направление
		bot.mprev = null; // бот не входит в многоклеточные цепочки, поэтому
							// ссылки
		bot.mnext = null; // на предыдущего, следующего в многоклеточной цепочке
							// пусты
		for (int i = 0; i < 64; i++)
		{ // заполняем геном командой 25 - фотосинтез
			bot.mind[i] = 25;
		}

		matrix[bot.x][bot.y] = bot; // даём ссылку на бота в массиве world[]

		return;
	}

	public void restoreLinks()
	{
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				if (matrix[x][y] != null)
				{
					if (matrix[x][y].alive == 3)
					{
						Bot bot = matrix[x][y];
						if(bot.mprevX>-1 && bot.mprevY>-1)
						{
							bot.mprev	= matrix[bot.mprevX][bot.mprevY];
						}
						if(bot.mnextX>-1 && bot.mnextY>-1)
						{
							bot.mnext	= matrix[bot.mnextX][bot.mnextY];
						}
					}
				}
			}
		}
	}

	public boolean isStarted()
	{
		return this.thread != null;
	}

	public void start()
	{
		if (!this.isStarted())
		{
			this.thread = new Worker();
			this.thread.start();
		}
	}

	public void stop()
	{
		started = false;
		this.thread = null;
	}

	public boolean isRecording()
	{
		return this.recorder.isRecording();
	}

	public void startRecording()
	{
		this.recorder.startRecording();
	}

	public boolean stopRecording()
	{
		return this.recorder.stopRecording();
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public boolean haveRecord()
	{
		return this.recorder.haveRecord();
	}

	public void makeSnapShot()
	{
		this.recorder.makeSnapShot();
	}

	public Bot[][] getWorldArray()
	{
		return  this.matrix;
	}
}
