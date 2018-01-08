package GUI.Statistics;

import ActionListeners.Statistics.FilmByAccountListener;
import DatabaseConnections.SQLExecutor;
import GUI.ComboModel;
import UserData.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FilmsByAccountPanel extends JPanel {
    public FilmsByAccountPanel(SQLExecutor exe){
        super(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        model.addColumn("Film");

        JComboBox c = new JComboBox();
        for(Account acc : exe.getAccounts()){
            c.addItem(new ComboModel(acc.getName(), acc));
        }
        c.addActionListener(new FilmByAccountListener(table, c));

        JScrollPane tableContainer = new JScrollPane(table);

        this.add(tableContainer, BorderLayout.CENTER);
        this.add(c, BorderLayout.NORTH);
    }
}
