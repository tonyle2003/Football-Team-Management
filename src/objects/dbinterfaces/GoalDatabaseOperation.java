package objects.dbinterfaces;

public interface GoalDatabaseOperation {
    
    int findGoalOfPlayerInSeason(String playerId, String season);
    int findGoalOfPlayer(String playerId);
}
