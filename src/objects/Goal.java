package objects;

public class Goal {

    private String id;
    private GoalType goalType;

    public Goal(String id, GoalType goalType) {
        this.id = id;
        this.goalType = goalType;
    }

    public enum GoalType {
        PENALTY, LONG_RANGE, OG, NORMAL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GoalType getGoalType() {
        return goalType;
    }

    public void setGoalType(GoalType goalType) {
        this.goalType = goalType;
    }

}
