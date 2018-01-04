package GUI;

import ActionListeners.SeriesPercentageWatchedListener;
import DatabaseConnections.SQLExecutor;
import Watchables.Series;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SeriePercentageWatchedAsAWholePanel extends JPanel {
    SeriePercentageWatchedAsAWholePanel(SQLExecutor exe) {
        super(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        model.addColumn("Series");
        model.addColumn("%");

        JComboBox c = new JComboBox();
        for (Series s : exe.getSeries()) {
            c.addItem(new ComboModel(s.getSeriesTitle(), s));
        }
        c.addActionListener(new SeriesPercentageWatchedListener(table, c));

        JScrollPane tableContainer = new JScrollPane(table);

        this.add(tableContainer, BorderLayout.EAST);
        this.add(c, BorderLayout.NORTH);
    }
}
