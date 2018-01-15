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

public class EditWatchedListener implements ActionListener {
    private SQLExecutor exe;
    private JFrame frame;
    private String profileName;
    private String programmeName;
    private JTextField percentage;
    private JComboBox combo;
    private JComboBox combo2;


    public EditWatchedListener(SQLExecutor exe, JFrame frame, String profileName, String programmeName, JTextField percentage, JComboBox combo, JComboBox combo2) {
        this.exe = exe;
        this.frame = frame;
        this.profileName = profileName;
        this.programmeName = programmeName;
        this.percentage = percentage;
        this.combo = combo;
        this.combo2 = combo2;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //checks if input is correct then update
        if(percentage.getText().matches("^[1-9][0-9]?$|^100$")){
            if(programmeName.contains(": ")){
                int x = programmeName.indexOf(": ");
                String programme = programmeName.substring(x+2);
                ArrayList<Episode> episodes = new ArrayList<>();
                List<Series> series = exe.getSeries();
                for(Series ser : series){
                    episodes.addAll(exe.getEpisodes(ser));
                }
                for(Episode epi : episodes){
                    if(epi.getTitle().equals(programme)){
                        exe.editWatched(profileName, epi.getProgrammeID(), Integer.parseInt(percentage.getText()));
                        JOptionPane.showMessageDialog(frame,profileName + " has now watched " + programmeName + " for " + percentage.getText() + "%!");
                        int y = combo2.getSelectedIndex();
                        combo.setSelectedItem(combo.getSelectedItem());
                        combo2.setSelectedIndex(y);
                        frame.dispose();
                    }
                }
            }else{
                List<Film> films = exe.getFilms();
                for(Film film : films){
                    if(film.getTitle().equals(programmeName)){
                        exe.editWatched(profileName, film.getProgrammeID(), Integer.parseInt(percentage.getText()));
                        JOptionPane.showMessageDialog(frame,profileName + " has now watched " + programmeName + " for " + percentage.getText() + "%!");
                        int x = combo2.getSelectedIndex();
                        combo.setSelectedItem(combo.getSelectedItem());
                        combo2.setSelectedIndex(x);
                        frame.dispose();
                    }
                }
            }
        }else{
            JOptionPane.showMessageDialog(frame,"Percentage is not a number or not between 1 and 100!" );
        }
    }
}
