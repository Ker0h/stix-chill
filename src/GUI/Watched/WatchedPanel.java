package GUI.Watched;

import ActionListeners.Watched.AddWatchedFormListener;
import ActionListeners.Watched.EditWatchedFormListener;
import ActionListeners.Watched.WatchedListener;
import ActionListeners.Watched.WatchedSelectAccountListener;
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

public class WatchedPanel extends JPanel {
    private int percentage;
    private String profileName;
    private String episodeName;
    private EditWatchedFormListener editListener;

    public WatchedPanel(SQLExecutor exe){
        super(new BorderLayout());

        JComboBox selectAccount = new JComboBox();
        List<Account> accounts = exe.getAccounts();
        for(Account a:accounts){
            selectAccount.addItem(new ComboModel(a.getName(), a));
        }
        ComboBoxUpdater.addAccountBox(selectAccount);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JComboBox selectProfile = new JComboBox();

        selectAccount.addActionListener(new WatchedSelectAccountListener(selectAccount, selectProfile, accounts, exe));
        selectProfile.addActionListener(new WatchedListener(selectProfile, table, exe, selectAccount));

        JPanel comboBoxPanel = new JPanel(new BorderLayout());
        comboBoxPanel.add(selectAccount, BorderLayout.WEST);
        comboBoxPanel.add(selectProfile, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton add = new JButton("Add a new Watched");
        JButton edit = new JButton("Edit watched");
        JButton delete = new JButton("Delete watched");
        add.addActionListener(new AddWatchedFormListener(exe));

        editListener = new EditWatchedFormListener(exe, episodeName, percentage, profileName);

        MouseListener tableListener = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.rowAtPoint(e.getPoint());
                episodeName = (String) table.getValueAt(selectedRow,0);
                percentage = (int) table.getValueAt(selectedRow,1);
                Object profile = selectProfile.getSelectedItem();
                profileName = profile.toString();

                edit.removeActionListener(editListener);

                editListener.setEpisodeName(episodeName);
                editListener.setPercentage(percentage);
                editListener.setProfileName(profileName);

                edit.addActionListener(editListener);
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

        buttonPanel.add(add);
        buttonPanel.add(edit);
        buttonPanel.add(delete);

        this.add(comboBoxPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
}
