package ActionListeners;

import DatabaseConnections.SQLExecutor;
import UserData.Watched;
import Watchables.Episode;
import Watchables.Series;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import java.util.ArrayList;

public class averageSeriesListener implements ActionListener {
    public JTable t;
    public JComboBox c;

    public averageSeriesListener(JTable t, JComboBox c) {
        this.t = t;
        this.c = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //get the Episodes from the selected option from the Jcombobox.
        SQLExecutor sql = new SQLExecutor();
        ArrayList<Series> x = (ArrayList<Series>) sql.getSeries();
        Series s = x.get(c.getSelectedIndex());
        ArrayList<Episode> z = (ArrayList<Episode>) sql.getEpisodes(s);

        //Make 2 new arraylists for the end result.
        ArrayList<String> episodeNames = new ArrayList<>();
        ArrayList<Double> percentage = new ArrayList<>();

        //loop through every Episode to get all the people who watched the episode with the percentage they watched.
        for(Episode epi : z){
            ArrayList<Watched> w = (ArrayList<Watched>) sql.getWatched(epi);
            double t = 0;
            double totalPer = 0;
            for (Watched wat : w){
                totalPer =+ wat.getPercentage();
                t++;
            }
            double calculatedPer = totalPer / t;
            if (Double.isNaN(calculatedPer)){
                calculatedPer = 0;
            }
            episodeNames.add(epi.getTitle());
            percentage.add(calculatedPer);
        }

        //make a new tableModel with 2 columns
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Title");
        model.addColumn("%");

        // add the rows with episode names and percentage watched
        int i = 0;
        for(String episodeName : episodeNames){
            model.addRow(new Object[]{episodeName, percentage.get(i)});
            i++;
        }
        
        //update the table you already made with the new model
        t.setModel(model);
    }
}
