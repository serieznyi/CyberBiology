package org.cyberbiology.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import org.cyberbiology.App;

public class PrimaryController {
    public MenuItem menuButtonRun;
    public MenuItem menuButtonExit;
    public MenuItem menuButtonSetting;
    public Label labelGeneration;
    public Label labelPopulation;
    public Label labelOrganic;
    public Pane mainPane;

    @FXML
    public void initialize() {

    }

    public void stopApp(ActionEvent actionEvent) {
    }

    public void startApp(ActionEvent actionEvent) {
    }

    public void showSettings(ActionEvent actionEvent) {
        App.getSelf().getSettingsDialogStage().showAndWait();
    }
}
