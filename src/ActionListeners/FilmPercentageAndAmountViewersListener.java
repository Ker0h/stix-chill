package ActionListeners;

import DatabaseConnections.SQLExecutor;
import UserData.Watched;
import Watchables.Film;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FilmPercentageAndAmountViewersListener implements ActionListener {
    private JTable t;
    private JComboBox c;

    public FilmPercentageAndAmountViewersListener(JTable t, JComboBox c) {
        this.t = t;
        this.c = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        SQLExecutor sql = new SQLExecutor();
        ArrayList<Film> fi = (ArrayList<Film>) sql.getFilms();
        Film film = fi.get(c.getSelectedIndex());
        ArrayList<Watched> wa = (ArrayList<Watched>) sql.getWatched(film);

        double totalAmountViewers = 0;
        double totalCompletedViewers = 0;

        for (Film fil : fi){
            for (Watched wat : wa){
                if(fil.getProgrammeID() == wat.GetProgrammeID()){
                    if(wat.getPercentage() == 100){
                        totalCompletedViewers++;
                        totalAmountViewers++;
                    }else{
                        totalAmountViewers++;
                    }
                }
            }
        }

        //make a new tableModel with 3 columns
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Title");
        model.addColumn("Total viewers");
        model.addColumn("Total of completed viewers");

        // add the rows with the film title, total amount of viewers, and the total viewers who completed the film 100%
        model.addRow(new Object[]{film.getTitle(), totalAmountViewers, totalCompletedViewers});

        //update the table you already made with the new model
        t.setModel(model);
    }
}
