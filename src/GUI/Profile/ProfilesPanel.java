package GUI.Profile;

import ActionListeners.Profile.DeleteProfileListener;
import ActionListeners.Profile.ProfileFormListener;
import ActionListeners.Profile.ProfilesListener;
import DatabaseConnections.SQLExecutor;
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
    private ProfileFormListener l;
    private DeleteProfileListener deleteProfileListener;

    public ProfilesPanel(SQLExecutor exe){
        super(new BorderLayout());

        JComboBox selectAccount = new JComboBox();
        for(Account a:exe.getAccounts()){
            selectAccount.addItem(new ComboModel(a.getName(), a));
        }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Date of birth");


        for (int i = model.getRowCount(); i > 0; i--) {
            model.removeRow(i - 1);
        }

        JTable table = new JTable(model);

        selectAccount.addActionListener(new ProfilesListener(selectAccount, table, exe));

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addProfileButton = new JButton("Add new profile");
        JButton edit = new JButton("Edit profile");
        JButton delete = new JButton("Delete profile");

        l = new ProfileFormListener(exe, model, selectAccount, exe.getAccounts().get(selectAccount.getSelectedIndex()));
        deleteProfileListener = new DeleteProfileListener(exe, profileName, model);

        MouseListener tableListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedRow = table.rowAtPoint(e.getPoint());
                profileName = (String) model.getValueAt(selectedRow, 0);
                dateOfBirth = (String) model.getValueAt(selectedRow, 1);

                edit.removeActionListener(l);
                delete.removeActionListener(deleteProfileListener);

                l.setProfileName(profileName);
                deleteProfileListener.setProfileName(profileName);
                l.setDateOfBirth(dateOfBirth);

                edit.addActionListener(l);
                delete.addActionListener(deleteProfileListener);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        table.addMouseListener(tableListener);

        addProfileButton.addActionListener(l);

        buttonPanel.add(addProfileButton);
        buttonPanel.add(edit);
        buttonPanel.add(delete);

        this.add(selectAccount, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
}
