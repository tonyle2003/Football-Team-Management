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

    @Override
    public String findIdbyName(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM club WHERE name = ?");
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getString(1);
            } else {
                return "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
