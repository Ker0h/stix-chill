package ActionListeners;

import DatabaseConnections.SQLExecutor;
import UserData.Account;
import UserData.Profile;
import UserData.Watched;
import Watchables.Episode;
import Watchables.Film;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WatchedListener implements ActionListener {
    private JComboBox selectProfile;
    private DefaultTableModel model;
    private SQLExecutor exe;
    private List<Profile> profiles;

    public WatchedListener(JComboBox selectProfile, DefaultTableModel model, SQLExecutor exe, List<Profile> profiles){
        this.selectProfile = selectProfile;
        this.model = model;
        this.exe = exe;
        this.profiles = profiles;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Watched> watched = exe.getWatched(profiles.get(selectProfile.getSelectedIndex()));

        for(int i = model.getRowCount(); i > 0; i--){
            model.removeRow(i - 1);
        }

        for(Watched w:watched){
            int programmeID = w.GetProgrammeID();
            model.addRow(new Object[]{w.getprofileName(), exe.getProgrammeTitleByID(programmeID), w.getPercentage()});
        }
    }
}
