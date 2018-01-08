package ActionListeners.Watched;

import GUI.ComboModel;
import UserData.Account;
import UserData.Profile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WatchedSelectAccountListener implements ActionListener {
    private JComboBox selectAccount;
    private JComboBox selectProfile;

    public WatchedSelectAccountListener(JComboBox selectAccount, JComboBox selectProfile) {
        this.selectAccount = selectAccount;
        this.selectProfile = selectProfile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Account selectedAccount = (Account) selectAccount.getSelectedItem();
        for(Object p:selectedAccount.getProfiles()){
          Profile prof = (Profile) p;
          selectProfile.addItem(new ComboModel(prof.getProfileName(), prof));
        }
    }
}
