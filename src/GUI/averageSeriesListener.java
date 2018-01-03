package GUI;

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

        SQLExecutor sql = new SQLExecutor();
        ArrayList<Series> x = (ArrayList<Series>) sql.getSeries();
        Series s = x.get(c.getSelectedIndex());
        ArrayList<Episode> z = (ArrayList<Episode>) sql.getEpisodes(s);

        ArrayList<String> episodeName = new ArrayList<>();
        ArrayList<Double> percentage = new ArrayList<>();
        for(Episode epi : z){
            ArrayList<Watched> w = (ArrayList<Watched>) sql.getWatched(epi);
            System.out.println(w);
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
            episodeName.add(epi.getTitle());
            percentage.add(calculatedPer);
        }
        System.out.println(episodeName);
        System.out.println(percentage);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Title");
        model.addColumn("%");

        int i = 0;
        for(String episodename : episodeName){
            model.addRow(new Object[]{episodename, percentage.get(i)});
            i++;
        }
        
        t.setModel(model);
    }
}
