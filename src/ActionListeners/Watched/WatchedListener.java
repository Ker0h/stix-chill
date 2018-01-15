package ActionListeners.Watched;

import DatabaseConnections.SQLExecutor;
import UserData.Account;
import UserData.Profile;
import UserData.Watched;
import Watchables.Episode;
import Watchables.Film;
import Watchables.Series;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WatchedListener implements ActionListener {
    private JComboBox selectProfile;
    private JComboBox selectAccount;
    private JTable t;
    private SQLExecutor exe;

    public WatchedListener(JComboBox selectProfile, JTable t, SQLExecutor exe, JComboBox selectAccount){
        this.selectProfile = selectProfile;
        this.t = t;
        this.exe = exe;
        this.selectAccount = selectAccount;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(selectProfile.getSelectedIndex()== -1){
            return;
        }
        List<Account> accounts = exe.getAccounts();

        int selectedIndex = selectAccount.getSelectedIndex();
        if(selectedIndex < 0){
            selectedIndex += 1;
        }
        Account acc = accounts.get(selectedIndex);

        List<Profile> profiles = exe.getProfiles(acc);
        Profile prof = profiles.get(selectProfile.getSelectedIndex());
        List<Watched> watched = exe.getWatched(prof);
        List<Film> films = exe.getFilms();
        List<Episode> episodes = new ArrayList<>();
        for(Series ser : exe.getSeries()){
            List<Episode> epi = exe.getEpisodes(ser);
            episodes.addAll(epi);
        }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Title");
        model.addColumn("Percentage");

        for(Watched wa : watched){
            for(Film fi: films){
                if(fi.getProgrammeID() == wa.GetProgrammeID()){
                    model.addRow(new Object[]{fi.getTitle(), wa.getPercentage()});
                }
            }
            for(Episode ep: episodes){
                if(ep.getProgrammeID() == wa.GetProgrammeID()){
                    model.addRow(new Object[]{ep.getSeries().getSeriesTitle() + ": " + ep.getTitle(), wa.getPercentage()});
                }
            }
        }
        t.setModel(model);

    }
}
