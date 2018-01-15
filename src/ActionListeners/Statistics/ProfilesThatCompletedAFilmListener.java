package ActionListeners.Statistics;

import DatabaseConnections.SQLExecutor;
import UserData.Profile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilesThatCompletedAFilmListener implements ActionListener {
    private JComboBox selectFilm;
    private DefaultTableModel model;
    private SQLExecutor exe;

    public ProfilesThatCompletedAFilmListener(JComboBox selectFilm, DefaultTableModel model, SQLExecutor exe){
        this.selectFilm = selectFilm;
        this.exe = exe;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = model.getRowCount(); i > 0; i--){
            model.removeRow(i - 1);
        }

        for(String p:exe.getProfilesThatCompletedAFilm(selectFilm.getSelectedItem().toString())){
            model.addRow(new Object[]{p});
        }
    }
}
