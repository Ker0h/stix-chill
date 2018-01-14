package GUI.Account;

import ActionListeners.Account.AccountFormListener;
import ActionListeners.Account.DeleteAccountListener;
import DatabaseConnections.SQLExecutor;
import UserData.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AccountsPanel extends JPanel {
    private int selectedRow;
    private String sub;
    private String name;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private AccountFormListener formListener;
    private DeleteAccountListener deleteAccountListener;

    public AccountsPanel(SQLExecutor exe){
        super(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Subscriber number");
        model.addColumn("Name");
        model.addColumn("City");
        model.addColumn("Postal/Zip code");
        model.addColumn("Street");
        model.addColumn("House number");

        for (int i = model.getRowCount(); i > 0; i--) {
            model.removeRow(i - 1);
        }

        for (Account a : exe.getAccounts()) {
            model.addRow(new Object[]{
                    a.getSubscriberNumber(),
                    a.getName(),
                    a.getCity(),
                    a.getPostalCode(),
                    a.getStreetName(),
                    a.getHouseNumber()
            });
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel crudButtons = new JPanel(new FlowLayout());
        JButton insert = new JButton("Add new account");
        JButton edit = new JButton("Update account");
        JButton delete = new JButton("Delete account");

        formListener = new AccountFormListener(exe, model, sub, name, street, houseNumber, postalCode, city);
        deleteAccountListener = new DeleteAccountListener(exe, sub, model);

        //Updates the data in the form- and delete listener depending on the selected account in the table
        MouseListener tableListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedRow = table.rowAtPoint(e.getPoint());
                sub = (String) model.getValueAt(selectedRow , 0);
                name = (String) model.getValueAt(selectedRow , 1);
                street = (String) model.getValueAt(selectedRow, 4);
                houseNumber = (String) model.getValueAt(selectedRow, 5);
                postalCode = (String) model.getValueAt(selectedRow, 3);
                city = (String) model.getValueAt(selectedRow, 2);

                edit.removeActionListener(formListener);
                delete.removeActionListener(deleteAccountListener);

                formListener.setSub(sub);
                deleteAccountListener.setSub(sub);
                formListener.setName(name);
                formListener.setStreet(street);
                formListener.setHouseNumber(houseNumber);
                formListener.setPostalCode(postalCode);
                formListener.setCity(city);

                edit.addActionListener(formListener);
                delete.addActionListener(deleteAccountListener);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };
        table.addMouseListener(tableListener);

        insert.addActionListener(new AccountFormListener(exe, model));

        crudButtons.add(insert);
        crudButtons.add(edit);
        crudButtons.add(delete);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(crudButtons, BorderLayout.SOUTH);
    }
}
