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
        container.add(createFooterPanel(), BorderLayout.SOUTH);
    }


    private JTabbedPane createMenuPanel(){
        JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.LEFT);
        BorderLayout layout = new BorderLayout();

        JPanel averageSeries = new JPanel(layout);
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        model.addColumn("Episode");
        model.addColumn("%");
        //model.addRow(new Object[]{"v1", "v2"});

        JComboBox c = new JComboBox();
        for(Series s : exe.getSeries()){
            c.addItem(new ComboModel(s.getSeriesTitle(), s));
        }
        c.addActionListener(new averageSeriesListener(table, c));

        JScrollPane tableContainer = new JScrollPane(table);

        averageSeries.add(tableContainer, BorderLayout.EAST);

        averageSeries.add(c, BorderLayout.NORTH);

        JPanel singleProfile = new JPanel(layout);
        JTextArea profileArea = new JTextArea();
        JScrollPane profileScroll = new JScrollPane(profileArea);

        //Account.getSingleProfileAccounts();
        singleProfile.add(profileScroll);

        tabbedPane.add("Average watchtime by series", averageSeries);
        tabbedPane.add("Accounts with a single profile", singleProfile);

        return tabbedPane;
    }

    private JPanel createFooterPanel(){
        JPanel panel = new JPanel();
        FlowLayout layout = new FlowLayout();
        panel.setLayout(layout);

        panel.add(new JLabel("Informatica"));
        panel.add(new JLabel("2017"));
        panel.add(new JLabel("Klas F"));
        panel.add(new JLabel("Stijn van Veen, <studentnummer>"));
        panel.add(new JLabel("Yannick Willems, 2128086"));
        panel.add(new JLabel("Jop van Wijnen, <studentnummer>"));

        return panel;
    }

}
