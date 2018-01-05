package GUI;

import ActionListeners.InsertAccountListener;
import ActionListeners.UpdateAccountListener;
import DatabaseConnections.SQLExecutor;
import UserData.Account;

import javax.swing.*;
import java.awt.*;

public class AccountForm implements Runnable{
    private JFrame frame;
    private SQLExecutor exe;
    private String actionCommand;
    private String sub;
    private String name;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;

    public AccountForm(SQLExecutor exe, String actionCommand, String sub, String name, String street, String houseNumber, String postalCode, String city) {
        this.frame = new JFrame();
        this.exe = exe;
        this.actionCommand = actionCommand;
        this.sub = sub;
        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    @Override
    public void run() {
        frame = new JFrame("Add new account");

        frame.setPreferredSize(new Dimension(300, 400));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        createComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);

    }

    private void createComponents(Container container){
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        JLabel subLabel = new JLabel("Subscriber number:");
        JTextField subField = new JTextField();
        subLabel.setLabelFor(subField);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        nameLabel.setLabelFor(nameField);

        JLabel streetLabel = new JLabel("Street name:");
        JTextField streetField = new JTextField();
        streetLabel.setLabelFor(streetField);

        JLabel houseNumberLabel = new JLabel("House number");
        JTextField houseNumberField = new JTextField();
        houseNumberLabel.setLabelFor(houseNumberField);

        JLabel postalLabel = new JLabel("Postal/Zip code:");
        JTextField postalField = new JTextField();
        postalLabel.setLabelFor(postalField);

        JLabel cityLabel = new JLabel("City:");
        JTextField cityField = new JTextField();
        cityLabel.setLabelFor(cityField);

        JButton submit = new JButton("Save account");
        if(actionCommand.equals("Add new account")){
            frame.setTitle("Add new account");
            submit.addActionListener(new InsertAccountListener(exe, subField, nameField, streetField, houseNumberField, postalField, cityField));
        }else if(actionCommand.equals("Update account")){
            frame.setTitle("Update account");
            subField.setText(sub);
            nameField.setText(name);
            streetField.setText(street);
            houseNumberField.setText(houseNumber);
            postalField.setText(postalCode);
            cityField.setText(city);
            submit.addActionListener(new UpdateAccountListener(exe, subField, nameField, streetField, houseNumberField, postalField, cityField));
        }

        panel.add(subLabel);
        panel.add(subField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(streetLabel);
        panel.add(streetField);
        panel.add(houseNumberLabel);
        panel.add(houseNumberField);
        panel.add(postalLabel);
        panel.add(postalField);
        panel.add(cityLabel);
        panel.add(cityField);
        panel.add(submit);

        JScrollPane scrollPane = new JScrollPane(panel);

        container.add(scrollPane);
    }
}
