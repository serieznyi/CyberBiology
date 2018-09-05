package org.cyberbiology.controller;

import cyberbiology.World;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.scene.canvas.Canvas;
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
    public Canvas canvas;
    private App app;

    @FXML
    public void initialize() {
        this.app = App.getSelf();
        this.canvas.setHeight(this.mainPane.getHeight());
        this.canvas.setWidth(this.mainPane.getWidth());
    }

    public Dimension2D getMainPaneSize() {
        return new Dimension2D(
                this.mainPane.getWidth(),
                this.mainPane.getHeight()
        );
    }

    public void stopApp(ActionEvent actionEvent) throws Exception {
        this.app.getPrimaryStage().close();
    }

    public void startApp(ActionEvent actionEvent) {
        // TODO привести к интерфейсу или избавиться от него
//        this.app.getRenderer().render((World) this.app.getWorld(), this.canvas);
    }

    public void showSettings(ActionEvent actionEvent) {
        this.app.getSettingsDialogStage().showAndWait();
    }
}
