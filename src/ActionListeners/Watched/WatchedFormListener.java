package ActionListeners.Watched;

import DatabaseConnections.SQLExecutor;
import GUI.Watched.WatchedForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WatchedFormListener implements ActionListener{
    private SQLExecutor exe;

    public WatchedFormListener(SQLExecutor exe) {
        this.exe = exe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new WatchedForm(exe));
    }
}
