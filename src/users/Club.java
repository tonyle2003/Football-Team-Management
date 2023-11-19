package users;

public class Club {

    private String id;
    private String name;
    private String stadium;

    public Club(String id, String name, String stadium) {
        this.id = id;
        this.name = name;
        this.stadium = stadium;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

}
