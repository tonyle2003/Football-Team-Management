package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dboperations.ClubDatabaseOperationImplementation;
import dboperations.FootballCompetitionDatabaseOperationImplementation;
import dboperations.GoalDatabaseOperationImplementation;
import dboperations.PlayerDatabaseOperationImplementation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.SumGoal;
import users.FootballCompetition;
import users.Player;



public class Test {

    public static void main(String[] args) {
        ObservableList<Player> playerlist;
        String season = "Premier League";
        ObservableList<FootballCompetition> competitionlist;
        GoalDatabaseOperationImplementation goalOp = new GoalDatabaseOperationImplementation();
        PlayerDatabaseOperationImplementation playerOp = new PlayerDatabaseOperationImplementation(null);
        playerlist = FXCollections.observableArrayList(playerOp.findAll());
        FootballCompetitionDatabaseOperationImplementation regOp = new FootballCompetitionDatabaseOperationImplementation();
        competitionlist = FXCollections.observableArrayList(regOp.findAll());
        for(int i = 0; i < competitionlist.size(); i++){
            if(competitionlist.get(i).getName().equals(season)){
                season = competitionlist.get(i).getId();
            }
        }
        for (int i = 0; i < playerlist.size(); i++){
            int goal = goalOp.findGoalOfPlayerInSeason(playerlist.get(i).getId(),season );
            playerlist.get(i).setSumOfGoal(goal);
        }

        for (int i = 0; i < playerlist.size(); i++){
            System.out.println(playerlist.get(i).getSumOfGoal() + " " + playerlist.get(i).getName());
        }
    }
}
