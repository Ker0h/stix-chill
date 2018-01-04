package GUI;

import ActionListeners.AverageSeriesPerAccountListener;
import ActionListeners.averageSeriesListener;
import DatabaseConnections.SQLExecutor;
import UserData.Account;
import Watchables.Series;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AverageSeriePerAccountPanel extends JPanel {
    AverageSeriePerAccountPanel(SQLExecutor exe){
        super(new BorderLayout());
        GridLayout grid = new GridLayout(0,2);
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        model.addColumn("");
        model.addColumn("");

        JComboBox ca = new JComboBox();
        for(Account a : exe.getAccounts()){
            ca.addItem(new ComboModel(a.getName(), a));
        }

        JComboBox cs = new JComboBox();
        for(Series s : exe.getSeries()){
            cs.addItem(new ComboModel(s.getSeriesTitle(), s));
        }

        AverageSeriesPerAccountListener ASPAL = new AverageSeriesPerAccountListener(table, ca, cs);
        ca.addActionListener(ASPAL);
        cs.addActionListener(ASPAL);

        //ca.addActionListener(new averageSeriesListener(table, ca));
        JPanel comboboxPanel = new JPanel();
        comboboxPanel.setLayout(grid);
        comboboxPanel.add(ca);
        comboboxPanel.add(cs);
        JScrollPane tableContainer = new JScrollPane(table);

        this.add(tableContainer, BorderLayout.EAST);
        this.add(comboboxPanel, BorderLayout.NORTH);

    }
}
