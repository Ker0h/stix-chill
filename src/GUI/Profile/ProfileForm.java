package GUI.Profile;

import ActionListeners.Profile.InsertProfileListener;
import ActionListeners.Profile.UpdateProfileListener;
import DatabaseConnections.SQLExecutor;
import UserData.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProfileForm implements Runnable {
    private JFrame frame;
    private DefaultTableModel model;
    private SQLExecutor exe;
    private String actionCommand;
    private String profileName;
    private String dateOfBirth;
    private Account account;

    public ProfileForm(SQLExecutor exe, DefaultTableModel model, String actionCommand, String profileName, String dateOfBirth, Account account) {
        this.model = model;
        this.exe = exe;
        this.actionCommand = actionCommand;
        this.profileName = profileName;
        this.dateOfBirth = dateOfBirth;
        this.account = account;
    }

    @Override
    public void run() {
        frame = new JFrame("Add new profile");

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(300, 150));

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container){
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        JLabel nameLabel = new JLabel("Profile name:");
        JTextField nameField = new JTextField();
        nameLabel.setLabelFor(nameField);

        JLabel dateOfBirthLabel = new JLabel("Date of birth:");
        JTextField dateOfBirthField = new JTextField();
        dateOfBirthLabel.setLabelFor(dateOfBirthField);


        JButton submit = new JButton("Save profile");

        if(actionCommand.equals("Add new profile")){
            frame.setTitle("Add new profile");
            submit.addActionListener(new InsertProfileListener(exe, model, frame, nameField, dateOfBirthField, account));
        }else if(actionCommand.equals("Update profile")){
            frame.setTitle("Update profile");
            nameField.setText(profileName);
            dateOfBirthField.setText(dateOfBirth);
            //submit.addActionListener(new UpdateProfileListener(exe, model, frame, profileName, nameField, dateOfBirthField, account));
        }

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(dateOfBirthLabel);
        panel.add(dateOfBirthField);
        panel.add(submit);

        JScrollPane scrollPane = new JScrollPane(panel);

        container.add(scrollPane);
    }
}
