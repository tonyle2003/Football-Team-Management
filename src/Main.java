import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override // Override the start method in the Application class
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("gui/LoginUI.fxml"));
            Scene scene = new Scene(root);

            // String css = this.getClass().getResource("application.css").toExternalForm();
            // scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}