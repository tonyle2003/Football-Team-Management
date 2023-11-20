package users.dbinterfaces;

import java.util.List;
import java.util.Map;

import users.Player;

public interface PlayerDatabaseOperation {

    List<Player> findAll();
    Player findById(String id);
    String findPlayerNameById(String playerId);
    Map<Player, Integer> findByAgeAndHeightAndNumberOfGoal(int age, double height, int goal);
}
