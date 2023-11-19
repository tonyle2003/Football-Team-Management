package objects.dbinterfaces;

import java.util.List;

import objects.Goal;

public interface GoalDatabaseOperation {
    
    List<Goal> findAll();
}
