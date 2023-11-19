package users;

import java.sql.Date;

import objects.Person;

public class Manager extends Person {

    public Manager(String id, String name, String nationality, Date dateOfBirth) {
        super(id, name, nationality, dateOfBirth);
    }
    
}
