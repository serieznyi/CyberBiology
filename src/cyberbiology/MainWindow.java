package cyberbiology;

import cyberbiology.prototype.IWindow;
import cyberbiology.prototype.view.IRenderer;
import cyberbiology.ui.PropertyDialog;
import cyberbiology.util.ProjectProperties;
import cyberbiology.view.BasicRenderer;
import cyberbiology.view.MultiCellRenderer;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class MainWindow extends JFrame implements IWindow
{
    private static final int BOT_WIDTH = 4;
    private static final int BOT_HEIGHT = 4;

    private static MainWindow window;

    private static World world;

    private JMenuItem runItem;
    private JMenuItem recordItem;
    private JMenuItem snapShotItem;

    private final JLabel generationLabel = new JLabel(" Generation: 0 ");
    private final JLabel populationLabel = new JLabel(" Population: 0 ");
    private final JLabel organicLabel = new JLabel(" Organic: 0 ");
    private final JLabel recorderBufferLabel = new JLabel("");
    private final JLabel memoryLabel = new JLabel("");
    private final JLabel frameSavedCounterLabel = new JLabel("");
    private final JLabel frameSkipSizeLabel = new JLabel("");

    private PropertyDialog propertyDialog;

    private JPanel paintPanel = new JPanel()
    {
        public void paint(Graphics g)
        {
            g.drawImage(buffer, 0, 0, null);
        };
    };

    private Image buffer;

    private IRenderer renderer;

    final private IRenderer[] availableRenderers = new IRenderer[]
        {
            new BasicRenderer(),
            new MultiCellRenderer()
        };

    private ProjectProperties properties;

    public MainWindow()
    {
    	window	= this;
		properties	= new ProjectProperties("properties.xml");

		this.propertyDialog = new PropertyDialog(properties, window);

        setTitle("CyberBiologyTest 1.0.0");

        this.setSize(new Dimension(1800, 900));

        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize(), fSize = getSize();

        if (fSize.height > sSize.height) { fSize.height = sSize.height; }

        if (fSize.width  > sSize.width)  { fSize.width = sSize.width; }

        this.setSize(new Dimension(sSize.width, sSize.height));
        
        
        setDefaultCloseOperation (WindowConstants.EXIT_ON_CLOSE);

        Container container = getContentPane();

        container.setLayout(new BorderLayout());// у этого лейаута приятная особенность - центральная часть растягивается автоматически

        container.add(paintPanel, BorderLayout.CENTER);// добавляем нашу карту в центр

        JPanel statusPanel = new JPanel(new FlowLayout());
        statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        container.add(statusPanel, BorderLayout.SOUTH);
        
        generationLabel.setPreferredSize(new Dimension(140, 18));
        generationLabel.setBorder(BorderFactory.createLoweredBevelBorder());
        statusPanel.add(generationLabel);
        
        populationLabel.setPreferredSize(new Dimension(140, 18));
        populationLabel.setBorder(BorderFactory.createLoweredBevelBorder());
        statusPanel.add(populationLabel);
        
        organicLabel.setPreferredSize(new Dimension(140, 18));
        organicLabel.setBorder(BorderFactory.createLoweredBevelBorder());
        statusPanel.add(organicLabel);

        memoryLabel.setPreferredSize(new Dimension(140, 18));
        memoryLabel.setBorder(BorderFactory.createLoweredBevelBorder());
        statusPanel.add(memoryLabel);
        
        recorderBufferLabel.setPreferredSize(new Dimension(140, 18));
        recorderBufferLabel.setBorder(BorderFactory.createLoweredBevelBorder());
        statusPanel.add(recorderBufferLabel);
        
        frameSavedCounterLabel.setPreferredSize(new Dimension(140, 18));
        frameSavedCounterLabel.setBorder(BorderFactory.createLoweredBevelBorder());
        statusPanel.add(frameSavedCounterLabel);
        
        frameSkipSizeLabel.setPreferredSize(new Dimension(140, 18));
        frameSkipSizeLabel.setBorder(BorderFactory.createLoweredBevelBorder());
        statusPanel.add(frameSkipSizeLabel);
        
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
         
        runItem = new JMenuItem("Запустить");
        fileMenu.add(runItem);
        runItem.addActionListener(e -> {
            if(world==null) {
                int width = paintPanel.getWidth() / BOT_WIDTH;
                int height = paintPanel.getHeight() / BOT_HEIGHT;
                world = new World(window, width, height);
                world.generateAdam();
                paint();
            }

            if(!world.isStarted()) {
                world.start();//Запускаем его
                runItem.setText("Пауза");

            } else {
                world.stop();
                runItem.setText("Продолжить");
                snapShotItem.setEnabled(true);
            }

        });
        snapShotItem = new JMenuItem("Сделать снимок");
        fileMenu.add(snapShotItem);
        snapShotItem.setEnabled(false);
        snapShotItem.addActionListener(e -> {
            if(world==null)
            {
                int width = paintPanel.getWidth() / BOT_WIDTH;// Ширина доступной части экрана для рисования карты
                int height = paintPanel.getHeight() / BOT_HEIGHT;// Боты 4 пикселя?
                world = new World(window,width,height);
                world.generateAdam();
                paint();
            }
            world.stop();
            runItem.setText("Продолжить");
            world.makeSnapShot();
        });
        
        recordItem = new JMenuItem("Начать запись");
        fileMenu.add(recordItem);
        
        recordItem.addActionListener(e -> {
            if(world==null)
            {
                int width = paintPanel.getWidth() / BOT_WIDTH;// Ширина доступной части экрана для рисования карты
                int height = paintPanel.getHeight() / BOT_HEIGHT;// Боты 4 пикселя?
                world = new World(window,width,height);
                world.generateAdam();
                paint();
            }
            if(!world.isRecording())
            {
                world.startRecording();
                recordItem.setText("Сохранить запись");
            }else
            {
                recordItem.setText("Начать запись");

                world.stopRecording();
                if(world.haveRecord())
                {
                    //saveItem.setEnabled(true);
                    //deleteItem.setEnabled(true);
                    //recordItem.setEnabled(false);
                }
            }
        });

        fileMenu.addSeparator();
        
        JMenuItem optionItem = new JMenuItem("Настройки");
        fileMenu.add(optionItem);
        optionItem.addActionListener(e -> this.propertyDialog.show());

        fileMenu.addSeparator();
         
        JMenuItem exitItem = new JMenuItem("Выйти");
        fileMenu.add(exitItem);
         
        exitItem.addActionListener(e -> {
            // Попытка корректно заверишть запись, если она велась
            // TODO: Не тестировалось!
            if(world!=null && world.isRecording())
            {
                world.stopRecording();
                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            System.exit(0);
        });
         
        menuBar.add(fileMenu);

        JMenu ViewMenu = new JMenu("Вид");
        menuBar.add(ViewMenu);
        
        JMenuItem item;

        for (IRenderer renderer1 : availableRenderers) {
            item = new JMenuItem(renderer1.getName());
            ViewMenu.add(item);
            item.addActionListener(new ViewMenuActionListener(this, renderer1));
        }
        
        this.setJMenuBar(menuBar);
        
        this.renderer = new BasicRenderer();
        this.pack();
        this.setVisible(true);
        this.setExtendedState(MAXIMIZED_BOTH);
        
        String tmp = this.properties.getFileDirectory();
        if(tmp==null || tmp.length()==0) {
            this.propertyDialog.show();
        }
    }

	public void setRenderer(IRenderer view)
	{
		this.renderer = view;
	}

    public void paint()
    {
    	buffer = this.renderer.render(world, this.paintPanel);
        generationLabel.setText(" Generation: " + String.valueOf(world.generation));
        populationLabel.setText(" Population: " + String.valueOf(world.population));
        organicLabel.setText(" Organic: " + String.valueOf(world.organic));
        recorderBufferLabel.setText(" Buffer: " + String.valueOf(world.recorder.getBufferSize()));
        
        Runtime runtime = Runtime.getRuntime();
        long memory = runtime.totalMemory() - runtime.freeMemory();
        this.memoryLabel.setText(" Memory MB: " + String.valueOf(memory/(1024L * 1024L)));
        
        this.frameSavedCounterLabel.setText(" Saved frames: " + String.valueOf(world.recorder.getFrameSavedCounter()));
        this.frameSkipSizeLabel.setText(" Skip frames: " + String.valueOf(world.recorder.getFrameSkipSize()));
        this.paintPanel.repaint();
    }

    public static void main(String[] args)
    {
    	MainWindow.window = new MainWindow();
    }

    public ProjectProperties getProperties()
    {
    	return this.properties;
    }
}