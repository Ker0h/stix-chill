package GUI;

import DatabaseConnections.SQLExecutor;
import GUI.Account.AccountsPanel;
import GUI.Profile.ProfilesPanel;
import GUI.Statistics.*;
import GUI.Watched.WatchedPanel;

import javax.swing.*;
import java.awt.*;

public class UserInterface implements Runnable {
    private JFrame frame;
    private SQLExecutor exe = new SQLExecutor();

    @Override
    public void run() {
        frame = new JFrame(" Stix & Chill");

        frame.setPreferredSize(new Dimension(1000, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        container.setLayout(new BorderLayout());
        container.add(createMenuPane(), BorderLayout.CENTER);
        container.add(new FooterPanel(), BorderLayout.SOUTH);
    }

    private JTabbedPane createMenuPane(){
        JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.LEFT);

        tabbedPane.add("Homepage", new HomepagePanel());
        tabbedPane.add("Average watchtime by series", new AverageSeriesPanel(exe));
        tabbedPane.add("Account average watchtime per series", new AverageSeriesPerAccountPanel(exe));
        tabbedPane.add("Accounts with a single profile", new SingleProfileAccountsPanel(exe));
        tabbedPane.add("Films per PG-rating", new FilmsByRatingPanel(exe));
        tabbedPane.add("Films watched by account", new FilmsByAccountPanel(exe));
        tabbedPane.add("Profiles that completed a film", new ProfilesThatCompletedAFilmPanel(exe));
        tabbedPane.add("EXTRA: Series percentage watched as a whole", new SeriesPercentageWatchedAsAWholePanel(exe));
        tabbedPane.add("EXTRA: Film percentage and amount of views", new FilmPercentageAndAmountOfViewersPanel(exe));

        tabbedPane.add("Data", createDataPane());
        return tabbedPane;
    }

    private JTabbedPane createDataPane(){
        JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);

        tabbedPane.add("Accounts", new AccountsPanel(exe));
        tabbedPane.add("Profiles", new ProfilesPanel(exe));
        tabbedPane.add("Watched", new WatchedPanel(exe));

        return tabbedPane;
    }
}
