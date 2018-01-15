package DatabaseConnections;

import GUI.ComboBoxUpdater;
import GUI.ComboModel;
import GUI.SingleProfileTableUpdater;
import UserData.Account;
import UserData.Profile;
import UserData.Watched;
import Watchables.Episode;
import Watchables.Film;
import Watchables.Programme;
import Watchables.Series;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLExecutor {
    private ResultSet resultSet = null;


    public void insertAccount(String sub, String name, String street, String houseNumber, String postalCode, String city){
        DBConnector dbConnector = new DBConnector();
        try {
            String SQL = "INSERT INTO Account(SubscriberNumber, Name, StreetName, HouseNumber, PostalCode, City) VALUES(" + sub + ", '" + name + "', '" + street + "', '" + houseNumber + "', '" + postalCode + "', '" + city + "');";
            dbConnector.crudSQL(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }

        ComboBoxUpdater.updateAccountBoxes(getAccounts());
    }

    public void updateAccount(String oldSub, String sub, String name, String street, String houseNumber, String postalCode, String city){
        DBConnector dbConnector = new DBConnector();
        try {
            String SQL = "UPDATE Account SET SubscriberNumber =" + sub + ", Name ='" + name + "', StreetName ='" + street + "', HouseNumber ='" + houseNumber + "', PostalCode ='" + postalCode + "', City ='" + city + "' WHERE SubscriberNumber =" + oldSub + ";";
            dbConnector.crudSQL(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }

        ComboBoxUpdater.updateAccountBoxes(getAccounts());
    }

    public void deleteAccount(String sub){
        DBConnector dbConnector = new DBConnector();
        try {
            String SQL = "DELETE FROM Account WHERE SubscriberNumber ='" + sub + "';";
            dbConnector.crudSQL(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }

        ComboBoxUpdater.updateAccountBoxes(getAccounts());
    }

    public void insertWatched(String profileName, int programmeId, int percentage){
        DBConnector dbConnector = new DBConnector();
        try {
            String SQL = "INSERT INTO Watched VALUES('" +  profileName + "', " + programmeId + ", " + percentage +");";
            dbConnector.crudSQL(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }
    }

    public void editWatched(String profileName, int programmeId, int percentage){
        DBConnector dbConnector = new DBConnector();
        try {
            String SQL = "UPDATE Watched SET Percentage =" + percentage + " WHERE ProfileName = '" + profileName + "' AND ProgrameID = " + programmeId + ";";
            dbConnector.crudSQL(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }
    }

    public void deleteWatched(String profileName, int programmeId){
        DBConnector dbConnector = new DBConnector();
        try {
            String SQL = "DELETE FROM Watched WHERE ProfileName = '" + profileName + "' AND ProgrameID = " + programmeId;
            dbConnector.crudSQL(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }
    }

    public List<Account> getAccounts(){
        List<Account> accounts = new ArrayList<>();
        DBConnector dbConnector = new DBConnector();
        try {
            String SQL = "SELECT * FROM Account";
            resultSet = dbConnector.runSQL(SQL);

            while (resultSet.next()) {
                String subNo = resultSet.getString("SubscriberNumber");
                String name = resultSet.getString("Name");
                String streetName = resultSet.getString("StreetName");
                String postalCode = resultSet.getString("PostalCode");
                String houseNumber = resultSet.getString("HouseNumber");
                String city = resultSet.getString("City");

                Account acc = new Account(subNo,name,streetName,houseNumber,postalCode,city);
                accounts.add(acc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }
        return accounts;
    }

    public List<Account> getAccount(String sub) {
        List<Account> accounts = new ArrayList<>();
        DBConnector dbConnector = new DBConnector();
        try {
            resultSet = dbConnector.runSQL("SELECT * FROM Account WHERE SubscriberNumber = " + "'" + sub + "';");

            while (resultSet.next()) {
                String subNo = resultSet.getString("SubscriberNumber");
                String name = resultSet.getString("Name");
                String streetName = resultSet.getString("StreetName");
                String postalCode = resultSet.getString("PostalCode");
                String houseNumber = resultSet.getString("HouseNumber");
                String city = resultSet.getString("City");

                Account acc = new Account(subNo, name, streetName, houseNumber, postalCode, city);
                accounts.add(acc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return accounts;
    }

    public List<Account> getAccountsWithSingleProfile() {
        List<Account> accounts = new ArrayList<>();
        DBConnector dbConnector = new DBConnector();
        try {
            resultSet = dbConnector.runSQL("SELECT Account.SubscriberNumber, Name, StreetName, HouseNumber, PostalCode, City \n" +
                                            "FROM Account \n" +
                                            "JOIN Profile ON Profile.SubscriberNumber = Account.SubscriberNumber\n" +
                                            "GROUP BY Account.SubscriberNumber, Name, StreetName, HouseNumber, PostalCode, City\n" +
                                             "HAVING COUNT(ProfileName) = 1;");

            while (resultSet.next()) {
                String subNo = resultSet.getString("SubscriberNumber");
                String name = resultSet.getString("Name");
                String streetName = resultSet.getString("StreetName");
                String postalCode = resultSet.getString("PostalCode");
                String houseNumber = resultSet.getString("HouseNumber");
                String city = resultSet.getString("City");

                Account acc = new Account(subNo, name, streetName, houseNumber, postalCode, city);
                accounts.add(acc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return accounts;
    }

    public void insertProfile(JTextField profileName, JTextField dateOfBirth, String subscriberNumber){
        DBConnector dbConnector = new DBConnector();
        try {
            String SQL = "INSERT INTO Profile(SubscriberNumber, ProfileName, DateOfBirth) VALUES(" + subscriberNumber + ", '" + profileName.getText() + "', '" + dateOfBirth.getText() + "');";
            dbConnector.crudSQL(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }

        SingleProfileTableUpdater.updateSingleProfileModel(getAccountsWithSingleProfile());
    }

    public void updateProfile(String profileName, JTextField nameField, JTextField dateField){
        DBConnector dbConnector = new DBConnector();
        try {
            String SQL = "UPDATE Profile SET ProfileName = '" + nameField.getText() + "', DateOfBirth = '" + dateField.getText() + "' WHERE ProfileName = '" + profileName + "';";
            dbConnector.crudSQL(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }
    }

    public void deleteProfile(String profileName){
        DBConnector dbConnector = new DBConnector();
        try {
            String SQL = "DELETE FROM Profile WHERE ProfileName = '" + profileName + "';";
            dbConnector.crudSQL(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }

        SingleProfileTableUpdater.updateSingleProfileModel(getAccountsWithSingleProfile());
    }

    public List<Profile> getProfiles(Account account){
        List<Profile> profiles = new ArrayList<>();
        DBConnector dbConnector = new DBConnector();
        try {
            String SQL = "SELECT ProfileName, DateOfBirth \n" +
                        "FROM Profile \n" +
                        "JOIN Account ON Profile.SubscriberNumber = Account.SubscriberNumber \n" +
                        "WHERE Account.SubscriberNumber = " + account.getSubscriberNumber();
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

    public List<Film> getLongestFilmByRating(Object age){
        List<Film> films = new ArrayList<>();
        DBConnector dbConnector = new DBConnector();

        try {
            resultSet = dbConnector.runSQL("SELECT TOP 1 * FROM Film f JOIN Programe p on p.ProgrameID = f.ProgrameID WHERE PG <= " + age.toString() + " ORDER BY Duration DESC;");

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

    public String getProgrammeTitleByID(int ProgrammeID) {
        DBConnector dbConnector = new DBConnector();
        String title = "";
        try {
            String SQL = "SELECT Title FROM Programe JOIN Watched ON Programe.ProgrameID = Watched.ProgrameID WHERE Watched.ProgrameID = '" + ProgrammeID + "'";
            resultSet = dbConnector.runSQL(SQL);

            while (resultSet.next()) {
                title = resultSet.getString("Title");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return title;
    }

    public List<Watched> getWatched(Account acc, Series ser){
        List<Watched> watched = new ArrayList<>();
        DBConnector dbConnector = new DBConnector();
        try {
            String SQL = "SELECT w.Percentage, w.ProfileName, w.ProgrameID FROM Watched w\n" +
                    "join Profile p on p.ProfileName = w.ProfileName\n" +
                    "join Account a on a.SubscriberNumber = p.SubscriberNumber\n" +
                    "join Episode e on e.ProgrameID = w.ProgrameID\n" +
                    "join Series s on s.SerieTitle = e.SerieTitle\n" +
                    "where s.SerieTitle = '" +ser.getSeriesTitle() + "' AND a.SubscriberNumber = '" +acc.getSubscriberNumber() + "'";
            resultSet = dbConnector.runSQL(SQL);

            while (resultSet.next()) {
                int percentage = resultSet.getInt("Percentage");
                String profileName = resultSet.getString("ProfileName");
                int programmeID = resultSet.getInt("ProgrameID");

                Watched watch = new Watched(percentage, profileName, programmeID);
                watched.add(watch);

            }
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

    public List<String> getProfilesThatCompletedAFilm(String film) {
        List<String> profiles = new ArrayList<String>();
        DBConnector dbConnector = new DBConnector();
        try {
            resultSet = dbConnector.runSQL("SELECT Profile.SubscriberNumber, Profile.ProfileName, Profile.DateOfBirth FROM Profile JOIN Watched ON Profile.ProfileName = Watched.ProfileName JOIN Film ON Watched.ProgrameID = Film.ProgrameID JOIN Programe ON Film.ProgrameID = Programe.ProgrameID WHERE Percentage = 100 AND Programe.Title = " + "'" + film + "';");
            while (resultSet.next()) {
                String profileName = resultSet.getString("ProfileName");
                profiles.add(profileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return profiles;
    }
}

