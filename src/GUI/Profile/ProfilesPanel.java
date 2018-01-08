package GUI.Profile;

import ActionListeners.Profile.ProfileFormListener;
import ActionListeners.Profile.ProfilesListener;
import DatabaseConnections.SQLExecutor;
import GUI.ComboModel;
import UserData.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProfilesPanel extends JPanel {
    private int selectedRow;
    private String profileName;
    private String dateOfBirth;
    private Account account;
    private ProfileFormListener l;

    public ProfilesPanel(SQLExecutor exe){
        super(new BorderLayout());

        JComboBox selectAccount = new JComboBox();
        for(Account a:exe.getAccounts()){
            selectAccount.addItem(new ComboModel(a.getName(), a));
        }

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        model.addColumn("Name");
        model.addColumn("Date of birth");
        selectAccount.addActionListener(new ProfilesListener(selectAccount, table, exe));

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addProfileButton = new JButton("Add new profile");

        addProfileButton.addActionListener(new ProfileFormListener(exe, model, account));

        buttonPanel.add(addProfileButton);

        this.add(selectAccount, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
}
