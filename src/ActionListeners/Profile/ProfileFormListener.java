package ActionListeners.Profile;

import DatabaseConnections.SQLExecutor;
import GUI.Profile.ProfileForm;
import UserData.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileFormListener implements ActionListener {
    private SQLExecutor exe;
    private DefaultTableModel model;
    private String profileName;
    private String dateOfBirth;
    private Account account;

    public ProfileFormListener(SQLExecutor exe, DefaultTableModel model, Account account){
        this.exe = exe;
        this.model = model;
        this.account = account;
    }

    public ProfileFormListener(SQLExecutor exe, DefaultTableModel model, String profileName, String dateOfBirth, Account account) {
        this.exe = exe;
        this.model = model;
        this.profileName = profileName;
        this.dateOfBirth = dateOfBirth;
        this.account = account;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new ProfileForm(exe, model, profileName, dateOfBirth, account));
    }
}
