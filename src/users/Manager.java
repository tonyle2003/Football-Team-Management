package users;

import java.time.LocalDate;

import objects.Person;

public class Manager extends Person {

    public Manager(String id, String name, String nationality, LocalDate dateOfBirth) {
        super(id, name, nationality, dateOfBirth);
    }
    
}
