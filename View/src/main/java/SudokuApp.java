import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SudokuApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ResourceBundle langBundle = ResourceBundle.getBundle("Lang", Locale.getDefault());

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"), langBundle);
        Scene mainScene = new Scene(root);

        stage.setTitle("SudokuGame");
        stage.setScene(mainScene);
        stage.show();
    }
}
