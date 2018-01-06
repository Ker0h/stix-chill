package ActionListeners;

import DatabaseConnections.SQLExecutor;
import GUI.AccountForm;
import UserData.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountFormListener implements ActionListener {
    private SQLExecutor exe;
    private DefaultTableModel model;
    private String sub;
    private String name;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;

    public AccountFormListener(SQLExecutor exe, DefaultTableModel model){
        this.exe = exe;
        this.model = model;
    }

    public AccountFormListener(SQLExecutor exe, DefaultTableModel model, String sub, String name, String street, String houseNumber, String postalCode, String city) {
        this.exe = exe;
        this.model = model;
        this.sub = sub;
        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new AccountForm(exe, model, e.getActionCommand(), sub, name, street, houseNumber, postalCode, city));
    }
}
