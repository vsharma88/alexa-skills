package my.vips.learning.aws.currency_info;

import java.sql.*;

/**
 * Created by vipinsharma on 06/04/18.
 */
public class CurrencyTableDAO {

    public static String getTheCurrencyNameForCountry(String countryName) throws SQLException {
        Connection conn = CurrencyTableDAO.getConnection();
        String result = null;
        if(conn == null){
            System.out.println("No connection taken !!");
            return null;
        }

        Statement stmt = conn.createStatement();
        String query = String.format("select currency_name from country where name = '%s'",countryName);
        ResultSet resultSet = stmt.executeQuery(query);

        if (resultSet.next()) {
           result =  resultSet.getObject(1).toString();
        }
        return result;
    }

    public static String getTheDialCodeForCountry(String countryName) throws SQLException {
        Connection conn = CurrencyTableDAO.getConnection();
        String result = null;
        if(conn == null){
            System.out.println("No connection taken !!");
            return null;
        }

        Statement stmt = conn.createStatement();
        String query = String.format("select dial_code from country where name = '%s'",countryName);
        ResultSet resultSet = stmt.executeQuery(query);

        if (resultSet.next()) {
            result =  resultSet.getObject(1).toString();
        }
        return result;
    }

    private static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://mysqldbinstance.c0ctovnetedb.us-east-1.rds.amazonaws.com:3306/test";
            String username = "vips";
            String password = "Iwdi2018";
            conn = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
