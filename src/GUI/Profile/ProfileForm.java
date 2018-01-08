package GUI.Profile;

import DatabaseConnections.SQLExecutor;
import UserData.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProfileForm implements Runnable {
    private JFrame frame;
    private DefaultTableModel model;
    private SQLExecutor exe;
    private String profileName;
    private String dateOfBirth;
    private Account account;

    public ProfileForm(SQLExecutor exe, DefaultTableModel model, String profileName, String dateOfBirth, Account account) {
        this.model = model;
        this.exe = exe;
        this.profileName = profileName;
        this.dateOfBirth = dateOfBirth;
        this.account = account;
    }

    @Override
    public void run() {
        frame = new JFrame("Add new profile");

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(300, 400));

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container){
        JPanel panel = new JPanel(new BorderLayout());

        container.add(panel);
    }
}
