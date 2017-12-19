package DatabaseConnections;

import java.sql.*;

public class SQLExecutor {

    public static void getAccounts(){
        ResultSet resultSet = null;

        try {
            DBConnector dbConnector = new DBConnector();
            String SQL = "SELECT * FROM Account";
            resultSet = dbConnector.runSQL(SQL);

            System.out.print(String.format("| %7s | %-32s | %-24s | %-8s | %-4s| %-18s|\n", " ", " ", " ", " ", " ", " ").replace(" ", "-"));

            while (resultSet.next()) {
                int subNo = resultSet.getInt("SubscriberNumber");
                String name = resultSet.getString("Name");
                String streetName = resultSet.getString("StreetName");
                String postalCode = resultSet.getString("PostalCode");
                String houseNumber = resultSet.getString("HouseNumber");
                String city = resultSet.getString("City");

                System.out.format("| %7s | %-32s | %-24s | %-8s | %-4s| %-18s| \n", subNo, name, streetName, postalCode, houseNumber, city);
            }
            System.out.println(String.format("| %7s | %-32s | %-24s | %-8s | %-4s| %-18s|\n", " ", " ", " ", " ", " ", " ").replace(" ", "-"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {e.printStackTrace();}
        }
    }
}
