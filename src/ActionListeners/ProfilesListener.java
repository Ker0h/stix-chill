package ActionListeners;

import DatabaseConnections.SQLExecutor;
import UserData.Account;
import UserData.Profile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProfilesListener implements ActionListener {
    private JComboBox selectAccount;
    private DefaultTableModel model;
    private SQLExecutor exe;

    public ProfilesListener(JComboBox selectAccount, DefaultTableModel model, SQLExecutor exe){
        this.selectAccount = selectAccount;
        this.model = model;
        this.exe = exe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Account> accounts = exe.getAccounts();
        Account selectedAccount = accounts.get(selectAccount.getSelectedIndex());

        for(Object o:selectedAccount.getProfiles()){
            Profile p = (Profile) o;
            model.addRow(new Object[]{p.getProfileName(), p.getDateOfBirth()});
        }

    }
}
