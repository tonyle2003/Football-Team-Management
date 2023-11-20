package dboperations;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dbconnection.DBConnection;
import objects.dbinterfaces.GoalDatabaseOperation;
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
                    "SELECT name from person WHERE person.id=?");
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

    @Override
    public Map<Player, Integer> findByAgeAndHeightAndNumberOfGoal(int age, double height, int goal) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -age);

            PreparedStatement statement = this.connection.prepareStatement(
                    "SELECT * FROM person JOINS player ON person.id=player.id" +
                            "WHERE player.height >= ? AND person.date_of_birth <= ?");
            statement.setDouble(1, height);
            statement.setDate(2, new Date(calendar.getTime().getTime()));

            ResultSet resultSet = statement.executeQuery();
            GoalDatabaseOperation goalDOB = new GoalDatabaseOperationImplementation(this.connection);
            Map<Player, Integer> result = new HashMap<>();
            while (resultSet.next()) {
                Player current = new Player(
                    resultSet.getString("person.id"),
                    resultSet.getString("person.name"),
                    resultSet.getString("person.nationality"),
                    resultSet.getDate("person.date_of_birth"),
                    resultSet.getDouble("player.height"), 
                    resultSet.getDouble("player.weight"),
                    resultSet.getInt("player.number"));
                int scored = goalDOB.findGoalOfPlayer(resultSet.getString("person.id"));
                if (scored >= goal) {
                    result.put(current, scored);
                }
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Player findById(String id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "SELECT * FROM person JOINS player ON person.id = player.id WHERE person.id=?");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Player(
                        resultSet.getString("person.id"),
                        resultSet.getString("person.name"),
                        resultSet.getString("person.nationality"),
                        resultSet.getDate("person.date_of_birth"),
                        resultSet.getDouble("player.height"),
                        resultSet.getDouble("player.weight"),
                        resultSet.getInt("player.number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
