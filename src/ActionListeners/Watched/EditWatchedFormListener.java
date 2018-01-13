package ActionListeners.Watched;

import DatabaseConnections.SQLExecutor;
import GUI.Watched.WatchedEditForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditWatchedFormListener implements ActionListener {
    private SQLExecutor exe;
    private String episodeName;
    private String profileName;
    private int percentage;

    public EditWatchedFormListener(SQLExecutor exe, String episodeName, int percentage, String profileName) {
        this.exe = exe;
        this.episodeName = episodeName;
        this.percentage = percentage;
        this.profileName = profileName;
    }

    public void setExe(SQLExecutor exe) {
        this.exe = exe;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new WatchedEditForm(exe, episodeName, percentage, profileName));
    }
}
