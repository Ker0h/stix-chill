package GUI;

import ActionListeners.AccountFormListener;
import DatabaseConnections.SQLExecutor;
import UserData.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AccountsPanel extends JPanel {
    AccountsPanel(SQLExecutor exe){
        super(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Subscriber Number");
        model.addColumn("Name");
        model.addColumn("City");
        model.addColumn("Postal/Zip code");
        model.addColumn("Street");
        model.addColumn("House number");

        for(Account a:exe.getAccounts()){
            model.addRow(new Object[]{a.getSubscriberNumber(), a.getName(), a.getCity(), a.getPostalCode(), a.getStreetName(), a.getHouseNumber()});
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel crudButtons = new JPanel(new FlowLayout());
        JButton insert = new JButton("Add new account");
        JButton edit = new JButton("Update account");
        JButton delete = new JButton("Delete account");

        insert.addActionListener(new AccountFormListener(exe));

        crudButtons.add(insert);
        crudButtons.add(edit);
        crudButtons.add(delete);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(crudButtons, BorderLayout.SOUTH);
    }
}
