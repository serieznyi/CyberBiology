package org.cyberbiology.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.cyberbiology.App;
import org.cyberbiology.domain.Size;

public class MainController {
    public MenuItem menuButtonRun;
    public MenuItem menuButtonExit;
    public MenuItem menuButtonSetting;
    public Label labelGeneration;
    public Label labelPopulation;
    public Label labelOrganic;
    public Pane mainPane;
    public Canvas canvas;
    public Label labelMemory;
    private App app;

    public void actionStopApp(ActionEvent actionEvent) throws Exception {
        this.app.getPrimaryStage().close();
    }

    public void actionStartApp(ActionEvent actionEvent) {
        this.app.runWorld();

//        this.labelGeneration.setText("Generation: " + String.valueOf(world.getGeneration()));
//        this.labelPopulation.setText("Population: " + String.valueOf(world.population));
//        this.labelOrganic.setText("Organic: " + String.valueOf(world.organic));
//
//        Runtime runtime = Runtime.getRuntime();
//        long memory = runtime.totalMemory() - runtime.freeMemory();
//        this.labelMemory.setText(" Memory MB: " + String.valueOf(memory/(1024L * 1024L)));

//        if (null == this.thread) {
//            this.thread = this.createThread();
//            this.thread.start();
//            this.menuButtonRun.setText("Пауза");
//        } else {
//            this.thread.interrupt();
//            this.thread = null;
//            this.menuButtonRun.setText("Продолжить");
////            this.menuButtonSnapshot.setEnabled(true);
//        }
    }

    public void actionShowSettings(ActionEvent actionEvent) {
        this.app.getSettingsDialogStage().showAndWait();
    }

    @FXML
    public void initialize() {
        this.app = App.getSelf();

        this.app.getPrimaryStage().widthProperty().addListener((observableValue, oldSize, newSize) -> {
            mainPane.setPrefWidth(newSize.doubleValue());
        });

        this.app.getPrimaryStage().heightProperty().addListener((observableValue, oldSize, newSize) -> {
            mainPane.setPrefHeight(newSize.doubleValue());
        });

        this.mainPane.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            canvas.widthProperty().setValue(newSceneWidth);

            canvas.getGraphicsContext2D().setFill(Color.WHITE);
            canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        });

        this.mainPane.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            canvas.heightProperty().setValue(newSceneHeight);

            canvas.getGraphicsContext2D().setFill(Color.WHITE);
            canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        });

    }

    public Size getMainPaneSize() {
        return new Size(
                this.mainPane.getWidth(),
                this.mainPane.getHeight()
        );
    }
}
