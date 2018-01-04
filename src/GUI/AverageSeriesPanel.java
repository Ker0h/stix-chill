package GUI;

import ActionListeners.averageSeriesListener;
import DatabaseConnections.SQLExecutor;
import Watchables.Series;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AverageSeriesPanel extends JPanel {
    AverageSeriesPanel(SQLExecutor exe) {
        super(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        model.addColumn("Episode");
        model.addColumn("%");

        JComboBox c = new JComboBox();
        for(Series s : exe.getSeries()){
            c.addItem(new ComboModel(s.getSeriesTitle(), s));
        }
        c.addActionListener(new averageSeriesListener(table, c));

        JScrollPane tableContainer = new JScrollPane(table);

        this.add(tableContainer, BorderLayout.CENTER);
        this.add(c, BorderLayout.NORTH);
    }
}
