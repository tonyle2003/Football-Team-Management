package users.dbinterfaces;

import java.util.List;

import users.FootballCompetition;

public interface FootballCompetitionDatabaseOperation {
    List<FootballCompetition> findAll();
}
