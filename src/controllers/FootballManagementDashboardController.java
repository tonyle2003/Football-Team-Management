package controllers;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import dboperations.ClubDatabaseOperationImplementation;
import dboperations.FootballCompetitionDatabaseOperationImplementation;
import dboperations.GoalDatabaseOperationImplementation;
import dboperations.PlayerDatabaseOperationImplementation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import objects.SumGoal;
import users.FootballCompetition;
import users.Player;

public class FootballManagementDashboardController implements Initializable {

    @FXML
    private AnchorPane UpdateAnchorPane;

    @FXML
    private AnchorPane ReportAnchorPane;

    @FXML
    private AnchorPane HomePagePane;

    @FXML
    private AnchorPane UpdateImportPane;

    @FXML
    private AnchorPane UpdatePlayersPane;

    @FXML
    private AnchorPane RemoveplayersPane;

    /*
     * Import new players
     */
    
    @FXML
    private TextField IdimportTextField;

    @FXML
    private TextField NameImportTextField;

    @FXML
    private TextField NationalityTextField;

    @FXML
    private DatePicker DatePickerImport;

    @FXML
    private TextField HeightImportTextField;

    @FXML
    private TextField WeightImportTextField;

    @FXML
    private TextField NumberImportTextField;

    @FXML
    private Label ImportLabel;

    /*
     * Update players
     */

    @FXML
    private ComboBox<String> updatePlayerComboBox;
     
    @FXML
    private Label UpdatePlayersIdLabel;

    @FXML
    private TextField UpdatePlayersNameTextField;

    @FXML
    private TextField UpdatePlayersNationalityTextField;

    @FXML
    private DatePicker UpdatePlayersDatePicker;

    @FXML
    private ComboBox<String> UpdatePlayersIDclubCombobox;

    @FXML
    private TextField UpdatePlayersHeightTextField;

    @FXML
    private TextField UpdatePlayersWeightTextField;

    @FXML
    private TextField UpdatePlayersNumberTextField;

    private ObservableList<String> playeridlist;

    @FXML
    private Label UpdateLabel;

    /*
     * Remove
     */

    private ObservableList<Player> playerlist;

    @FXML
    private TableView<Player> ListPlayerView;

    @FXML
    private Label myRemoveLabel;

    @FXML
    private ComboBox<String> ClubIdComboBox;

    private String clubId;

    /*
     * Report
     */
    
    @FXML
    private AnchorPane Report1Pane;

    @FXML
    private AnchorPane Report2Pane;

    @FXML
    private TableView<Player> Report1TableView;

    @FXML
    private TableView<Player> Report2TableView;

    @FXML
    private TextField AgeLabel;

    @FXML
    private TextField HeightLabel;

    @FXML
    private TextField GoalsLabel;

    private ObservableList<SumGoal> goalslist;

    @FXML
    private ComboBox<String> PickCompetitionCombobox;

    private ObservableList<FootballCompetition> competitionlist;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navigateToDashboard(null);
    }

    /*
     * Update
     */

    @FXML
    private void navigateToDashboard(ActionEvent actionEvent){
        UpdateAnchorPane.setVisible(false);
        ReportAnchorPane.setVisible(false);
        HomePagePane.setVisible(true);
    }

    @FXML
    private void handleUpdateButton(ActionEvent actionEvent) {
        UpdateAnchorPane.setVisible(true);
        ReportAnchorPane.setVisible(false);
        UpdateImportPane.setVisible(false);
        UpdatePlayersPane.setVisible(false);
        RemoveplayersPane.setVisible(false);
        ImportLabel.setText(null);
        UpdateLabel.setText(null);
        myRemoveLabel.setText(null);
    }

    @FXML
    private void handleUpdateImport(ActionEvent actionEvent){
        UpdateImportPane.setVisible(true);
        UpdatePlayersPane.setVisible(false);
        RemoveplayersPane.setVisible(false);
        HomePagePane.setVisible(false);
    }
    
    @FXML
    private void handleUpdatePlayers(ActionEvent actionEvent){
        UpdateImportPane.setVisible(false);
        UpdatePlayersPane.setVisible(true);
        RemoveplayersPane.setVisible(false);
        HomePagePane.setVisible(false);
        PlayerDatabaseOperationImplementation regOp = new PlayerDatabaseOperationImplementation(null);
        playeridlist = FXCollections.observableArrayList(regOp.getPlayerId());
        updatePlayerComboBox.setItems(playeridlist);
    }

    @FXML
    private void handlechooseplayer(ActionEvent actionEvent){
        String playerid = updatePlayerComboBox.getSelectionModel().getSelectedItem();
        PlayerDatabaseOperationImplementation regOp = new PlayerDatabaseOperationImplementation(null);
        Player player = regOp.findById(playerid);
        System.out.println(player.getId());
        UpdatePlayersIdLabel.setText(playerid);
        UpdatePlayersNameTextField.setText(player.getName());
        UpdatePlayersNationalityTextField.setText(player.getNationality());
        UpdatePlayersDatePicker.setValue(player.getDateOfBirth());
        UpdatePlayersHeightTextField.setText(String.valueOf(player.getHeight()));
        UpdatePlayersWeightTextField.setText(String.valueOf(player.getWeight()));
        UpdatePlayersNumberTextField.setText(String.valueOf(player.getNumber()));
    }

    @FXML
    private void handleUpdateRemove(ActionEvent actionEvent){
        ListPlayerView.getColumns().clear();
        UpdateImportPane.setVisible(false);
        UpdatePlayersPane.setVisible(false);
        RemoveplayersPane.setVisible(true);
        HomePagePane.setVisible(false);
        ClubDatabaseOperationImplementation reOp = new ClubDatabaseOperationImplementation();
        ObservableList<String> clublist = FXCollections.observableArrayList(reOp.findAll());
        ClubIdComboBox.setItems(clublist);
        PlayerDatabaseOperationImplementation regOp = new PlayerDatabaseOperationImplementation(null); 
        playerlist = FXCollections.observableArrayList(regOp.findAll());
        populateplayerlist();
    }

    @FXML
    private void handleImportnewPlayerButton(ActionEvent actionEvent){
        String Id = IdimportTextField.getText();
        String Name = NameImportTextField.getText();
        String Nationality = NationalityTextField.getText();
        LocalDate dob = DatePickerImport.getValue();
        Double Height = Double.parseDouble(HeightImportTextField.getText());
        Double Weight = Double.parseDouble(WeightImportTextField.getText());
        Integer Number = Integer.parseInt(NumberImportTextField.getText());

        Player player = new Player(Id, Name, Nationality, dob, Height, Weight,Number); 

        PlayerDatabaseOperationImplementation regOp = new PlayerDatabaseOperationImplementation(null);
        boolean insertstatus = regOp.insertPlayer(player);

        if(insertstatus){
            ImportLabel.setText("Player " + Name + " inserted successfully");
        }else{
            ImportLabel.setText("Failed to insert player " + Name);
        }

    }
    
    @FXML
    private void handlResetnewPlayerButton(ActionEvent actionEvent){
        IdimportTextField.clear();
        NameImportTextField.clear();
        NationalityTextField.clear();
        DatePickerImport.setValue(null);
        HeightImportTextField.clear();
        WeightImportTextField.clear();
        WeightImportTextField.clear();
        NumberImportTextField.clear(); 
    }

    @FXML
    private void handleUpdatePlayerButton(ActionEvent actionEvent){
        String playerid = updatePlayerComboBox.getSelectionModel().getSelectedItem();
        String Name = UpdatePlayersNameTextField.getText();
        String Nationality = UpdatePlayersNationalityTextField.getText();
        LocalDate dob = UpdatePlayersDatePicker.getValue();
        Double Height = Double.parseDouble(UpdatePlayersHeightTextField.getText());
        Double Weight = Double.parseDouble(UpdatePlayersWeightTextField.getText());
        Integer Number = Integer.parseInt(UpdatePlayersNumberTextField.getText());

        Player player = new Player(playerid, Name, Nationality, dob, Height, Weight,Number); 
        
        PlayerDatabaseOperationImplementation regOp = new PlayerDatabaseOperationImplementation(null);
        boolean updatestatus = regOp.updatePlayerById(player,playerid);

        if(updatestatus){
            UpdateLabel.setText("Player " + Name + " updated successfully");
        }else{
            UpdateLabel.setText("Failed to update player " + Name);
        }


    }

    @FXML
    private void handlResetPlayerButton(ActionEvent actionEvent){
        String playerid = updatePlayerComboBox.getSelectionModel().getSelectedItem();
        PlayerDatabaseOperationImplementation regOp = new PlayerDatabaseOperationImplementation(null);
        Player player = regOp.findById(playerid);
        UpdatePlayersIdLabel.setText(player.getId());
        UpdatePlayersNameTextField.setText(player.getName());
        UpdatePlayersNationalityTextField.setText(player.getNationality());
        UpdatePlayersDatePicker.setValue(player.getDateOfBirth());
        UpdatePlayersHeightTextField.setText(String.valueOf(player.getHeight()));
        UpdatePlayersWeightTextField.setText(String.valueOf(player.getWeight()));
        UpdatePlayersNumberTextField.setText(String.valueOf(player.getNumber()));
    }

    @FXML
    private void handleRemovalReload(ActionEvent actionEvent){
        ClubIdComboBox.getSelectionModel().clearSelection();
        ListPlayerView.getColumns().clear();
        PlayerDatabaseOperationImplementation regOp = new PlayerDatabaseOperationImplementation(null); 
        playerlist = FXCollections.observableArrayList(regOp.findAll());
        populateplayerlist();
    }

    @FXML
    private void handleRemovalplayers(ActionEvent actionEvent){
        myRemoveLabel.setText(null);

        Player playerpicked = ListPlayerView.getSelectionModel().getSelectedItem();

        if(playerpicked == null){
            myRemoveLabel.setText("Please select a player to remove");
            return;
        }

        PlayerDatabaseOperationImplementation regOp = new PlayerDatabaseOperationImplementation(null);
        int deletestatus = regOp.deletePLayerFromClubByPlayerId(playerpicked.getId(),clubId);
        if(deletestatus == 1){
            myRemoveLabel.setText("Player removed successfully");
            playerlist.remove(playerpicked);
            populateplayerlist();
        }else{if(deletestatus == 0){
            myRemoveLabel.setText("Player not in club to remove");
        }else{myRemoveLabel.setText("Player not removed");}}

    }

    @FXML
    private void pickedClubComboBox(ActionEvent actionEvent){
        ListPlayerView.getColumns().clear();
        String clubName = ClubIdComboBox.getSelectionModel().getSelectedItem();
        System.out.println(clubId);
        PlayerDatabaseOperationImplementation regOp = new PlayerDatabaseOperationImplementation(null);
        ClubDatabaseOperationImplementation reOp = new ClubDatabaseOperationImplementation();
        clubId = reOp.findIdbyName(clubName);
        playerlist = FXCollections.observableArrayList(regOp.findAllByClubId(clubId));
        populateplayerlist();
    }


    void populateplayerlist(){
        TableColumn<Player, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setMinWidth(40);
        idColumn.setStyle("-fx-alignment: center;");

        TableColumn<Player, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setMinWidth(180);
        nameColumn.setStyle("-fx-alignment: center;");

        TableColumn<Player, String> nationalityColumn = new TableColumn<>("Nationality");
        nationalityColumn.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        nationalityColumn.setMinWidth(140);
        nameColumn.setStyle("-fx-alignment: center;");

        TableColumn<Player, Date> dobColumn = new TableColumn<>("Date of birth");
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        dobColumn.setMinWidth(140);
        nameColumn.setStyle("-fx-alignment: center;");

        TableColumn<Player, Double> heightColumn = new TableColumn<>("Height");
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));
        heightColumn.setMinWidth(100);
        nameColumn.setStyle("-fx-alignment: center;");

        TableColumn<Player, Double> weightColumn = new TableColumn<>("Weight");
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        weightColumn.setMinWidth(100);
        nameColumn.setStyle("-fx-alignment: center;");

        TableColumn<Player, Integer> numberColumn = new TableColumn<>("Number");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        numberColumn.setMinWidth(100);
        nameColumn.setStyle("-fx-alignment: center;");

        ListPlayerView.setItems(playerlist);
        ListPlayerView.setStyle("-fx-font-size: 18px");
        ListPlayerView.getColumns().addAll(idColumn,nameColumn,nationalityColumn,dobColumn,heightColumn,weightColumn,numberColumn);

        
    }

    /*
     * Report
     */

    @FXML
    private void handleReportButton(ActionEvent actionEvent){
        UpdateAnchorPane.setVisible(false);
        ReportAnchorPane.setVisible(true);
        Report1Pane.setVisible(false);
        Report2Pane.setVisible(false);
        //Report3Pane.setVisible(false);
    }

    @FXML
    private void handleReport1Button(ActionEvent actionEvent){
        Report1Pane.setVisible(true);
        Report2Pane.setVisible(false);
        //Report3Pane.setVisible(false);
        Report1TableView.getColumns().clear();
    }

    @FXML
    private void handleFilterAction(ActionEvent actionEvent){
        int age = Integer.parseInt(AgeLabel.getText());
        double height = Double.parseDouble(HeightLabel.getText());
        int goals = Integer.parseInt(GoalsLabel.getText());
        Report1TableView.getColumns().clear();
        PlayerDatabaseOperationImplementation regOp = new PlayerDatabaseOperationImplementation(null);
        Map<Player,Integer> playermap = regOp.findByAgeAndHeightAndNumberOfGoal(age, height, goals);
        Set<Player> players = playermap.keySet();
        playerlist = FXCollections.observableArrayList(players);
        populateplayerlistbygoal();
    }

    @FXML
    private void handleReport2Button(ActionEvent actionEvent){
        Report1Pane.setVisible(false);
        Report2Pane.setVisible(true);
        //Report3Pane.setVisible(false);
        Report2TableView.getColumns().clear();
        FootballCompetitionDatabaseOperationImplementation regOp = new FootballCompetitionDatabaseOperationImplementation();
        competitionlist = FXCollections.observableArrayList(regOp.findAll());
        ObservableList<String> competitionlistName = FXCollections.observableArrayList();
        for(int i = 0; i < competitionlist.size(); i++){
            competitionlistName.add(competitionlist.get(i).getName());
        }
        PickCompetitionCombobox.setItems(competitionlistName);

    }

    void populateplayerlistbygoal(){
        TableColumn<Player, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setMinWidth(40);
        idColumn.setStyle("-fx-alignment: center;");

        TableColumn<Player, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setMinWidth(180);
        nameColumn.setStyle("-fx-alignment: center;");

        TableColumn<Player, String> nationalityColumn = new TableColumn<>("Nationality");
        nationalityColumn.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        nationalityColumn.setMinWidth(110);
        nameColumn.setStyle("-fx-alignment: center;");

        TableColumn<Player, Date> dobColumn = new TableColumn<>("Date of birth");
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        dobColumn.setMinWidth(120);
        nameColumn.setStyle("-fx-alignment: center;");

        TableColumn<Player, Double> heightColumn = new TableColumn<>("Height");
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));
        heightColumn.setMinWidth(70);
        nameColumn.setStyle("-fx-alignment: center;");

        TableColumn<Player, Double> weightColumn = new TableColumn<>("Weight");
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        weightColumn.setMinWidth(70);
        nameColumn.setStyle("-fx-alignment: center;");

        TableColumn<Player, Integer> numberColumn = new TableColumn<>("Number");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        numberColumn.setMinWidth(70);
        nameColumn.setStyle("-fx-alignment: center;");

        TableColumn<Player, Integer> goalColumn = new TableColumn<>("Number of goals");
        goalColumn.setCellValueFactory(new PropertyValueFactory<>("sumOfGoal"));
        goalColumn.setMinWidth(150);
        goalColumn.setStyle("-fx-alignment: center;");

        Report1TableView.setItems(playerlist);
        Report1TableView.setStyle("-fx-font-size: 16px");
        Report1TableView.getColumns().addAll(idColumn,nameColumn,nationalityColumn,dobColumn,heightColumn,weightColumn,numberColumn,goalColumn);
    }

    @FXML
    private void HandlePickCompetitionCombobox(ActionEvent actionEvent){
        Report2TableView.getColumns().clear();
        String season = PickCompetitionCombobox.getSelectionModel().getSelectedItem();
        String id = "";
        for(int i = 0; i < competitionlist.size(); i++){
            if(competitionlist.get(i).getName().equals(season)){
                id = competitionlist.get(i).getId();
                break;
            }
        }
        System.out.println(id);
        PlayerDatabaseOperationImplementation playerOp = new PlayerDatabaseOperationImplementation(null);
        System.out.println(playerOp.findPlayersAndGoalsInSeasonOrderByGoal(id));
        playerlist = FXCollections.observableArrayList(playerOp.findPlayersAndGoalsInSeasonOrderByGoal(id)); 
        for(int i = 0; i < playerlist.size(); i++)
            System.out.println(playerlist.get(i).getName());
        populateplayerlistbygoal2();

    }

    void populateplayerlistbygoal2(){
        TableColumn<Player, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setMinWidth(40);
        idColumn.setStyle("-fx-alignment: center;");

        TableColumn<Player, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setMinWidth(180);
        nameColumn.setStyle("-fx-alignment: center;");

        TableColumn<Player, String> nationalityColumn = new TableColumn<>("Nationality");
        nationalityColumn.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        nationalityColumn.setMinWidth(110);
        nameColumn.setStyle("-fx-alignment: center;");

        TableColumn<Player, Date> dobColumn = new TableColumn<>("Date of birth");
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        dobColumn.setMinWidth(120);
        nameColumn.setStyle("-fx-alignment: center;");

        TableColumn<Player, Double> heightColumn = new TableColumn<>("Height");
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));
        heightColumn.setMinWidth(70);
        nameColumn.setStyle("-fx-alignment: center;");

        TableColumn<Player, Double> weightColumn = new TableColumn<>("Weight");
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        weightColumn.setMinWidth(70);
        nameColumn.setStyle("-fx-alignment: center;");

        TableColumn<Player, Integer> numberColumn = new TableColumn<>("Number");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        numberColumn.setMinWidth(70);
        nameColumn.setStyle("-fx-alignment: center;");

        TableColumn<Player, Integer> goalColumn = new TableColumn<>("Number of goals");
        goalColumn.setCellValueFactory(new PropertyValueFactory<>("sumOfGoal"));
        goalColumn.setMinWidth(150);
        goalColumn.setStyle("-fx-alignment: center;");

        Report2TableView.setItems(playerlist);
        Report2TableView.setStyle("-fx-font-size: 16px");
        Report2TableView.getColumns().addAll(idColumn,nameColumn,nationalityColumn,dobColumn,heightColumn,weightColumn,numberColumn,goalColumn);
    }
    
}