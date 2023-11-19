package objects.dbinterfaces;

import java.sql.Date;

public interface GoalDatabaseOperation {
    
    int findGoalOfPlayerInSeason(String playerId, Date date);
    int findGoalOfPlayer(String playerId);
}
