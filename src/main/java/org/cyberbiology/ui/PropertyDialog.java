package org.cyberbiology.ui;

import org.cyberbiology.MainWindow;
import org.cyberbiology.util.ProjectProperties;

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
        fileDirectoryName.setText(this.projectProperties.getOutputDirectory());

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
            this.projectProperties.setOutputDirectory(fileDirectoryName.getText());
        }
    }
}
