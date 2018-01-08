package ActionListeners.Account;

import DatabaseConnections.SQLExecutor;
import UserData.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertAccountListener implements ActionListener {
    private SQLExecutor exe;
    private DefaultTableModel model;
    private JTextField sub;
    private JTextField name;
    private JTextField street;
    private JTextField houseNumber;
    private JTextField postalCode;
    private JTextField city;

    public InsertAccountListener(SQLExecutor exe, DefaultTableModel model, JTextField sub, JTextField name, JTextField street, JTextField houseNumber, JTextField postalCode, JTextField city) {
        this.exe = exe;
        this.model = model;
        this.sub = sub;
        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        exe.insertAccount(sub.getText(), name.getText(), street.getText(), houseNumber.getText(), postalCode.getText(), city.getText());

        for (int i = model.getRowCount(); i > 0; i--) {
            model.removeRow(i - 1);
        }

        for (Account a : exe.getAccounts()) {
            model.addRow(new Object[]{a.getSubscriberNumber(), a.getName(), a.getCity(), a.getPostalCode(), a.getStreetName(), a.getHouseNumber()});
        }
    }
}
