package ActionListeners.Profile;

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
    private JTable t;
    private SQLExecutor exe;

    public ProfilesListener(JComboBox selectAccount, JTable t, SQLExecutor exe){
        this.selectAccount = selectAccount;
        this.t = t;
        this.exe = exe;

        selectAccount.setSelectedIndex(0);

        List<Account> accounts = exe.getAccounts();
        Account selectedAccount = accounts.get(selectAccount.getSelectedIndex());

        System.out.println(selectedAccount.toString());
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Name");
        model.addColumn("Date of birth");

        for (int i = model.getRowCount(); i > 0; i--) {
            model.removeRow(i - 1);
        }

        for(Object o:exe.getProfiles(selectedAccount)){
            Profile p = (Profile) o;
            model.addRow(new Object[]{p.getProfileName(), p.getDateOfBirth()});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        List<Account> accounts = exe.getAccounts();
        Account selectedAccount = accounts.get(selectAccount.getSelectedIndex());
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Name");
        model.addColumn("Date of birth");

        for (int i = model.getRowCount(); i > 0; i--) {
            model.removeRow(i - 1);
        }

        for(Object o:exe.getProfiles(selectedAccount)){
            Profile p = (Profile) o;
            model.addRow(new Object[]{p.getProfileName(), p.getDateOfBirth()});
        }
        t.setModel(model);
    }
}
