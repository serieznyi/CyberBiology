package org.cyberbiology.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import org.cyberbiology.App;
import org.cyberbiology_old.util.ProjectProperties;

import java.io.File;

public class SettingsDialogController {
    public Button saveButton;
    public Button closeButton;
    public Button selectDirButton;
    public TextField outputDirPathField;

    @FXML
    public void initialize() {
        ProjectProperties properties = App.getSelf().getProperties();
        this.outputDirPathField.setText(properties.getOutputDirectory());
    }

    public void actionSaveSettings(ActionEvent actionEvent) {
        ProjectProperties properties = App.getSelf().getProperties();

        properties.setOutputDirectory(this.outputDirPathField.getText());

        App.getSelf().getSettingsDialogStage().close();
    }

    public void actionCloseDialog(ActionEvent actionEvent) {
        App.getSelf().getSettingsDialogStage().close();
    }

    public void actionSelectOutputDirectory(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select output directory");
        File directory = directoryChooser.showDialog(App.getSelf().getSettingsDialogStage());

        if (directory != null) {
            System.out.println(directory.getAbsolutePath());

            this.outputDirPathField.setText(directory.getAbsolutePath());
        }
    }
}
