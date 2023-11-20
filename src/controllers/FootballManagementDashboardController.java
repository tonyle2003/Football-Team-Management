package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TextField NameimportTextField;

    @FXML
    private TextField NationalityTextField;

    @FXML
    private DatePicker DatePickerImport;

    @FXML
    private ComboBox<String> IdClubImportComboBox;

    @FXML
    private TextField HeightImportTextField;

    @FXML
    private TextField WeightImportTextField;

    @FXML
    private TextField NumberImportTextField;

    /*
     * Update players
     */

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

    @FXML
    private TableView<Player> ListPlayerView;

    /*
     * Report
     */
    
    @FXML
    private AnchorPane Report1Pane;

    @FXML
    private AnchorPane Report2Pane;

    @FXML
    private AnchorPane Report3Pane;

    @FXML
    private TableView<Player> Report1TableView;

    @FXML
    private TableView<Player> Report2TableView;

    @FXML
    private TableView<Player> Report3TableView;


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
    }

    @FXML
    private void handleUpdateRemove(ActionEvent actionEvent){
        UpdateImportPane.setVisible(false);
        UpdatePlayersPane.setVisible(false);
        RemoveplayersPane.setVisible(true);
        HomePagePane.setVisible(false);
    }

    @FXML
    private void handleImportnewPlayerButton(ActionEvent actionEvent){
        
    }
    
    @FXML
    private void handlResetnewPlayerButton(ActionEvent actionEvent){
        
    }

    @FXML
    private void handleUpdatePlayerButton(ActionEvent actionEvent){
        
    }

    @FXML
    private void handlResetPlayerButton(ActionEvent actionEvent){
        
    }

    @FXML
    private void handleRemovalReload(ActionEvent actionEvent){
        
    }

    @FXML
    private void handleRemovalplayers(ActionEvent actionEvent){
        
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
        Report3Pane.setVisible(false);
    }

    @FXML
    private void handleReport1Button(ActionEvent actionEvent){
        Report1Pane.setVisible(true);
        Report2Pane.setVisible(false);
        Report3Pane.setVisible(false);
    }

    @FXML
    private void handleReport2Button(ActionEvent actionEvent){
        Report1Pane.setVisible(false);
        Report2Pane.setVisible(true);
        Report3Pane.setVisible(false);
    }

    @FXML
    private void handleReport3Button(ActionEvent actionEvent){
        Report1Pane.setVisible(false);
        Report2Pane.setVisible(false);
        Report3Pane.setVisible(true);
    }

    
}