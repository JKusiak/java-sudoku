import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;



public class MenuController implements Initializable {
    ResourceBundle langBundle = ResourceBundle.getBundle("Lang", Locale.getDefault());
    ResourceBundle authorsBundle = ResourceBundle.getBundle("Authors", Locale.getDefault());

    ObservableList<Locale> languages = FXCollections.observableArrayList(
            new Locale("pl", "PL"),
            new Locale("en", "UK")
    );

    @FXML public ComboBox<DifficultyLevels> difficultyBox;
    @FXML public ComboBox<Locale> languageBox;
    @FXML public Button playBtn;
    @FXML public Label authorsLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        difficultyBox.getItems().setAll(DifficultyLevels.values());
        difficultyBox.getSelectionModel().selectFirst();

        languageBox.getItems().addAll(languages);
        languageBox.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                Locale.setDefault(newValue);
                langBundle = ResourceBundle.getBundle("Lang", Locale.getDefault());
                playBtn.setText(langBundle.getString("playBtn"));

                authorsBundle = ResourceBundle.getBundle("Authors", Locale.getDefault());
                authorsLabel.setText((authorsBundle.getString("lan")
                        + authorsBundle.getString("names")));
            }
        });
    }

    @FXML
    public void clickPlay(ActionEvent event) throws IOException {
        event.consume();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Sudoku.fxml"), langBundle);
        Parent sudokuLayout = loader.load();

        DifficultyLevels level = difficultyBox.getSelectionModel().getSelectedItem();

        SudokuController sudokuController = loader.getController();
        sudokuController.startup(level);

        Scene sudokuScene = new Scene(sudokuLayout);
        stage.setScene(sudokuScene);
    }
}
