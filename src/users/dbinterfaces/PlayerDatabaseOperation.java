package users.dbinterfaces;

import java.util.List;
import java.util.Map;

import users.Player;

public interface PlayerDatabaseOperation {

    List<Player> findAll();
    Player findById(String id);
    List<String> getPlayerId();
    String findPlayerNameById(String playerId);
    Map<Player, Integer> findByAgeAndHeightAndNumberOfGoal(int age, double height, int goal);
    List<Player> findAllByClubId(String clubId);
    int deletePLayerFromClubByPlayerId(String playerId, String clubId);
    boolean insertPlayer(Player player);
    boolean updatePlayerById(Player player,String id);
}
