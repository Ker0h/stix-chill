package ActionListeners.Profile;

import DatabaseConnections.SQLExecutor;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteProfileListener implements ActionListener {
    private SQLExecutor exe;
    private String profileName;
    private DefaultTableModel model;

    public DeleteProfileListener(SQLExecutor exe, String profileName, DefaultTableModel model) {
        this.exe = exe;
        this.profileName = profileName;
        this.model = model;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
