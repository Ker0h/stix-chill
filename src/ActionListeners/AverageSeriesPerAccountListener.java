package ActionListeners;

import DatabaseConnections.SQLExecutor;
import UserData.Account;
import UserData.Watched;
import Watchables.Episode;
import Watchables.Series;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AverageSeriesPerAccountListener implements ActionListener {
    public JTable t;
    public JComboBox ca;
    public JComboBox cs;

    public AverageSeriesPerAccountListener(JTable t, JComboBox ca, JComboBox cs) {
        this.t = t;
        this.ca = ca;
        this.cs = cs;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(ca.getSelectedItem());
        System.out.println(cs.getSelectedItem());

        //get the Episodes from the selected option from the Jcombobox.
        SQLExecutor sql = new SQLExecutor();
        ArrayList<Series> x = (ArrayList<Series>) sql.getSeries();
        Series s = x.get(cs.getSelectedIndex());
        ArrayList<Episode> ep = (ArrayList<Episode>) sql.getEpisodes(s);
        ArrayList<Account> ac = (ArrayList<Account>) sql.getAccounts();
        Account acc = ac.get(ca.getSelectedIndex());
        ArrayList<Watched> wa = (ArrayList<Watched>) sql.getWatched(acc, s);

        ArrayList<String> episodeNames = new ArrayList<>();
        ArrayList<Double> percentage = new ArrayList<>();
        ArrayList<String> season = new ArrayList<>();

        for(Episode epi : ep){
            double t = 0;
            double totalPer = 0;
            for (Watched wat : wa){
                if(epi.getProgrammeID() == wat.GetProgrammeID()){
                    totalPer =+ wat.getPercentage();
                    t++;
                    break;
                }
                totalPer = 0;
            }
            double calculatedPer = totalPer / t;
            if (Double.isNaN(calculatedPer)){
                calculatedPer = 0;
            }
            episodeNames.add(epi.getTitle());
            percentage.add(calculatedPer);
            season.add(epi.getSeason());
        }
        //make a new tableModel with 2 columns
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Title");
        model.addColumn("Season");
        model.addColumn("Percentage watched");

        // add the rows with episode names and percentage watched
        int i = 0;
        for(String episodeName : episodeNames){
            model.addRow(new Object[]{episodeName, season.get(i), percentage.get(i)});
            i++;
        }

        //update the table you already made with the new model
        t.setModel(model);

    }
}
