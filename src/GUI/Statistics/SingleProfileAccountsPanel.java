package GUI.Statistics;

import GUI.SingleProfileTableUpdater;
import UserData.Account;
import DatabaseConnections.SQLExecutor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SingleProfileAccountsPanel extends JPanel {
    public SingleProfileAccountsPanel(SQLExecutor exe){
        super(new BorderLayout());
        DefaultTableModel singleProfileModel = new DefaultTableModel();
        SingleProfileTableUpdater.setSingleProfileModel(singleProfileModel);
        JTable singleProfileAccountTable = new JTable(singleProfileModel);

        singleProfileModel.addColumn("Subscriber number");
        singleProfileModel.addColumn("Name");
        singleProfileModel.addColumn("City");
        singleProfileModel.addColumn("Postal/Zip code");
        singleProfileModel.addColumn("Street");
        singleProfileModel.addColumn("House number");

        for(Account a:exe.getAccountsWithSingleProfile()){
            singleProfileModel.addRow(new Object[]{a.getSubscriberNumber(),
                    a.getName(),
                    a.getCity(),
                    a.getPostalCode(),
                    a.getStreetName(),
                    a.getHouseNumber()
            });
        }

        JScrollPane scrollPane = new JScrollPane(singleProfileAccountTable);

        this.add(scrollPane, BorderLayout.CENTER);
    }
}
