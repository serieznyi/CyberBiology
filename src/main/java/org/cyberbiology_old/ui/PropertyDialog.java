package org.cyberbiology_old.ui;

import org.cyberbiology_old.MainWindow;
import org.cyberbiology_old.util.ProjectProperties;

import javax.swing.*;

public class PropertyDialog extends JDialog {

    private final ProjectProperties projectProperties;
    private final MainWindow window;

    public PropertyDialog (ProjectProperties projectProperties, MainWindow window)
    {
        this.projectProperties = projectProperties;
        this.window = window;
    }

    @Override
    public void show() {
        JTextField fileDirectoryName = new JTextField();
        fileDirectoryName.setText(this.projectProperties.getFileDirectory());

        final JComponent[] inputs = new JComponent[]
                {
                        new JLabel("Директория для хранения файлов записи"),
                        fileDirectoryName,
                };

        int result = JOptionPane.showConfirmDialog(
                this.window,
                inputs,
                "Настройки",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null
        );

        if (result == JOptionPane.OK_OPTION)
        {
            this.projectProperties.setFileDirectory(fileDirectoryName.getText());
        }
    }
}
