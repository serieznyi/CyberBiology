package org.cyberbiology;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import cyberbiology.util.ProjectProperties;

import java.io.IOException;

public class App extends Application {
    private static App self;
    private Stage primaryStage;
    private Stage settingsDialogStage;
    private ProjectProperties properties;

    @Override
    public void start(Stage primaryStage) throws Exception {
        App.self = this;

        this.primaryStage = primaryStage;

        this.properties	= new ProjectProperties("properties.xml");

        this.createSettingsDialogStage();

        this.configurePrimaryStage();

        this.primaryStage.show();

    }

    public static App getSelf() {
        return App.self;
    }

    private void configurePrimaryStage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../fxml/primary_window.fxml"));
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
}
