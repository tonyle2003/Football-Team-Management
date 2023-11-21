package dboperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconnection.DBConnection;
import users.dbinterfaces.ClubDatabaseOperation;

public class ClubDatabaseOperationImplementation implements ClubDatabaseOperation {

    private Connection connection;

    public ClubDatabaseOperationImplementation() {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public List<String> findAll() {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "SELECT * FROM club");
            ResultSet resultSet = statement.executeQuery();
            List<String> clubs = new ArrayList<>();
            while (resultSet.next()) {
                clubs.add(resultSet.getString("name"));
            }
            return clubs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
