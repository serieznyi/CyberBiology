package org.cyberbiology.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.cyberbiology.App;
import org.cyberbiology.domain.Size;
import org.cyberbiology.helper.MemoryHelper;
import org.cyberbiology.prototype.view.IRenderer;

import java.util.ResourceBundle;

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
    private ResourceBundle bundle;

    public void actionStopApp() {
        this.app.stopWorld();
        this.app.getPrimaryStage().close();
    }

    public void actionStartApp() {
        if (!this.app.isWorldStarted()) {
            this.app.startWorld();
            this.menuButtonRun.setText(this.app.getResourceBundle().getString("menu.title.state.pause"));
            this.menuButtonMakeSnapshot.setDisable(true);
        } else {
            this.app.stopWorld();
            this.menuButtonRun.setText(this.app.getResourceBundle().getString("menu.title.state.continue"));
            this.menuButtonMakeSnapshot.setDisable(false);
        }
    }

    public void actionShowSettings() {
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

        ToggleGroup groupEffect = new ToggleGroup();
        for (IRenderer renderer : App.AVAILABLE_RENDERERS) {
            RadioMenuItem menuItem = new RadioMenuItem(renderer.getName());
            menuItem.setToggleGroup(groupEffect);

            if (renderer.getName().equals(app.getCurrentRenderer().getName())) {
                menuItem.setSelected(true);
            }

            menuItem.setOnAction(e -> app.setRenderer(renderer));

            this.renderModeMenu.getItems().add(menuItem);
        }

        Rectangle clip = new Rectangle(this.mainPane.getWidth(), this.mainPane.getHeight());
        this.mainPane.setClip(clip);
        this.mainPane.getChildren().add(this.setupShadowPane());
    }

    private Pane setupShadowPane() {
        int shadowSize = 100;

        Pane shadowPane = new Pane();
        shadowPane.setStyle(
                "-fx-background-color: white;" +
                "-fx-effect: dropshadow(gaussian, black, " + shadowSize + ", 0, 0, 0);" +
                "-fx-background-insets: " + shadowSize + ";"
        );

        Rectangle innerBounds = new Rectangle();
        Rectangle outerBounds = new Rectangle();
        shadowPane.layoutBoundsProperty().addListener((observable, oldBounds, newBounds) -> {
            innerBounds.relocate(newBounds.getMinX() + shadowSize, newBounds.getMinY() + shadowSize);
            innerBounds.setWidth(newBounds.getWidth() - shadowSize * 2);
            innerBounds.setHeight(newBounds.getHeight() - shadowSize * 2);
            outerBounds.setWidth(newBounds.getWidth());
            outerBounds.setHeight(newBounds.getHeight());

            Shape clip = Shape.subtract(outerBounds, innerBounds);
            shadowPane.setClip(clip);
        });

        return shadowPane;
    }

    public void initEventListeners() {
        this.app.getWorld().addListener(world -> {
            Platform.runLater(() -> {
                labelGeneration.setText("Iteration: " + String.valueOf(world.getIteration()));
                labelPopulation.setText("Population: " + String.valueOf(world.getPopulation()));

                Runtime runtime = Runtime.getRuntime();
                long usedMemory = runtime.totalMemory() - runtime.freeMemory();
                String usedMemoryHuman = MemoryHelper.human(usedMemory, true);
                String totalMemoryHuman = MemoryHelper.human(runtime.totalMemory(), true);
                labelMemory.setText(" Memory: " + usedMemoryHuman + " / " + totalMemoryHuman);
            });
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
