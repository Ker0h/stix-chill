package GUI;

import ActionListeners.ProfilesThatCompletedAFilmListener;
import DatabaseConnections.SQLExecutor;
import Watchables.Film;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProfilesThatCompletedAFilmPanel extends JPanel {
    ProfilesThatCompletedAFilmPanel(SQLExecutor exe){
        super(new BorderLayout());

        JComboBox selectFilm = new JComboBox<>();
        List<Film> films = exe.getFilms();

        for(Film f:films){
            selectFilm.addItem(new ComboModel(f.getTitle(), f));
        }
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Profiles");
        selectFilm.addActionListener(new ProfilesThatCompletedAFilmListener(selectFilm, model, exe));
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        this.add(selectFilm, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }
}
