import dao.FileSudokuBoardDao;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import exceptions.NoDataException;
import exceptions.NoSuchFileException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solver.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;


public class SudokuController {
    SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
    ResourceBundle langBundle = ResourceBundle.getBundle("Lang", Locale.getDefault());
    String savingPath = System.getProperty("user.dir") + "/sudokuSave.txt";

    Logger logger = LogManager.getLogger("general");

    @FXML private GridPane sudokuGrid;
    @FXML private Label message;

    public void startup(DifficultyLevels level) {
        board.solveGame();

        bindToGrid(board);
        setDifficulty(board, level);
        drawSudoku();
    }

    @FXML
    public void solveGame() {
        board.solveGame();
        drawSudoku();
    }

    @FXML
    public void checkGame() {
        if (board.checkBoard()) {
            message.setText(langBundle.getString("layout_correct"));
            message.setTextFill(Color.GREEN);
            logger.debug(langBundle.getString("layout_correct"));
        } else {
            message.setText(langBundle.getString("layout_incorrect"));
            message.setTextFill(Color.RED);
            logger.debug(langBundle.getString("layout_incorrect"));
        }
    }

    @FXML
    public void saveGame() {
        try {
            FileSudokuBoardDao dao = new FileSudokuBoardDao(savingPath);
            dao.write(board);
            message.setText(langBundle.getString("save_correct"));
            message.setTextFill(Color.GREEN);
            logger.debug(langBundle.getString("save_correct"));
        } catch (NoSuchFileException e) {
            message.setText(langBundle.getString("save_wrong"));
            message.setTextFill(Color.RED);
            logger.debug(langBundle.getString("save_wrong"));
        }
    }


    @FXML
    public void loadGame() {
        try {
            FileSudokuBoardDao dao = new FileSudokuBoardDao(savingPath);
            board = dao.read();

            bindToGrid(board);
            drawSudoku();
            message.setText(langBundle.getString("load_correct"));
            message.setTextFill(Color.GREEN);
            logger.debug(langBundle.getString("load_correct"));
        } catch (NoSuchFileException e) {
            message.setText(langBundle.getString("load_no_file"));
            message.setTextFill(Color.BLACK);
            logger.debug(langBundle.getString("load_no_file"));
        } catch (NoDataException e) {
            message.setText(langBundle.getString("load_empty_file"));
            message.setTextFill(Color.RED);
            logger.debug(langBundle.getString("load_empty_file"));
        }
    }

    @FXML
    private void backToMenu(ActionEvent event) throws IOException {
        event.consume();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent menu = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"), langBundle);
        Scene sudokuScene = new Scene(menu);
        stage.setScene(sudokuScene);
    }

    private void bindToGrid(SudokuBoard board) {
        sudokuGrid.getChildren().clear();
        for (int rowId = 0; rowId < sudokuGrid.getRowCount(); rowId++) {
            for (int columnId = 0; columnId < sudokuGrid.getColumnCount(); columnId++) {
                FieldView fieldView = new FieldView(rowId, columnId, board);
                sudokuGrid.add(fieldView.getTextField(), columnId, rowId);
            }
        }
    }

    private void drawSudoku() {
        for (int rowId = 0; rowId < sudokuGrid.getRowCount(); rowId++) {
            for (int columnId = 0; columnId < sudokuGrid.getColumnCount(); columnId++) {
                String value = "";
                if (board.get(rowId, columnId) != 0) {
                    value = String.valueOf(board.get(rowId, columnId));
                }
                Node node = getNodeFromGridPane(sudokuGrid, columnId, rowId);
                if (node instanceof TextField) {
                    ((TextField) node).setText(value);
                }
            }
        }
    }

    private Node getNodeFromGridPane(GridPane sudokuPane, int col, int row) {
        for (Node node : sudokuPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public static void setDifficulty(SudokuBoard board, DifficultyLevels level) {
        switch (level) {
            case HARD: {
                removeFields(board, 65);
                break;
            }
            case MEDIUM: {
                removeFields(board, 55);
                break;
            }
            default: {
                removeFields(board, 40);
                break;
            }
        }
    }

    private static void removeFields(SudokuBoard board, double removeAmount) {
        Random rng = new Random();

        for (int i = 0; i < removeAmount; i++) {
            Integer nextX = rng.nextInt(9);
            Integer nextY = rng.nextInt(9);

            if (board.get(nextX, nextY) != 0) {
                board.set(nextX, nextY, 0);
            }
        }
    }

}
