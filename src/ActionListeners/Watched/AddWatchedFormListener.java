package ActionListeners.Watched;

import DatabaseConnections.SQLExecutor;
import GUI.Watched.WatchedAddForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWatchedFormListener implements ActionListener{
    private SQLExecutor exe;
    private JComboBox combo1;
    private JComboBox combo2;


    public AddWatchedFormListener(SQLExecutor exe, JComboBox combo1, JComboBox combo2) {
        this.exe = exe;
        this.combo1 = combo1;
        this.combo2 = combo2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new WatchedAddForm(exe, combo1, combo2));
    }
}
