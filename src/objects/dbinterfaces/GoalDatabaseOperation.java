package objects.dbinterfaces;

import java.util.Map;

public interface GoalDatabaseOperation {
    
    int findGoalOfPlayerInSeason(String playerId, String season);
    int findGoalOfPlayer(String playerId);
}
