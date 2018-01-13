package GUI.Statistics;

import UserData.Account;
import DatabaseConnections.SQLExecutor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SingleProfileAccountsPanel extends JPanel {
    public SingleProfileAccountsPanel(SQLExecutor exe){
        super(new BorderLayout());
        DefaultTableModel singleProfileModel = new DefaultTableModel();
        JTable singleProfileAccountTable = new JTable(singleProfileModel);
        singleProfileModel.addColumn("Accounts with a single profile");

        for(Account a:exe.getAccounts()){
            System.out.println(a.getName() + ": " + a.getProfiles().size());
            if(a.getProfiles().size() == 1) {
                singleProfileModel.addRow(new Object[]{a.getSubscriberNumber()});
            }
        }

        JScrollPane scrollPane = new JScrollPane(singleProfileAccountTable);

        this.add(scrollPane, BorderLayout.CENTER);
    }
}
