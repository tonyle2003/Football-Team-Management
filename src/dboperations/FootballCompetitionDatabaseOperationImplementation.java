package dboperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconnection.DBConnection;
import users.FootballCompetition;
import users.dbinterfaces.FootballCompetitionDatabaseOperation;

public class FootballCompetitionDatabaseOperationImplementation implements FootballCompetitionDatabaseOperation {

    private Connection connection = DBConnection.getConnection();

    @Override
    public List<FootballCompetition> findAll() {
        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM football_competition");
            ResultSet resultSet = statement.executeQuery();
            List<FootballCompetition> footballCompetitions = new ArrayList<>();
            while (resultSet.next()) {
                footballCompetitions.add(new FootballCompetition(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("quantity_of_club"),
                        resultSet.getString("nationality")));
            }
            return footballCompetitions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}