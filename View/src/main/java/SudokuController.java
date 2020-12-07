import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import solver.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;

import java.io.IOException;
import java.util.Random;


public class SudokuController {
    SudokuBoard board;

    @FXML
    private GridPane sudokuGrid;

    public void startup(DifficultyLevels level) {
        board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();

        bindGrid();
        setDifficulty(board, level);
        drawSudoku();
    }

    @FXML
    private void backToMenu(ActionEvent event) throws IOException {
        event.consume();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent menu = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
        Scene sudokuScene = new Scene(menu);
        stage.setScene(sudokuScene);
    }

    private void bindGrid() {
        sudokuGrid.getChildren().clear();
        for (int rowId = 0; rowId < sudokuGrid.getRowCount() ; rowId++) {
            for (int columnId = 0; columnId < sudokuGrid.getColumnCount() ; columnId++) {

                Label label = new Label("");
                label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                label.setAlignment(Pos.CENTER);
                label.setFont(Font.font(18));
                label.setBorder(new Border(
                        new BorderStroke(
                                Color.BLACK,
                                BorderStrokeStyle.SOLID,
                                null, new BorderWidths(0.5))));
                // add to grid
                sudokuGrid.add(label, columnId, rowId);
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
                if (node instanceof Label) {
                    ((Label) node).setText(value);
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

            if (board.get(nextX, nextY) != 0){
                board.set(nextX, nextY, 0);
            }
        }
    }

}
