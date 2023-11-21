package objects;

import java.time.LocalDate;

public class Person {

    private String id;
    private String name;
    private String nationality;
    private LocalDate dateOfBirth;
    private int sumOfGoal;

    public Person(String id, String name, String nationality, LocalDate dateOfBirth2) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth2;
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getSumOfGoal() {
        return sumOfGoal;
    }

    public void setSumOfGoal(int sumOfGoal) {
        this.sumOfGoal = sumOfGoal;
    }

}
