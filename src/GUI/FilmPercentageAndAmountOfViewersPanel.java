package GUI;

import ActionListeners.FilmPercentageAndAmountViewersListener;
import ActionListeners.averageSeriesListener;
import DatabaseConnections.SQLExecutor;
import Watchables.Film;
import Watchables.Series;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FilmPercentageAndAmountOfViewersPanel extends JPanel {
    FilmPercentageAndAmountOfViewersPanel(SQLExecutor exe) {
        super(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        model.addColumn("");
        model.addColumn("");

        JComboBox c = new JComboBox();
        for(Film f : exe.getFilms()){
            c.addItem(new ComboModel(f.getTitle(), f));
        }
        c.addActionListener(new FilmPercentageAndAmountViewersListener(table, c));

        JScrollPane tableContainer = new JScrollPane(table);

        this.add(tableContainer, BorderLayout.EAST);
        this.add(c, BorderLayout.NORTH);
    }
}
