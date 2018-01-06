package ActionListeners;

import DatabaseConnections.SQLExecutor;
import UserData.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteAccountListener implements ActionListener {
    private SQLExecutor exe;
    private String sub;
    private DefaultTableModel model;

    public DeleteAccountListener(SQLExecutor exe, String sub, DefaultTableModel model) {
        this.exe = exe;
        this.sub = sub;
        this.model = model;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        exe.deleteAccount(sub);

        for (int i = model.getRowCount(); i > 0; i--) {
            model.removeRow(i - 1);
        }

        for (Account a : exe.getAccounts()) {
            model.addRow(new Object[]{a.getSubscriberNumber(), a.getName(), a.getCity(), a.getPostalCode(), a.getStreetName(), a.getHouseNumber()});
        }
    }
}
