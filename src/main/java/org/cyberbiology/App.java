package org.cyberbiology;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import cyberbiology.util.ProjectProperties;
import org.cyberbiology.controller.PrimaryController;
import java.io.IOException;
import org.cyberbiology.domain.Size;
import cyberbiology.World;
import cyberbiology.prototype.view.IRenderer;
import cyberbiology.prototype.IWorld;

public class App extends Application {
    private static final int BOT_WIDTH = 4;
    private static final int BOT_HEIGHT = 4;

    private static App self;
    private Stage primaryStage;
    private Stage settingsDialogStage;
    private ProjectProperties properties;
    private World world;
    private FXMLLoader primaryFXMLLoader;
    private IRenderer renderer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        App.self = this;

        this.primaryStage = primaryStage;

        this.primaryFXMLLoader = new FXMLLoader(getClass().getResource("../../fxml/primary_window.fxml"));

        this.properties	= new ProjectProperties("properties.xml");

        this.properties.load();

        this.createSettingsDialogStage();

        this.configurePrimaryStage();

        PrimaryController primaryController = this.primaryFXMLLoader.getController();

        Size mainPaneSize = primaryController.getMainPaneSize();

//        this.world = new World(
//                (int) mainPaneSize.getWidth() / BOT_WIDTH,
//                (int) mainPaneSize.getHeight() / BOT_HEIGHT
//        );

        this.primaryStage.show();
    }

    public IWorld getWorld() {
        return this.world;
    }

    public static App getSelf() {
        return App.self;
    }

    private void configurePrimaryStage() throws IOException {
        Parent root = this.primaryFXMLLoader.load();

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

    public IRenderer getRenderer() {
        return renderer;
    }
}
