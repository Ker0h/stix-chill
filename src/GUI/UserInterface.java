package GUI;

import DatabaseConnections.SQLExecutor;

import javax.swing.*;
import java.awt.*;

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
        tabbedPane.add("Account average watchtime per series", new AverageSeriePerAccountPanel(exe));
        tabbedPane.add("Accounts with a single profile", new SingleProfileAccountsPanel(exe));
        tabbedPane.add("Films per PG-rating", new FilmsForMinorsPanel(exe));
        tabbedPane.add("Films watched by account", new FilmsByAccountPanel(exe));
        tabbedPane.add("Profiles that completed a film", new ProfilesThatCompletedAFilmPanel(exe));
        tabbedPane.add("EXTRA: Serie percentage watched as a whole", new SeriePercentageWatchedAsAWholePanel(exe));


        tabbedPane.add("Data", createDataPane());
        return tabbedPane;
    }

    private JTabbedPane createDataPane(){
        JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);

        tabbedPane.add("Accounts", new AccountsPanel(exe));

        return tabbedPane;
    }
}
