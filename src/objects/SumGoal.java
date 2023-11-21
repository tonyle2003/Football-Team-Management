package objects;

public class SumGoal {
    private int SumGoal;
    private String playerId;

    public SumGoal(int SumGoal, String playerId) {
        this.SumGoal = SumGoal;
        this.playerId = playerId;
    }

    public int getSumGoal() {
        return SumGoal;
    }

    public void setSumGoal(int sumGoal) {
        SumGoal = sumGoal;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    
}
