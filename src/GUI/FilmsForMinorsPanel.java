package GUI;

import DatabaseConnections.SQLExecutor;
import ActionListeners.filmsForMinorsListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FilmsForMinorsPanel extends JPanel {
    FilmsForMinorsPanel(SQLExecutor exe){
        super(new BorderLayout());
        JComboBox pg = new JComboBox();
        pg.addItem(new ComboModel("6", 6));
        pg.addItem(new ComboModel("12", 12));
        pg.addItem(new ComboModel("16", 16));
        pg.addItem(new ComboModel("18", 18));

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Film");
        pg.addActionListener(new filmsForMinorsListener(pg, model, exe));
        JTable table = new JTable(model);

        this.add(pg, BorderLayout.NORTH);
        this.add(table, BorderLayout.CENTER);
    }

}
