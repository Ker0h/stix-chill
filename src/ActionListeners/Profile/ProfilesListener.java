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
    private JTable table;
    private SQLExecutor exe;
    private JButton button;
    private List<Account> accounts;

    public ProfilesListener(JComboBox selectAccount, JTable table, SQLExecutor exe, JButton button, List<Account> accounts){
        this.selectAccount = selectAccount;
        this.exe = exe;
        this.table = table;
        this.button = button;
        this.accounts = accounts;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Account selectedAccount = accounts.get(selectAccount.getSelectedIndex());
            DefaultTableModel model = new DefaultTableModel();
            ProfileFormListener l = new ProfileFormListener(exe, model, selectAccount, selectedAccount);

            model.addColumn("Name");
            model.addColumn("Date of birth");

            for (int i = model.getRowCount(); i > 0; i--) {
                model.removeRow(i - 1);
            }

            for (Object o : exe.getProfiles(selectedAccount)) {
                Profile p = (Profile) o;
                model.addRow(new Object[]{p.getProfileName(), p.getDateOfBirth()});
            }

            table.setModel(model);
            button.addActionListener(l);
        }catch (Exception ex){}
    }
}
