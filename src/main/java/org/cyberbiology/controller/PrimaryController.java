package org.cyberbiology.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import org.cyberbiology.App;
import org.cyberbiology.LoopedThread;
import org.cyberbiology.domain.Size;
import org.cyberbiology_old.World;
import org.cyberbiology_old.prototype.IWorld;

public class PrimaryController {
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
    private Thread thread;

    @FXML
    public void initialize() {
        this.app = App.getSelf();
        this.canvas.setHeight(this.mainPane.getHeight());
        this.canvas.setWidth(this.mainPane.getWidth());
    }

    public Size getMainPaneSize() {
        return new Size(
                this.mainPane.getWidth(),
                this.mainPane.getHeight()
        );
    }

    public void stopApp(ActionEvent actionEvent) throws Exception {
        this.app.getPrimaryStage().close();
    }

    public void startApp(ActionEvent actionEvent) {
        if (null == this.thread) {
            this.thread = this.createThread();
            this.thread.start();
            this.menuButtonRun.setText("Пауза");
        } else {
            this.thread.interrupt();
            this.thread = null;
            this.menuButtonRun.setText("Продолжить");
//            this.menuButtonSnapshot.setEnabled(true);
        }
    }

    private LoopedThread createThread() {
        return new LoopedThread(() -> {
            World world = (World) this.app.getWorld();

            world.makeStep();

            Platform.runLater(() -> {
                this.labelGeneration.setText("Generation: " + String.valueOf(world.getGeneration()));
                this.labelPopulation.setText("Population: " + String.valueOf(world.population));
                this.labelOrganic.setText("Organic: " + String.valueOf(world.organic));

                Runtime runtime = Runtime.getRuntime();
                long memory = runtime.totalMemory() - runtime.freeMemory();
                this.labelMemory.setText(" Memory MB: " + String.valueOf(memory/(1024L * 1024L)));

                if (world.getGeneration() % 10 == 0) {
//                buffer = renderer.render(world, paintPanel);
                }

//            this.paintPanel.repaint();
            });
        });
    }

    public void showSettings(ActionEvent actionEvent) {
        this.app.getSettingsDialogStage().showAndWait();
    }
}
