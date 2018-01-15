package ActionListeners.Profile;

import DatabaseConnections.SQLExecutor;
import UserData.Account;
import UserData.Profile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateProfileListener implements ActionListener {
    private SQLExecutor exe;
    private DefaultTableModel model;
    private JComboBox comboBox;
    private JFrame frame;
    private String profileName;
    private JTextField nameField;
    private JTextField dateField;
    private Account account;

    public UpdateProfileListener(SQLExecutor exe, DefaultTableModel model, JComboBox comboBox, JFrame frame, String profileName, JTextField nameField, JTextField dateField, Account account) {
        this.exe = exe;
        this.model = model;
        this.comboBox = comboBox;
        this.frame = frame;
        this.profileName = profileName;
        this.nameField = nameField;
        this.dateField = dateField;
        this.account = account;
    }

    /*
     * If the given DateOfBirth matches the regex, updates the profile
     * Else it shows a message to the user.
     * Then the DefaultTableModel is refreshed, the FormPanel disposed and the earlier selected account reselected
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(dateField.getText().matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")){
            try {
                exe.updateProfile(profileName, nameField, dateField);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }

            for (int i = model.getRowCount(); i > 0; i--) {
                model.removeRow(i - 1);
            }

            for (Profile p : exe.getProfiles(account)) {
                model.addRow(new Object[]{p.getProfileName(), p.getDateOfBirth()});
            }

            frame.dispose();
            comboBox.setSelectedIndex(comboBox.getSelectedIndex());
        }else{
            JOptionPane.showMessageDialog(frame, "Date of birth is supposed to be YYY-MM-DD format");
        }

    }
}
