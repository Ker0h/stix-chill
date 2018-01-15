package ActionListeners.Statistics;

import DatabaseConnections.SQLExecutor;
import UserData.Watched;
import Watchables.Episode;
import Watchables.Series;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class
SeriesPercentageWatchedListener implements ActionListener {
    private JTable t;
    private JComboBox cs;

    public SeriesPercentageWatchedListener(JTable t, JComboBox cs) {
        this.t = t;
        this.cs = cs;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        SQLExecutor sql = new SQLExecutor();
        ArrayList<Series> x = (ArrayList<Series>) sql.getSeries();
        Series s = x.get(cs.getSelectedIndex());
        ArrayList<Episode> ep = (ArrayList<Episode>) sql.getEpisodes(s);
        ArrayList<List<Watched>> wat = new ArrayList<>();

        double percentage = 0;
        double amountOfEpisodes = (double)ep.size();

        for (Episode epi : ep){
            ArrayList<Watched> wa = (ArrayList<Watched>) sql.getWatched(epi);
            wat.add(wa);
        }
        for (List watc : wat){
            for(Object watch : watc){
                Watched watched = (Watched)watch;
                percentage =+ (double) watched.getPercentage();
            }
        }

        //make a new tableModel with 2 columns
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Title");
        model.addColumn("Percentage watched");

        // add the rows with episode names and percentage watched
        model.addRow(new Object[]{s.getSeriesTitle(), percentage/amountOfEpisodes});


        //update the table you already made with the new model
        t.setModel(model);
    }
}
