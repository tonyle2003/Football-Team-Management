package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/football_management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "minhdeptrai";

    public static Connection getConnection() {
        Connection connection = null;
        //System.out.println("Connecting to a selected database...");
        // Open a connection
        try{
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        try{
            if (connection != null){
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void printInfo(Connection connection){
        try{
            if(connection != null){
                System.out.println("Connect successfully!"
                    + "\nDatabase name: " + connection.getCatalog());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}