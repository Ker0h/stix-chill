package GUI.Watched;

import ActionListeners.Watched.WatchedListener;
import ActionListeners.Watched.WatchedSelectAccountListener;
import DatabaseConnections.SQLExecutor;
import GUI.ComboModel;
import UserData.Account;
import UserData.Profile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class WatchedPanel extends JPanel {
    public WatchedPanel(SQLExecutor exe){
        super(new BorderLayout());

        JComboBox selectAccount = new JComboBox();
        List<Account> accounts = exe.getAccounts();
        for(Account a:accounts){
            selectAccount.addItem(new ComboModel(a.getName(), a));
        }
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JComboBox selectProfile = new JComboBox();

        selectAccount.addActionListener(new WatchedSelectAccountListener(selectAccount, selectProfile, accounts, exe));
        selectProfile.addActionListener(new WatchedListener(selectProfile, table, exe, selectAccount));

        JPanel comboBoxPanel = new JPanel(new BorderLayout());
        comboBoxPanel.add(selectAccount, BorderLayout.WEST);
        comboBoxPanel.add(selectProfile, BorderLayout.EAST);

        this.add(comboBoxPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }
}
