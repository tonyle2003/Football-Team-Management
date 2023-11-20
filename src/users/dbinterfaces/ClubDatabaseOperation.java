package users.dbinterfaces;

import java.util.List;

import users.Club;

public interface ClubDatabaseOperation {
    List<Club> findAll();
}
