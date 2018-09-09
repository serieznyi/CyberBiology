package org.cyberbiology;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.cyberbiology_old.snapshot.SnapShotManager;
import org.cyberbiology_old.util.ProjectProperties;
import org.cyberbiology_old.World;
import org.cyberbiology.controller.MainController;
import java.io.IOException;
import org.cyberbiology.domain.Size;
import org.cyberbiology_old.prototype.view.IRenderer;
import org.cyberbiology_old.prototype.IWorld;
import org.cyberbiology_old.view.BasicRenderer;

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
    private FXMLLoader mainFXMLLoader;
    private IRenderer renderer = new BasicRenderer();
    private SnapShotManager snapShotManager;
    private WorldHandler worldHandler;

    @Override
    public void start(Stage primaryStage) throws Exception {
        App.self = this;

        this.primaryStage = primaryStage;

        this.mainFXMLLoader = new FXMLLoader(getClass().getResource("../../fxml/main.fxml"));

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

        Parent root = FXMLLoader.load(getClass().getResource("../../fxml/settings_dialog.fxml"));
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
        launch(args);
    }

    private IRenderer getRenderer() {
        return renderer;
    }

    public WorldHandler getWorldHandler() {
        MainController primaryController = this.mainFXMLLoader.getController();

        if (null == this.worldHandler) {
            this.worldHandler = new WorldHandler(
                    (World) this.getWorld(),
                    this.getRenderer(),
                    primaryController.canvas.getGraphicsContext2D()
            );
        }

        return this.worldHandler;
    }

    public SnapShotManager getSnapShotManager() {
        return snapShotManager;
    }
}
