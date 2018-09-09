package org.cyberbiology.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.cyberbiology.App;
import org.cyberbiology.WorldHandler;
import org.cyberbiology.domain.Size;
import org.cyberbiology.helper.MemoryHelper;
import org.cyberbiology.prototype.view.IRenderer;

public class MainController {
    public MenuItem menuButtonRun;
    public MenuItem menuButtonExit;
    public MenuItem menuButtonSetting;
    public Label labelGeneration;
    public Label labelPopulation;
    public Pane mainPane;
    public Canvas canvas;
    public Label labelMemory;
    public MenuItem menuButtonMakeSnapshot;
    public Menu renderModeMenu;
    private App app;

    public void actionStopApp(ActionEvent actionEvent) throws Exception {
        this.app.getPrimaryStage().close();
    }

    public void actionStartApp(ActionEvent actionEvent) {
        WorldHandler worldHandler = this.app.getWorldHandler();
        if (!worldHandler.isWorked()) {
            worldHandler.start();
            this.menuButtonRun.setText("Pause");
            this.menuButtonMakeSnapshot.setDisable(true);
        } else {
            worldHandler.stop();
            this.menuButtonRun.setText("Continue");
            this.menuButtonMakeSnapshot.setDisable(false);
        }
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

        for (IRenderer renderer : App.AVAILABLE_RENDERERS) {
            MenuItem menuItem = new MenuItem(renderer.getName());

            menuItem.setOnAction(e -> app.getWorldHandler().setRenderer(renderer));

            this.renderModeMenu.getItems().add(menuItem);
        }
    }

    public void initEventListeners() {
        this.app.getWorld().addListener(world -> {
            labelGeneration.setText("Iteration: " + String.valueOf(world.getIteration()));
            labelPopulation.setText("Population: " + String.valueOf(world.getPopulation()));

            Runtime runtime = Runtime.getRuntime();
            long usedMemory = runtime.totalMemory() - runtime.freeMemory();
            String usedMemoryHuman = MemoryHelper.human(usedMemory, true);
            String totalMemoryHuman = MemoryHelper.human(runtime.totalMemory(), true);
            labelMemory.setText(" Memory: " + usedMemoryHuman + " / " + totalMemoryHuman);
        });
    }

    public Size getMainPaneSize() {
        return new Size(
                this.mainPane.getWidth(),
                this.mainPane.getHeight()
        );
    }

    public void actionMakeSnapshot() {
        this.app.getSnapShotManager().makeSnapShot(this.app.getWorld());
    }
}
