package users;

public class FootballCompetition {

    private String id;
    private String name;
    private int quantityOfClub;
    private String nationality;

    public FootballCompetition(String id, String name, int quantityOfClub, String nationality) {
        this.id = id;
        this.name = name;
        this.quantityOfClub = quantityOfClub;
        this.nationality = nationality;
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

    public int getQuantityOfClub() {
        return quantityOfClub;
    }

    public void setQuantityOfClub(int quantityOfClub) {
        this.quantityOfClub = quantityOfClub;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

}
