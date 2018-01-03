package GUI;

import UserData.Account;
import DatabaseConnections.SQLExecutor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SingleProfileAccountsPanel extends JPanel {
    SingleProfileAccountsPanel(SQLExecutor exe){
        super(new BorderLayout());
        DefaultTableModel singleProfileModel = new DefaultTableModel();
        JTable singleProfileAccountTable = new JTable(singleProfileModel);
        singleProfileModel.addColumn("Accounts with a single profile");

        for(Account a:exe.getAccounts()){
            if(a.getProfiles().size() == 1);
            singleProfileModel.addRow(new Object[]{a.getSubscriberNumber()});
        }

        this.add(singleProfileAccountTable, BorderLayout.CENTER);
    }
}
