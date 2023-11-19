package dboperations;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnection.DBConnection;
import objects.dbinterfaces.GoalDatabaseOperation;

public class GoalDatabaseOperationImplementation implements GoalDatabaseOperation {

    private Connection connection;

    public GoalDatabaseOperationImplementation(Connection connection) {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public int findGoalOfPlayer(String playerId) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                "SELECT COUNT(id) FROM goal WHERE id_player=" + playerId + " AND goal_type != 'OG'");
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int findGoalOfPlayerInSeason(String playerId, Date date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findGoalOfPlayerInSeason'");
    }

}
