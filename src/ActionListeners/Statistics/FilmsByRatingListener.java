package ActionListeners.Statistics;

import DatabaseConnections.SQLExecutor;
import Watchables.Film;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FilmsByRatingListener implements ActionListener {
    private JComboBox pg;
    private DefaultTableModel model;
    private SQLExecutor sql;


    public FilmsByRatingListener(JComboBox pg, DefaultTableModel model, SQLExecutor sql){
        this.pg = pg;
        this.model = model;
        this.sql = sql;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Film> film = sql.getLongestFilmForMinors(pg.getSelectedItem());
        if(model.getRowCount() > 0) {
            model.removeRow(model.getRowCount() - 1);
        }

        for(Film f:film){
            model.addRow(new Object[]{f.getTitle()});
        }
    }
}
