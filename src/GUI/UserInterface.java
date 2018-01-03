package GUI;

import DatabaseConnections.SQLExecutor;
import Watchables.*;
import UserData.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserInterface implements Runnable {
    private JFrame frame;
    SQLExecutor exe = new SQLExecutor();

    @Override
    public void run() {
        frame = new JFrame(" Stix & Chill");

        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        container.setLayout(new BorderLayout());
        container.add(createMenuPanel(), BorderLayout.CENTER);
        container.add(new FooterPanel(), BorderLayout.SOUTH);
    }

    private JTabbedPane createMenuPanel(){
        JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.LEFT);

        tabbedPane.add("Homepage", new HomepagePanel());
        tabbedPane.add("Average watchtime by series", new AverageSeriesPanel(exe));
        tabbedPane.add("Accounts with a single profile", new SingleProfileAccountsPanel(exe));

        return tabbedPane;
    }
}
