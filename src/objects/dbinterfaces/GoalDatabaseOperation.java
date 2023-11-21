package objects.dbinterfaces;

import java.util.List;

import objects.Goal;

public interface GoalDatabaseOperation {
    
    int findGoalOfPlayerInSeason(String playerId, String season);
    int findGoalOfPlayer(String playerId);
}
