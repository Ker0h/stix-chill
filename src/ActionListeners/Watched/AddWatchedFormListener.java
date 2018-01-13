package ActionListeners.Watched;

import DatabaseConnections.SQLExecutor;
import GUI.Watched.WatchedAddForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWatchedFormListener implements ActionListener{
    private SQLExecutor exe;


    public AddWatchedFormListener(SQLExecutor exe) {
        this.exe = exe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new WatchedAddForm(exe));
    }
}
