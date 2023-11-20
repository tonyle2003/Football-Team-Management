package dboperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconnection.DBConnection;
import users.Player;
import users.dbinterfaces.PlayerDatabaseOperation;

public class PlayerDatabaseOperationImplementation implements PlayerDatabaseOperation {

    private Connection connection;

    public PlayerDatabaseOperationImplementation(Connection connection) {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public List<Player> findAll() {
        List<Player> players = new ArrayList<>();
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                "SELECT * FROM player JOINS person ON person.id = player.id");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                players.add(new Player(
                    result.getString("person.id"),
                    result.getString("person.name"),
                    result.getString("person.nationality"),
                    result.getDate("person.date_of_birth"),
                    result.getDouble("player.height"),
                    result.getDouble("player.weight"),
                    result.getInt("player.number")));
            }
            return players;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    @Override
    public String findPlayerNameById(String playerId) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "SELECT name from person WHERE person.id=?"  
            );
            statement.setString(1, playerId);
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
