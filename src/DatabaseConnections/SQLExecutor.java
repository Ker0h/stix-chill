package DatabaseConnections;

import UserData.Account;
import UserData.Profile;
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
}
