import java.util.ArrayList;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;
import sudoku.SudokuBoard;

public class FieldView {
    private int row;
    private int column;
    private TextField field;
    private SudokuBoard board;

    FieldView(int rowId, int columnId, SudokuBoard sudokuBoard) {
        row = rowId;
        column = columnId;
        board = sudokuBoard;

        field = new TextField("");
        field.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        field.setAlignment(Pos.CENTER);
        field.setBorder(new Border(
                new BorderStroke(
                        Color.BLACK,
                        BorderStrokeStyle.SOLID,
                        null, new BorderWidths(1))));

        if ((rowId / 3) % 2 == 0 && ((columnId / 3) % 2 == 0)
                || (rowId / 3) % 2 != 0 && ((columnId / 3) % 2 != 0)) {
            field.setStyle("-fx-background-color: #fcffee");
        }

        field.textProperty().addListener((observable, oldValue, newValue) -> {
            changedField(observable, oldValue, newValue, field);
        });
    }

    public TextField getTextField() {
        return field;
    }

    private void changedField(ObservableValue<? extends String> observable,
                              String oldValue, String newValue, TextField field) {
        if (!isValidInteger(newValue)) {
            field.textProperty().setValue(oldValue);
        } else {
            if (newValue.equals("")) {
                board.set(row, column, 0);
            } else {
                board.set(row, column, Integer.parseInt(newValue));
            }
        }
    }

    private static boolean isValidInteger(String str) {
        ArrayList<String> available = new ArrayList<String>(10);
        available.add("");
        available.add("1");
        available.add("2");
        available.add("3");
        available.add("4");
        available.add("5");
        available.add("6");
        available.add("7");
        available.add("8");
        available.add("9");

        return available.contains(str);
    }
}