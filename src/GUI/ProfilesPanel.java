package GUI;

import ActionListeners.ProfilesListener;
import DatabaseConnections.SQLExecutor;
import UserData.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProfilesPanel extends JPanel {
    ProfilesPanel(SQLExecutor exe){
        super(new BorderLayout());

        JComboBox selectAccount = new JComboBox();
        for(Account a:exe.getAccounts()){
            selectAccount.addItem(new ComboModel(a.getName(), a));
        }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Date of birth");
        selectAccount.addActionListener(new ProfilesListener(selectAccount, model, exe));

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        this.add(selectAccount, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }
}
