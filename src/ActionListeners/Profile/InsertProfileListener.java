package ActionListeners.Profile;

import DatabaseConnections.SQLExecutor;
import UserData.Account;
import UserData.Profile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertProfileListener implements ActionListener {
    private SQLExecutor exe;
    private DefaultTableModel model;
    private JComboBox comboBox;
    private JFrame frame;
    private JTextField profileName;
    private JTextField dateOfBirth;
    private Account account;

    public InsertProfileListener(SQLExecutor exe, DefaultTableModel model, JComboBox comboBox, JFrame frame, JTextField profileName, JTextField dateOfBirth, Account account) {
        this.exe = exe;
        this.model = model;
        this.comboBox = comboBox;
        this.frame = frame;
        this.profileName = profileName;
        this.dateOfBirth = dateOfBirth;
        this.account = account;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(dateOfBirth.getText().matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")){
            try {
                exe.insertProfile(profileName, dateOfBirth, account.getSubscriberNumber());
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
