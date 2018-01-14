package GUI.Statistics;

import DatabaseConnections.SQLExecutor;
import ActionListeners.Statistics.FilmsByRatingListener;
import GUI.ComboModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FilmsByRatingPanel extends JPanel {
    public FilmsByRatingPanel(SQLExecutor exe){
        super(new BorderLayout());
        JComboBox pg = new JComboBox();
        pg.addItem(new ComboModel("6", 6));
        pg.addItem(new ComboModel("12", 12));
        pg.addItem(new ComboModel("16", 16));
        pg.addItem(new ComboModel("18", 18));

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Film");
        pg.addActionListener(new FilmsByRatingListener(pg, model, exe));
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        this.add(pg, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

}
