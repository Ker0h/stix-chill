package DatabaseConnections;

import UserData.Account;
import UserData.Profile;
import Watchables.Episode;
import Watchables.Film;
import Watchables.Series;

import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SQLExecutor sqlExecutor = new SQLExecutor();

        sqlExecutor.getAccounts();
        System.out.println("--------------------------------------------------------------------");
        sqlExecutor.getSeries();
        System.out.println("--------------------------------------------------------------------");
        Account acc = new Account(1215426, "Fam. van Raalte", "Schopenhauerdijkje", "5","3991 ML" , "Houten");
        sqlExecutor.getProfiles(acc);
        System.out.println("--------------------------------------------------------------------");
        Series ser = new Series("Sherlock", "Engels-Amerikaans", "Detective",12 ,"Fargo" );
        sqlExecutor.getEpisodes(ser);
        System.out.println("--------------------------------------------------------------------");
        sqlExecutor.getFilms();
        System.out.println("--------------------------------------------------------------------");
        sqlExecutor.getWatched((Profile) acc.getProfiles().get(1));
        System.out.println("--------------------------------------------------------------------");
        Film film = new Film(1001, "test", "00:01", "test", "NL", 69);
        sqlExecutor.getWatched(film);
        System.out.println("--------------------------------------------------------------------");
        Episode episode = new Episode(2003, "Cancer Man", "01:00", "S01E04", ser);
        sqlExecutor.getWatched(episode);



    }
}