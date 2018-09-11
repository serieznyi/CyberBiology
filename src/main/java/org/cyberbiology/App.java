package org.cyberbiology;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.cyberbiology.render.MultiCellRenderer;
import org.cyberbiology.snapshot.SnapShotManager;
import org.cyberbiology.util.ProjectProperties;
import org.cyberbiology.controller.MainController;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.cyberbiology.domain.Size;
import org.cyberbiology.prototype.view.IRenderer;
import org.cyberbiology.prototype.IWorld;
import org.cyberbiology.render.BasicRenderer;

public class App extends Application {
    private static final int BOT_WIDTH = 4;
    private static final int BOT_HEIGHT = 4;
    private static final int MIN_WINDOW_HEIGHT = 600;
    private static final int MIN_WINDOW_WIDTH = 900;
    private static App self;
    private Stage primaryStage;
    private Stage settingsDialogStage;
    private ProjectProperties properties;
    private World world;
    private IRenderer currentRenderer = new BasicRenderer();
    private FXMLLoader mainFXMLLoader;
    private FXMLLoader settingsDialogFXMLLoader;
    private SnapShotManager snapShotManager;
    private Thread physicsThread;
    private AnimationTimer renderLoop;
    public static final IRenderer[] AVAILABLE_RENDERERS = new IRenderer[]
            {
                    new BasicRenderer(),
                    new MultiCellRenderer()
            };
    private ResourceBundle resourceBundle;

    @Override
    public void start(Stage primaryStage) throws Exception {
        App.self = this;

        this.primaryStage = primaryStage;

        this.resourceBundle = ResourceBundle.getBundle("org.cyberbiology.App", new UTF8Control());

        this.mainFXMLLoader = new FXMLLoader(getClass().getResource("main.fxml"), this.resourceBundle);
        this.settingsDialogFXMLLoader = new FXMLLoader(getClass().getResource("settings_dialog.fxml"), this.resourceBundle);

        this.properties	= new ProjectProperties("properties.xml");

        this.properties.load();

        this.createSettingsDialogStage();

        this.configurePrimaryStage();

        this.snapShotManager = new SnapShotManager(properties.getOutputDirectory());

        MainController mainController = this.mainFXMLLoader.getController();

        Size mainPaneSize = mainController.getMainPaneSize();

        this.world = new World(
                (int) mainPaneSize.getWidth() / BOT_WIDTH,
                (int) mainPaneSize.getHeight() / BOT_HEIGHT
        );

        mainController.initEventListeners();

        this.primaryStage.show();
    }

    public IWorld getWorld() {
        return this.world;
    }

    public static App getSelf() {
        return App.self;
    }

    private void configurePrimaryStage() throws IOException {
        Parent root = this.mainFXMLLoader.load();

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        this.primaryStage.setX(bounds.getMinX());
        this.primaryStage.setY(bounds.getMinY());
        this.primaryStage.setWidth(bounds.getWidth());
        this.primaryStage.setHeight(bounds.getHeight());
        this.primaryStage.setMinWidth(MIN_WINDOW_WIDTH);
        this.primaryStage.setMinHeight(MIN_WINDOW_HEIGHT);
        this.primaryStage.setFullScreen(true);
        this.primaryStage.setTitle("CyberBiologyTest 1.0.0");
        this.primaryStage.setScene(new Scene(root));
        this.primaryStage.show();
    }

    private void createSettingsDialogStage() throws IOException {
        this.settingsDialogStage = new Stage();
        this.settingsDialogStage.initOwner(this.primaryStage);
        this.settingsDialogStage.initModality(Modality.APPLICATION_MODAL);

        Parent root = this.settingsDialogFXMLLoader.load();
        this.settingsDialogStage.setTitle("Settings");
        this.settingsDialogStage.setResizable(false);
        this.settingsDialogStage.setScene(new Scene(root));
    }

    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    public Stage getSettingsDialogStage() {
        return this.settingsDialogStage;
    }

    public ProjectProperties getProperties() {
        return properties;
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("en"));

        launch(args);
    }

    public void startWorld() {
        this.startPhysics();
        this.startRender();
    }

    public void stopWorld() {
        this.stopPhysics();
        this.stopRender();
    }

    /**
     * Physics thread running at 30fps
     */
    private void startPhysics() {
        World worldLocal = this.world;

        this.physicsThread = new Thread(new Runnable() {
            double physicsFps = 1000f / 1230f; // TODO 30f можно читать из настроек приложения

            @Override
            public void run() {
                long prevTime = System.currentTimeMillis();
                long currTime;

                while (true) {

                    currTime = System.currentTimeMillis();

                    // run only at required physics fps
                    if ((currTime - prevTime) >= physicsFps) {
                        worldLocal.makeStep();

                        prevTime = currTime;
                    }
                }
            }
        });

        physicsThread.setDaemon(true);
        physicsThread.start();
    }

    private void stopPhysics()
    {
        this.physicsThread.interrupt();
        this.physicsThread = null;
    }

    public boolean isWorldStarted() {
        return null != this.physicsThread;
    }

    /**
     * Render loop
     */
    private void startRender()
    {
        World worldLocal = this.world;
        App app = this;
        MainController mainController = this.mainFXMLLoader.getController();

        this.renderLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    app.currentRenderer.render(
                            worldLocal.clone(),
                            mainController.canvas.getGraphicsContext2D()
                    );

                } catch (CloneNotSupportedException ex) {
                    // TODO что с этим делать?
                    System.out.println("ERROR");
                }

            }
        };

        this.renderLoop.start();

    }

    private void stopRender() {
        this.renderLoop.stop();
    }

    public SnapShotManager getSnapShotManager() {
        return snapShotManager;
    }

    public void setRenderer(IRenderer renderer) {
        this.currentRenderer = renderer;
    }

    public IRenderer getCurrentRenderer() {
        return this.currentRenderer;
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }
}
