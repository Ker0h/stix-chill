package GUI;

import DatabaseConnections.SQLExecutor;

import javax.swing.*;
import java.awt.*;

public class AccountForm implements Runnable{
    private JFrame frame;
    private SQLExecutor exe;

    @Override
    public void run() {
        frame = new JFrame("Add new account");

        frame.setPreferredSize(new Dimension(400, 500));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        createComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);

    }

    private void createComponents(Container container){
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(layout);

        JLabel subLabel = new JLabel("Subscriber number:");
        JTextField subField = new JTextField();

        panel.add(subLabel);
        panel.add(subField);

        container.add(panel);
    }
}
