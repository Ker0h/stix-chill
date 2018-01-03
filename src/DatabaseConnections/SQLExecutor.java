package DatabaseConnections;

import UserData.Account;
import UserData.Profile;
import UserData.Watched;
import Watchables.Episode;
import Watchables.Film;
import Watchables.Programme;
import Watchables.Series;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLExecutor {
    private ResultSet resultSet = null;

    public List<Account> getAccounts(){
        List<Account> accounts = new ArrayList<>();
        DBConnector dbConnector = new DBConnector();
        try {
            String SQL = "SELECT * FROM Account";
            resultSet = dbConnector.runSQL(SQL);

            while (resultSet.next()) {
                int subNo = resultSet.getInt("SubscriberNumber");
                String name = resultSet.getString("Name");
                String streetName = resultSet.getString("StreetName");
                String postalCode = resultSet.getString("PostalCode");
                String houseNumber = resultSet.getString("HouseNumber");
                String city = resultSet.getString("City");

                Account acc = new Account(subNo,name,streetName,houseNumber,postalCode,city);
                accounts.add(acc);
               // System.out.println(acc.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }
        return accounts;
    }

    public List<Profile> getProfiles(Account account){
        List<Profile> profiles = new ArrayList<>();
        DBConnector dbConnector = new DBConnector();
        try {
            String SQL = "SELECT * FROM Profile WHERE SubscriberNumber = " + account.getSubscriberNumber();
            resultSet = dbConnector.runSQL(SQL);

            while (resultSet.next()) {
                String profileName = resultSet.getString("ProfileName");
                String dateOfBirth = resultSet.getString("DateOfBirth");

                Profile prof = new Profile(profileName, dateOfBirth, account);
                account.addProfile(prof);
                profiles.add(prof);
            }
            //System.out.println(account.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }
        return profiles;
    }

    public List<Series> getSeries(){
        List<Series> series = new ArrayList<>();
        DBConnector dbConnector = new DBConnector();
        try {
            String SQL = "SELECT * FROM Series";
            resultSet = dbConnector.runSQL(SQL);

            while (resultSet.next()) {
                String seriesTitle = resultSet.getString("SerieTitle");
                String language = resultSet.getString("Language");
                String genre = resultSet.getString("Genre");
                int pg = resultSet.getInt("PG");
                String isLike = resultSet.getString("IsLike");

                Series ser = new Series(seriesTitle,language,genre,pg,isLike);
                series.add(ser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }
        return series;
    }

    public List<Episode> getEpisodes(Series ser){
        List<Episode> episodes = new ArrayList<>();
        DBConnector dbConnector = new DBConnector();
        try {
            String SQL = "SELECT * FROM Episode e join Programe p on p.ProgrameID = e.ProgrameID WHERE SerieTitle = '" + ser.getSeriesTitle() + "'";
            resultSet = dbConnector.runSQL(SQL);

            while (resultSet.next()) {
                int programmeID = resultSet.getInt("ProgrameID");
                String duration = resultSet.getString("Duration");
                String season = resultSet.getString("Season");
                String title = resultSet.getString("Title");
                Episode ep = new Episode(programmeID, title, duration, season, ser);
                ser.addEpisode(ep);
                episodes.add(ep);
            }
            //System.out.println(ser);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }
        return episodes;
    }

    public List<Film> getFilms(){
        List<Film> films = new ArrayList<>();
        DBConnector dbConnector = new DBConnector();
        try {
            String SQL = "SELECT * FROM Film f join Programe p on p.ProgrameID = f.ProgrameID";
            resultSet = dbConnector.runSQL(SQL);

            while (resultSet.next()) {
                int programmeID = resultSet.getInt("ProgrameID");
                String title = resultSet.getString("Title");
                String duration = resultSet.getString("Duration");
                String genre = resultSet.getString("Genre");
                String language = resultSet.getString("Language");
                int pg = resultSet.getInt("PG");

                Film film = new Film(programmeID, title, duration, genre, language, pg);
                films.add(film);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }
        return films;
    }

    public List<Watched> getWatched(Profile prof){
        List<Watched> watched = new ArrayList<>();
        DBConnector dbConnector = new DBConnector();
        try {
            String SQL = "SELECT * FROM watched WHERE ProfileName = '" + prof.getProfileName() + "'";
            resultSet = dbConnector.runSQL(SQL);

            while (resultSet.next()) {
                int percentage = resultSet.getInt("Percentage");
                String profileName = resultSet.getString("ProfileName");
                int programmeID = resultSet.getInt("ProgrameID");

                Watched watch = new Watched(percentage, profileName, programmeID);
                prof.addWatched(watch);
                watched.add(watch);
            }
            //System.out.println(prof.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }
        return watched;
    }

    public List<Watched> getWatched(Film film){
        List<Watched> watched = new ArrayList<>();
        DBConnector dbConnector = new DBConnector();
        try {
            String SQL = "SELECT * FROM watched WHERE ProgrameID = '" + film.getProgrammeID() + "'";
            resultSet = dbConnector.runSQL(SQL);

            while (resultSet.next()) {
                int percentage = resultSet.getInt("Percentage");
                String profileName = resultSet.getString("ProfileName");
                int programmeID = resultSet.getInt("ProgrameID");

                Watched watch = new Watched(percentage, profileName, programmeID);
                film.addWatched(watch);
                watched.add(watch);
            }
            //System.out.println(film.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }
        return watched;
    }

    public List<Watched> getWatched(Episode episode){
        List<Watched> watched = new ArrayList<>();
        DBConnector dbConnector = new DBConnector();
        try {
            String SQL = "SELECT * FROM watched WHERE ProgrameID = '" + episode.getProgrammeID() + "'";
            resultSet = dbConnector.runSQL(SQL);

            while (resultSet.next()) {
                int percentage = resultSet.getInt("Percentage");
                String profileName = resultSet.getString("ProfileName");
                int programmeID = resultSet.getInt("ProgrameID");

                Watched watch = new Watched(percentage, profileName, programmeID);
                episode.addWatched(watch);
                watched.add(watch);
            }
            //System.out.println(episode.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }
        return watched;
    }
}
