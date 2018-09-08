package org.cyberbiology_old;

import org.cyberbiology_old.prototype.IWindow;
import org.cyberbiology_old.prototype.IWorld;

public class World implements IWorld
{
	public static final int BOT_WIDTH = 4;
	public static final int BOT_HEIGHT = 4;

	public int width;
	public int height;

	public Bot[][] matrix; // Матрица мира
	public int generation;
	public int population;
	public int organic;

	public World(IWindow win, int width, int height)
	{
        this.population = 0;
        // TODO мне кжется это итерация, а не поколение. Поколение увеличивается после рождения нового бота
        this.generation = 0;
        this.organic = 0;
		this.setSize(width, height);
	}

	public int getGeneration() {
		return  this.generation;
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

	void makeStep() {
		if (this.isMatrixEmpty()) {
			this.generateAdam();
		}

		// обновляем матрицу
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				if (matrix[x][y] != null)
				{
					// if (matrix[x][y].alive == 3)
					{
//						System.out.println(getClass() + " - Process bot [" + x + "][" + y + "]");

						matrix[x][y].step(); // выполняем шаг бота
					}
				}
			}
		}
		generation = generation + 1;
	}

	private void generateAdam()
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

	private boolean isMatrixEmpty() {
		for (Bot[] matrixLine : this.matrix) {
			for (Bot bot : matrixLine) {
				if (bot != null) {
					return false;
				}
			}
		}

		return true;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public Bot[][] getWorldArray()
	{
		return  this.matrix;
	}
}
