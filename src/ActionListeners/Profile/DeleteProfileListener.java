package ActionListeners.Profile;

import DatabaseConnections.SQLExecutor;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteProfileListener implements ActionListener {
    private SQLExecutor exe;
    private JComboBox comboBox;
    private String profileName;

    public DeleteProfileListener(SQLExecutor exe, JComboBox comboBox, String profileName) {
        this.exe = exe;
        this.comboBox = comboBox;
        this.profileName = profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        exe.deleteProfile(profileName);
        comboBox.setSelectedIndex(comboBox.getSelectedIndex());

    }
}
