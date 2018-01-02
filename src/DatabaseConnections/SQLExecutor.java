package DatabaseConnections;

import UserData.Account;
import UserData.Profile;
import UserData.Watched;
import Watchables.Episode;
import Watchables.Film;
import Watchables.Series;

import java.sql.*;
import java.util.List;

public class SQLExecutor {
    private ResultSet resultSet = null;

    public void getAccounts(){
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
                System.out.println(acc.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }
    }

    public void getProfiles(Account account){
        DBConnector dbConnector = new DBConnector();
        try {
            String SQL = "SELECT * FROM Profile WHERE SubscriberNumber = " + account.getSubscriberNumber();
            resultSet = dbConnector.runSQL(SQL);

            while (resultSet.next()) {
                String profileName = resultSet.getString("ProfileName");
                String dateOfBirth = resultSet.getString("DateOfBirth");

                Profile prof = new Profile(profileName, dateOfBirth, account);
                System.out.println(prof.toString());
                account.addProfile(prof);
            }
            System.out.println(account.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }
    }

    public void getSeries(){
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
                System.out.println(ser.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }
    }

    public void getEpisodes(Series ser){
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
                System.out.println(ep);
            }
            System.out.println(ser);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }
    }

    public void getFilms(){
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
                System.out.println(film.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }
    }

    public void getWatched(Profile prof){
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
                System.out.println(watch.toString());
            }
            System.out.println(prof.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }
    }
}
