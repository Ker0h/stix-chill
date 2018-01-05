package ActionListeners;

import DatabaseConnections.SQLExecutor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertAccountListener implements ActionListener {
    private SQLExecutor exe;
    private JTextField sub;
    private JTextField name;
    private JTextField street;
    private JTextField houseNumber;
    private JTextField postalCode;
    private JTextField city;

    public InsertAccountListener(SQLExecutor exe, JTextField sub, JTextField name, JTextField street, JTextField houseNumber, JTextField postalCode, JTextField city) {
        this.exe = exe;
        this.sub = sub;
        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
