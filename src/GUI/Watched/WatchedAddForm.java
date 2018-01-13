package GUI.Watched;

import ActionListeners.Watched.InsertWatchedListener;
import DatabaseConnections.SQLExecutor;
import UserData.Account;
import UserData.Profile;
import Watchables.Episode;
import Watchables.Film;
import Watchables.Series;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WatchedAddForm implements Runnable {
    private JFrame frame;
    private SQLExecutor exe;
    private JComboBox combo1;
    private JComboBox combo2;

    public WatchedAddForm(SQLExecutor exe, JComboBox combo1, JComboBox combo2) {
        this.exe = exe;
        this.combo1 = combo1;
        this.combo2 = combo2;
    }

    @Override
    public void run() {
        frame = new JFrame("Add new watched");

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 250));

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }
    public void createComponents(Container container) {
        List<Account> accounts = exe.getAccounts();
        ArrayList<Profile> profiles = new ArrayList<>();
        ArrayList<String> comboProfileNames = new ArrayList<>();
        for(Account acc :  accounts){
            List<Profile> prof = exe.getProfiles(acc);
            profiles.addAll(prof);
        }
        for(Profile pr : profiles){
            comboProfileNames.add(pr.getProfileName());
        }
        List<Film> films = exe.getFilms();
        List<Series> series = exe.getSeries();
        ArrayList<Episode> episodes = new ArrayList<>();
        ArrayList<Object> allFilmAndEpisodes = new ArrayList<>();
        ArrayList<String> episodeAndFilmNames = new ArrayList<>();
        for(Series ser : series){
            episodes.addAll(exe.getEpisodes(ser));
        }
        for (Film f : films){
            episodeAndFilmNames.add(f.getTitle());
            allFilmAndEpisodes.add(f);
        }
        for(Episode e : episodes){
            episodeAndFilmNames.add(e.getSeries().getSeriesTitle() + ": " + e.getTitle());
            allFilmAndEpisodes.add(e);
        }


        JPanel panel = new JPanel();
        JComboBox comboProfiles = new JComboBox();
        comboProfiles.setModel(new DefaultComboBoxModel(comboProfileNames.toArray()));
        JComboBox comboProgrammes = new JComboBox();
        comboProgrammes.setModel(new DefaultComboBoxModel(episodeAndFilmNames.toArray()));
        JLabel percentageLabel = new JLabel("Percentage watched:");
        JTextField percentageInput = new JTextField();
        percentageInput.setPreferredSize(new Dimension(50, 24));
        percentageLabel.setLabelFor(percentageInput);

        JPanel buttonPanel = new JPanel();
        JButton addWatched = new JButton("Add a Watched");
        addWatched.addActionListener(new InsertWatchedListener(exe, frame, comboProfiles, comboProgrammes, percentageInput, profiles, allFilmAndEpisodes, combo1, combo2));
        buttonPanel.add(addWatched);

        panel.add(comboProfiles);
        panel.add(comboProgrammes);
        panel.add(percentageLabel);
        panel.add(percentageInput);

        JScrollPane scrollPane = new JScrollPane(panel);

        container.add(scrollPane);
        container.add(buttonPanel,BorderLayout.SOUTH);
    }
}
