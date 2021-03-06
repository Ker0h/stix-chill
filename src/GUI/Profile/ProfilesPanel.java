package GUI.Profile;

import ActionListeners.Profile.DeleteProfileListener;
import ActionListeners.Profile.ProfileFormListener;
import ActionListeners.Profile.ProfilesListener;
import DatabaseConnections.SQLExecutor;
import GUI.ComboBoxUpdater;
import GUI.ComboModel;
import UserData.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class ProfilesPanel extends JPanel {
    private int selectedRow;
    private String profileName;
    private String dateOfBirth;
    private ProfileFormListener formListener;
    private DeleteProfileListener deleteProfileListener;

    public ProfilesPanel(SQLExecutor exe){
        super(new BorderLayout());
        List<Account> accounts = exe.getAccounts();

        JComboBox selectAccount = new JComboBox();
        for(Account a:exe.getAccounts()){
            selectAccount.addItem(new ComboModel(a.getName(), a));
        }
        ComboBoxUpdater.addAccountBox(selectAccount);

        JTable table = new JTable();


        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addProfileButton = new JButton("Add new profile");
        JButton edit = new JButton("Edit profile");
        JButton delete = new JButton("Delete profile");

        formListener = new ProfileFormListener(exe, (DefaultTableModel) table.getModel(), selectAccount, profileName, dateOfBirth, accounts.get(selectAccount.getSelectedIndex()));
        deleteProfileListener = new DeleteProfileListener(exe, selectAccount, profileName);

        //Updates the data in the form- and deletelistener depending on the selected account in the table
        MouseListener tableListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedRow = table.rowAtPoint(e.getPoint());
                profileName = (String) table.getModel().getValueAt(selectedRow, 0);
                dateOfBirth = (String) table.getModel().getValueAt(selectedRow, 1);

                edit.removeActionListener(formListener);
                delete.removeActionListener(deleteProfileListener);

                formListener.setProfileName(profileName);
                deleteProfileListener.setProfileName(profileName);
                formListener.setDateOfBirth(dateOfBirth);
                formListener.setAccount(accounts.get(selectAccount.getSelectedIndex()));

                edit.addActionListener(formListener);
                delete.addActionListener(deleteProfileListener);
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

        selectAccount.addActionListener(new ProfilesListener(selectAccount, table, exe, addProfileButton, accounts));
        addProfileButton.addActionListener(formListener);

        JScrollPane scrollPane = new JScrollPane(table);

        buttonPanel.add(addProfileButton);
        buttonPanel.add(edit);
        buttonPanel.add(delete);

        this.add(selectAccount, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
}
