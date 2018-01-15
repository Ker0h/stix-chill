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
    private JComboBox comboBox;
    private String profileName;
    private String dateOfBirth;
    private Account account;

    public ProfileFormListener(SQLExecutor exe, DefaultTableModel model, JComboBox comboBox, String profileName, String dateOfBirth, Account account) {
        this.exe = exe;
        this.model = model;
        this.comboBox = comboBox;
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

    //Invokes a new ProfileForm, possibly with selected data
    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new ProfileForm(exe, model, comboBox, e.getActionCommand(), profileName, dateOfBirth, account));
    }
}
