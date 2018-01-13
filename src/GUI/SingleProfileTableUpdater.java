package GUI;

import UserData.Account;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public abstract class SingleProfileTableUpdater {
    private static DefaultTableModel model;

    //Used after initialisation in the SingleProfileAccountsPanel
    public static void setSingleProfileModel(DefaultTableModel model){
        SingleProfileTableUpdater.model = model;
    }

    //Updates the DefaultTableModel during runtime
    public static void updateSingleProfileModel(List<Account> singleProfileAccounts){
        for (int i = model.getRowCount(); i > 0; i--) {
            model.removeRow(i - 1);
        }

        for (Account a : singleProfileAccounts) {
            model.addRow(new Object[]{a.getSubscriberNumber(), a.getName(), a.getCity(), a.getPostalCode(), a.getStreetName(), a.getHouseNumber()});
        }
    }
}
