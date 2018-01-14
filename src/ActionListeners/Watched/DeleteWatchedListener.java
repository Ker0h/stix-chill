package ActionListeners.Watched;

import DatabaseConnections.SQLExecutor;
import Watchables.Episode;
import Watchables.Film;
import Watchables.Series;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DeleteWatchedListener implements ActionListener {
    private JPanel panel;
    private SQLExecutor exe;
    private String episodeName;
    private String profileName;
    private int percentage;
    private JComboBox combo;
    private JComboBox combo2;

    public DeleteWatchedListener(JPanel panel, SQLExecutor exe, String episodeName, String profileName, int percentage, JComboBox combo, JComboBox combo2) {
        this.panel = panel;
        this.exe = exe;
        this.episodeName = episodeName;
        this.profileName = profileName;
        this.percentage = percentage;
        this.combo = combo;
        this.combo2 = combo2;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(profileName != null && episodeName != null) {
            if (episodeName.contains(": ")) {
                int x = episodeName.indexOf(": ");
                String programme = episodeName.substring(x + 2);
                ArrayList<Episode> episodes = new ArrayList<>();
                List<Series> series = exe.getSeries();
                for (Series ser : series) {
                    episodes.addAll(exe.getEpisodes(ser));
                }
                for (Episode epi : episodes) {
                    if (epi.getTitle().equals(programme)) {
                        exe.deleteWatched(profileName, epi.getProgrammeID());
                        JOptionPane.showMessageDialog(panel, "Deleted!");
                        int y = combo2.getSelectedIndex();
                        combo.setSelectedItem(combo.getSelectedItem());
                        combo2.setSelectedIndex(y);
                        setProfileName(null);
                        setEpisodeName(null);
                    }
                }
            } else {
                List<Film> films = exe.getFilms();
                for (Film film : films) {
                    if (film.getTitle().equals(episodeName)) {
                        exe.deleteWatched(profileName, film.getProgrammeID());
                        JOptionPane.showMessageDialog(panel, "Deleted!");
                        int x = combo2.getSelectedIndex();
                        combo.setSelectedItem(combo.getSelectedItem());
                        combo2.setSelectedIndex(x);
                        setProfileName(null);
                        setEpisodeName(null);
                    }
                }
            }
        }else{
            JOptionPane.showMessageDialog(panel,"Please select something before deleting!" );
        }
    }
}
