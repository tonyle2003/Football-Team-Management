package dboperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import dbconnection.DBConnection;
import objects.Goal;
import objects.dbinterfaces.GoalDatabaseOperation;

public class GoalDatabaseOperationImplementation implements GoalDatabaseOperation {

    private Connection connection;

    public GoalDatabaseOperationImplementation() {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public int findGoalOfPlayer(String playerId) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                "SELECT COUNT(id) as scored FROM goal WHERE id_player=? AND goal_type != 'OG'");
            statement.setString(1, playerId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int findGoalOfPlayerInSeason(String playerId, String season) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                "SELECT COUNT(goal.id) as scored FROM football_competition" +
                "JOIN match ON football_competition.id = match.id_competition" + 
                "JOIN goal ON goal.id_match = match.id" +
                "JOIN player ON player.id = goal.id_player" + 
                "WHERE goal.id_player=? AND football_competition.id=? AND goal.goal_type != 'OG'" +
                "GROUP BY football_competition.id"
            );
            statement.setString(1, playerId);
            statement.setString(2, season);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
