import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;


import java.io.IOException;

public class MenuController {

    @FXML
    public ComboBox<DifficultyLevels> difficultyBox;

    @FXML
    public void initialize() {
        difficultyBox.getItems().setAll(DifficultyLevels.values());
        difficultyBox.getSelectionModel().selectFirst();
    }

    @FXML
    public void clickPlay(ActionEvent event) throws IOException {
        event.consume();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Sudoku.fxml"));
        Parent sudokuLayout = loader.load();

        DifficultyLevels level = difficultyBox.getSelectionModel().getSelectedItem();

        SudokuController sudokuController = loader.getController();
        sudokuController.startup(level);

        Scene sudokuScene = new Scene(sudokuLayout);
        stage.setScene(sudokuScene);


    }
}
