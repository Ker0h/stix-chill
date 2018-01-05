package ActionListeners;

import DatabaseConnections.SQLExecutor;
import GUI.AccountForm;
import UserData.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountFormListener implements ActionListener {
    private SQLExecutor exe;
    private String sub;
    private String name;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;

    public AccountFormListener(SQLExecutor exe){
        this.exe = exe;
    }

    public AccountFormListener(SQLExecutor exe, String sub, String name, String street, String houseNumber, String postalCode, String city) {
        this.exe = exe;
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
        System.out.println(sub);
        SwingUtilities.invokeLater(new AccountForm(exe, e.getActionCommand(), sub, name, street, houseNumber, postalCode, city));
    }
}
