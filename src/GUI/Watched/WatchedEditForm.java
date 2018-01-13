package GUI.Watched;

import ActionListeners.Watched.EditWatchedListener;
import DatabaseConnections.SQLExecutor;

import javax.swing.*;
import java.awt.*;

public class WatchedEditForm implements Runnable {
    private JFrame frame;
    private SQLExecutor exe;
    private String programmeName;
    private int percentage;
    private String profileName;

    public WatchedEditForm(SQLExecutor exe, String programmeName, int percentage, String profileName) {
        this.exe = exe;
        this.programmeName = programmeName;
        this.percentage = percentage;
        this.profileName = profileName;
    }

    @Override
    public void run() {
        frame = new JFrame("Edit watched");

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(450, 150));

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
        JPanel panel = new JPanel();

        JLabel label = new JLabel(profileName + " has watched " + programmeName + " for a percentage of: ");
        JTextField percentageField = new JTextField();
        percentageField.setPreferredSize(new Dimension(24,24));
        percentageField.setText(String.valueOf(percentage));
        label.setLabelFor(percentageField);

        JPanel buttonPanel = new JPanel();
        JButton editButton = new JButton("Update");
        editButton.addActionListener(new EditWatchedListener(exe, frame, profileName, programmeName, percentageField));
        buttonPanel.add(editButton);

        panel.add(label);
        panel.add(percentageField);

        JScrollPane scrollPane = new JScrollPane(panel);
        container.add(scrollPane);
        container.add(buttonPanel, BorderLayout.SOUTH);
    }
}
