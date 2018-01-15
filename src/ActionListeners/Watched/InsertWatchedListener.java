package ActionListeners.Watched;

import DatabaseConnections.SQLExecutor;
import UserData.Profile;
import UserData.Watched;
import Watchables.Programme;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InsertWatchedListener implements ActionListener {
    private SQLExecutor exe;
    private JFrame frame;
    private JComboBox profileCombo;
    private JComboBox programmeCombo;
    private JTextField percentage;
    private ArrayList<Profile> profiles;
    private ArrayList programmes;
    private JComboBox combo1;
    private JComboBox combo2;

    public InsertWatchedListener(SQLExecutor exe, JFrame frame, JComboBox profileCombo, JComboBox programmeCombo, JTextField percentage, ArrayList profiles, ArrayList programmes, JComboBox combo1, JComboBox combo2) {
        this.exe = exe;
        this.frame = frame;
        this.profileCombo = profileCombo;
        this.programmeCombo = programmeCombo;
        this.percentage = percentage;
        this.profiles = profiles;
        this.programmes = programmes;
        this.combo1 = combo1;
        this.combo2 = combo2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // makes some new variables
        String selectedProfileName = profileCombo.getSelectedItem().toString();
        String selectedProgrammeName = programmeCombo.getSelectedItem().toString();
        Profile selectedProfile = null;
        // gets the right profilename
        for(Profile pro : profiles){
            if(pro.getProfileName().equals(selectedProfileName)){
                selectedProfile = pro;
            }
        }
        // gets the right programmeID
        int selectedProgrammeId = 0;
        for (Object p : programmes){
            Programme pr = (Programme) p;
            if(pr.getTitle().equals(selectedProgrammeName)){
                selectedProgrammeId = pr.getProgrammeID();
            }else if(selectedProgrammeName.toLowerCase().contains(pr.getTitle().toLowerCase())){
                selectedProgrammeId = pr.getProgrammeID();
            }
        }
        //checks the input and insert if correct
        if(percentage.getText().matches("^[1-9][0-9]?$|^100$")){
            int selectedPercentage = Integer.parseInt(percentage.getText());
            if(selectedPercentage <= 100 && selectedPercentage > 0){
                ArrayList<Watched> watched =(ArrayList<Watched>) exe.getWatched(selectedProfile);
                boolean exists = false;
                for(Watched wa :watched){
                    if(wa.GetProgrammeID() == selectedProgrammeId && wa.getprofileName().equals(selectedProfile.getProfileName())){
                        System.out.println("this data already exists");
                        exists = true;
                        JOptionPane.showMessageDialog(frame,"This data already exists!");
                        break;
                    }
                }
                if(!exists){
                    exe.insertWatched(selectedProfileName, selectedProgrammeId, selectedPercentage);
                    JOptionPane.showMessageDialog(frame,selectedProfileName + " has seen " + selectedPercentage + "% of " + selectedProgrammeName);
                    int y = combo2.getSelectedIndex();
                    combo1.setSelectedItem(combo1.getSelectedItem());
                    combo2.setSelectedIndex(y);
                    frame.dispose();
                }
            }
        }else{
            JOptionPane.showMessageDialog(frame,"Percentage is not a number or not between 1 and 100!" );
        }
    }
}
