package users.dbinterfaces;

import java.util.List;

public interface ClubDatabaseOperation {
    List<String> findAll();
    String findIdbyName(String name); 
}
