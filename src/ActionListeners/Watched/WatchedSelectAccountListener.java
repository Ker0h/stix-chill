package ActionListeners.Watched;

import DatabaseConnections.SQLExecutor;
import GUI.ComboModel;
import UserData.Account;
import UserData.Profile;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class WatchedSelectAccountListener implements ActionListener {
    private JComboBox selectAccount;
    private JComboBox selectProfile;
    private List<Account> accounts;
    private SQLExecutor exe;

    public WatchedSelectAccountListener(JComboBox selectAccount, JComboBox selectProfile, List<Account> accounts, SQLExecutor exe) {
        this.selectAccount = selectAccount;
        this.selectProfile = selectProfile;
        this.accounts = accounts;
        this.exe = exe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Account acc = accounts.get(selectAccount.getSelectedIndex());

        ArrayList<Profile> prof = (ArrayList<Profile>) exe.getProfiles(acc);
        selectProfile.removeAllItems();
        for(Profile p : prof){
            selectProfile.addItem(new ComboModel(p.getProfileName(),p));
        }
    }
}
