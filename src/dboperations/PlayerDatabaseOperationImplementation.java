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
                    "SELECT person.id, person.name, person.nationality, person.date_of_birth, player.height, player.weight, player.number FROM player JOIN person ON person.id = player.id");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                players.add(new Player(
                        result.getString("person.id"),
                        result.getString("person.name"),
                        result.getString("person.nationality"),
                        result.getDate("person.date_of_birth").toLocalDate(),
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
                    "SELECT * FROM person JOIN player ON person.id = player.id WHERE player.height >= ? AND person.date_of_birth <= ?");
            statement.setDouble(1, height);
            statement.setDate(2, new Date(calendar.getTime().getTime()));

            ResultSet resultSet = statement.executeQuery();
            GoalDatabaseOperation goalDOB = new GoalDatabaseOperationImplementation();
            Map<Player, Integer> result = new HashMap<>();
            while (resultSet.next()) {
                Player current = new Player(
                        resultSet.getString("person.id"),
                        resultSet.getString("person.name"),
                        resultSet.getString("person.nationality"),
                        resultSet.getDate("person.date_of_birth").toLocalDate(),
                        resultSet.getDouble("player.height"),
                        resultSet.getDouble("player.weight"),
                        resultSet.getInt("player.number"));
                int scored = goalDOB.findGoalOfPlayer(resultSet.getString("person.id"));
                if (scored >= goal) {
                    current.setSumOfGoal(scored);
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
                    "SELECT * FROM person JOIN player ON person.id = player.id WHERE person.id=?");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Player(
                        resultSet.getString("person.id"),
                        resultSet.getString("person.name"),
                        resultSet.getString("person.nationality"),
                        resultSet.getDate("person.date_of_birth").toLocalDate(),
                        resultSet.getDouble("player.height"),
                        resultSet.getDouble("player.weight"),
                        resultSet.getInt("player.number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Player> findAllByClubId(String clubId) {
        try {
            PreparedStatement statement = this.connection
                    .prepareStatement(
                            "SELECT * FROM player JOIN person ON person.id = player.id WHERE player.id_club=?");
            statement.setString(1, clubId);
            List<Player> players = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                players.add(new Player(resultSet.getString("person.id"),
                        resultSet.getString("person.name"),
                        resultSet.getString("person.nationality"),
                        resultSet.getDate("person.date_of_birth").toLocalDate(),
                        resultSet.getDouble("player.height"),
                        resultSet.getDouble("player.weight"),
                        resultSet.getInt("player.number")));
            }
            return players;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deletePLayerFromClubByPlayerId(String playerId, String clubId) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "UPDATE player SET id_club=null WHERE id=? AND id_club=?");
            statement.setString(1, playerId);
            statement.setString(2, clubId);
            int rowEffected = statement.executeUpdate();
            if (rowEffected == 1) {
                connection.commit();
                return 1;
            } else {
                connection.rollback();
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean insertPlayer(Player player) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "INSERT INTO football_management.person VALUES(?,?,?,?)");
            statement.setString(1, player.getId());
            statement.setString(2, player.getName());
            statement.setString(3, player.getNationality());
            statement.setDate(4, Date.valueOf(player.getDateOfBirth()));
            PreparedStatement statement2 = this.connection.prepareStatement(
                    "INSERT INTO football_management.player(id, height, weight, number) VALUES(?,?,?,?) ");
            statement2.setString(1, player.getId());
            statement2.setDouble(2, player.getHeight());
            statement2.setDouble(3, player.getWeight());
            statement2.setInt(4, player.getNumber());

            int rowEffected = statement.executeUpdate();
            int rowEffected2 = statement2.executeUpdate();
            if (rowEffected == 1 && rowEffected2 == 1) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePlayerById(Player player, String id) {
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "UPDATE person JOIN player ON player.id = person.id " +
                            " SET person.name=?, person.nationality=?, person.date_of_birth=?, player.height=?, player.weight=?, player.number=? "
                            +
                            " WHERE person.id=? ");
            statement.setString(1, player.getName());
            statement.setString(2, player.getNationality());
            statement.setDate(3, Date.valueOf(player.getDateOfBirth()));
            statement.setDouble(4, player.getHeight());
            statement.setDouble(5, player.getWeight());
            statement.setInt(6, player.getNumber());
            statement.setString(7, player.getId());
            int rowEffected = statement.executeUpdate();
            if (rowEffected == 2) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<String> getPlayerId() {
        List<String> players = new ArrayList<>();
        try {
            PreparedStatement statement = this.connection.prepareStatement(
                    "SELECT person.id, person.name, person.nationality, person.date_of_birth, player.height, player.weight, player.number FROM player JOIN person ON person.id = player.id");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                players.add(result.getString("person.id"));
            }
            return players;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    @Override
    public List<Player> findPlayersAndGoalsInSeasonOrderByGoal(String season) {
        String query = """
                SELECT person.id, person.name,person.nationality,person.date_of_birth,player.height,player.weight,player.number, COUNT(goal.id) AS total_goals
                FROM goal
                JOIN player ON goal.id_player = player.id
                JOIN person ON person.id = player.id
                JOIN _match ON goal.id_match = _match.id
                JOIN football_competition ON _match.id_competition = football_competition.id
                WHERE
                    goal.goal_type != 'OG' and football_competition.id = ?
                GROUP BY player.id, football_competition.id
                ORDER BY
                    total_goals DESC;
                """;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, season);
            ResultSet resultSet = statement.executeQuery();
            List<Player> players = new ArrayList<Player>();
            while (resultSet.next()) {
                Player current = new Player(resultSet.getString("person.id"),
                        resultSet.getString("person.name"),
                        resultSet.getString("person.nationality"),
                        resultSet.getDate("person.date_of_birth").toLocalDate(),
                        resultSet.getDouble("player.height"),
                        resultSet.getDouble("player.weight"),
                        resultSet.getInt("player.number"));
                current.setSumOfGoal(resultSet.getInt("total_goals"));
                players.add(current);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
