package users.dbinterfaces;

import java.util.List;

import users.Player;

public interface PlayerDatabaseOperation {

    List<Player> findAll();
    String findPlayerNameById(String playerId);
}
