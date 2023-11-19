package users;

import java.sql.Date;

import objects.Person;

public class Player extends Person {

    private double height;
    private double weight;
    private int number;

    public Player(String id, String name, String nationality, Date dateOfBirth, double height, double weight,
            int number) {
        super(id, name, nationality, dateOfBirth);
        this.height = height;
        this.weight = weight;
        this.number = number;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
