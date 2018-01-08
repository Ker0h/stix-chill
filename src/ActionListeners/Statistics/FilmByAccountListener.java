package ActionListeners.Statistics;

import DatabaseConnections.SQLExecutor;
import UserData.Account;
import UserData.Profile;
import UserData.Watched;
import Watchables.Film;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FilmByAccountListener implements ActionListener {
    private JTable t;
    private JComboBox c;

    public FilmByAccountListener(JTable t, JComboBox c) {
        this.t = t;
        this.c = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //get the accounts, films, profiles and watched data from the database.
        SQLExecutor sql = new SQLExecutor();
        ArrayList<Account> ac = (ArrayList<Account>) sql.getAccounts();
        Account acc = ac.get(c.getSelectedIndex());
        ArrayList<Film> fi = (ArrayList<Film>) sql.getFilms();
        ArrayList<Profile> pr = (ArrayList<Profile>) sql.getProfiles(acc);
        ArrayList<List<Watched>> wa = new ArrayList<>();

        //Make 1 new arraylist for the end result.
        ArrayList<String> filmNames = new ArrayList<>();

        for(Profile pro : pr){
            wa.add( sql.getWatched(pro));
        }

        for(Film fil : fi){
            for (List wat :  wa){
                for(Object watch :  wat) {
                    Watched watche = (Watched) watch;
                    if (fil.getProgrammeID() == watche.GetProgrammeID()) {
                        filmNames.add(fil.getTitle());
                    }
                }
            }
        }

        //make a new tableModel with 2 columns
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Title");

        // add the rows with episode names and percentage watched
        int i = 0;
        for(i =0; i < filmNames.size(); i++){
            model.addRow(new Object[]{filmNames.get(i)});
        }

        //update the table you already made with the new model
        t.setModel(model);

    }
}
