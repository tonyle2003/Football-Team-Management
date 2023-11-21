package users;

import java.time.LocalDate;

import objects.Person;

public class Player extends Person {

    private double height;
    private double weight;
    private int number;
    private int sumOfGoal;

    public Player(String id, String name, String nationality, LocalDate dateOfBirth, double height, double weight,
            int number) {
        super(id, name, nationality, dateOfBirth);
        this.height = height;
        this.weight = weight;
        this.number = number;
    }

    public int getSumOfGoal() {
        return sumOfGoal;
    }

    public void setSumOfGoal(int sumOfGoal) {
        this.sumOfGoal = sumOfGoal;
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
