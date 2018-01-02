package GUI;

import Watchables.*;
import UserData.*;

import javax.swing.*;
import java.awt.*;

public class UserInterface implements Runnable {
    private JFrame frame;

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
        JComboBox<Series> selectSeries = new JComboBox();
        averageSeries.add(selectSeries, BorderLayout.NORTH);

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
