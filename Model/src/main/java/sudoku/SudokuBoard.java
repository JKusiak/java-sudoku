package sudoku;

import com.google.common.base.Objects;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solver.SudokuSolver;

public class SudokuBoard implements Serializable, Cloneable {
    public static final int dimension = 9;
    public static final int empty = 0;
    private SudokuField[][] board = new SudokuField[dimension][dimension];
    private SudokuSolver sudokuSolver;

    private static final Logger logger = LogManager.getLogger(SudokuBoard.class.getPackage().getName());



    // parametrized constructor passing type of solving algorithm to the board object
    public SudokuBoard(SudokuSolver solver) {
        this();
        sudokuSolver = solver;
    }

    // non - parametrized constructor passing ordered values from 1 to 9 to the board object
    public SudokuBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new SudokuField();
            }
        }
    }

    // method invoking solve procedure on the solver type passed by constructor
    public void solveGame() {
        sudokuSolver.solve(this);
    }

    public int get(int x, int y) {
        return board[x][y].getFieldValue();
    }

    public void set(int x, int y, int value) {
        board[x][y].setFieldValue(value);
    }

    // iterates through 9 elements horizontally in the row passed in parameter,
    // adding them to the list
    public SudokuStructure getRow(int x) {
        // this one doesn't propagate changes to the array, just deletes it as garbage
        // just after creating a new list, so we can't use it here
        //List<SudokuField> rowList = new ArrayList(Arrays.asList(new SudokuField[dimension]));

        List<SudokuField> rowList = Arrays.asList(new SudokuField[dimension]);

        for (int i = 0; i < dimension; i++) {
            rowList.set(i, new SudokuField(this.get(x, i)));
        }

        return new SudokuStructure(rowList);
    }

    // iterates through 9 elements vertically in the column passed in parameter,
    // adding them to the list
    public SudokuStructure getColumn(int y) {
        List<SudokuField> columnList = Arrays.asList(new SudokuField[dimension]);
        for (int i = 0; i < dimension; i++) {
            columnList.set(i, new SudokuField(this.get(i, y)));
        }

        return new SudokuStructure(columnList);
    }

    // approximates coordinates to the corresponding sudoku box beginning and
    // iterates through its elements adding them to the list
    public SudokuStructure getBox(int x, int y) {
        int squareSize = 3;
        int xboundary = (int) Math.floor(x / squareSize) * squareSize;
        int yboundary = (int) Math.floor(y / squareSize) * squareSize;
        List<SudokuField> boxList = Arrays.asList(new SudokuField[dimension]);
        int i = 0;

        for (int xbox = xboundary; xbox < xboundary + squareSize; xbox++) {
            for (int ybox = yboundary; ybox < yboundary + squareSize; ybox++) {
                boxList.set(i, new SudokuField(this.get(xbox, ybox)));
                i++;
            }
        }

        return new SudokuStructure(boxList);
    }

    public boolean checkBoard() {
        // pass numbers from 1 to 9 to functions to get all rows and all columns
        for (int i = 0; i < dimension; i++) {
            if (!getRow(i).verify() || !getColumn(i).verify()) {
                logger.debug("Board layout is incorrect");
                return false;
            }
        }

        // for less iterations gets box just once for each 3x3 sudoku box that is counted
        // in the game rules, not all the boxes one can create on board (49 total boxes)
        for (int y = 0; y < dimension; y += 3) {
            for (int x = 0; x < dimension; x += 3) {
                if (!getBox(x, y).verify()) {
                    logger.debug("Board layout is incorrect");
                    return false;
                }
            }
        }
        logger.debug("Board layout is correct");
        return true;
    }

    // overridden method for testing purposes
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        SudokuBoard objBoard = (SudokuBoard) obj;

        for (int rowId = 0; rowId < board.length; rowId++) {
            for (int columnId = 0; columnId < board[rowId].length; columnId++) {
                if (this.get(rowId, columnId) != objBoard.get(rowId, columnId)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(board);
    }

    @Override
    public String toString() {
        return "SudokuBoard.SudokuBoard{"
                + "board="
                + Arrays.toString(board)
                + '}';
    }

    // this method creates a copy of an object without maintaining connection to the
    // parent object, all changes in the first object after clone won't affect
    // object created from it
    @Override
    public SudokuBoard clone() {
        // SudokuBoard board = (SudokuBoard) super.clone();

        SudokuBoard newBoard = new SudokuBoard(this.sudokuSolver.clone());
        for (int rowId = 0; rowId < board.length; rowId++) {
            for (int columnId = 0; columnId < board[rowId].length; columnId++) {
                newBoard.set(rowId, columnId, this.get(rowId, columnId));
            }
        }
        return newBoard;
    }
}
