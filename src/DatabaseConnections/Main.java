package DatabaseConnections;

import UserData.Account;
import UserData.Profile;
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
    }
}