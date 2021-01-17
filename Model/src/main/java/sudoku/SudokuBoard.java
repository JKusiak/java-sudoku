package sudoku;

import com.google.common.base.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solver.SudokuSolver;


@Entity
@Table(name = "BOARDS")
public class SudokuBoard implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "GAMENAME", unique = true)
    private String gameName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "BOARDS_ID")
    private List<SudokuField> fields = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public List<SudokuField> getFields() {
        return fields;
    }

    public void setFields(List<SudokuField> fields) {
        this.fields = fields;
    }

    public void addField(SudokuField field) {
        fields.add(field);
        field.setSudokuBoard(null);
    }

    public SudokuBoard() {

    }

    //----------------------------------------------------------------------------------

    public static final int dimension = 9;
    public static final int empty = 0;
    @Transient
    private SudokuSolver sudokuSolver;

    @Transient
    private SudokuField[][] boardFieldsArray = new SudokuField[dimension][dimension];

    private static final Logger logger =
            LogManager.getLogger(SudokuBoard.class.getPackage().getName());

    // parametrized constructor passing type of solving algorithm to the board object
    public SudokuBoard(SudokuSolver solver) {
        for (int i = 0; i < boardFieldsArray.length; i++) {
            for (int j = 0; j < boardFieldsArray[i].length; j++) {
                boardFieldsArray[i][j] = new SudokuField();
            }
        }
        sudokuSolver = solver;
    }

    // method invoking solve procedure on the solver type passed by constructor
    public void solveGame() {
        sudokuSolver.solve(this);
    }

    public int get(int x, int y) {
        return boardFieldsArray[x][y].getValue();
    }

    public void set(int x, int y, int value) {
        boardFieldsArray[x][y].setValue(value);
    }

    public SudokuField[][] getBoardFieldsArray() {
        return boardFieldsArray;
    }

    public void setBoardFieldsArray(SudokuField[][] array) {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                boardFieldsArray[i][j] = array[i][j];
            }
        }
    }

    public void setSudokuSolver(SudokuSolver solver) {
        sudokuSolver = solver;
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

    public List<SudokuField> twoDimensionArrayToList(SudokuField[][] twoDArray) {
        List<SudokuField> list = new ArrayList<SudokuField>();
        for (SudokuField[] array : twoDArray) {
            list.addAll(Arrays.asList(array));
        }
        return list;
    }

    public SudokuField[][] listToTwoDimensionArray(List<SudokuField> list) {
        SudokuField[][] array = new SudokuField[dimension][dimension];
        int listElement = 0;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                array[i][j] = list.get(listElement);
                listElement++;
            }
        }
        return array;
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

        for (int rowId = 0; rowId < boardFieldsArray.length; rowId++) {
            for (int columnId = 0; columnId < boardFieldsArray[rowId].length; columnId++) {
                if (this.get(rowId, columnId) != objBoard.get(rowId, columnId)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(boardFieldsArray);
    }

    @Override
    public String toString() {
        return "SudokuBoard.SudokuBoard{"
                + "board="
                + Arrays.toString(boardFieldsArray)
                + '}';
    }

    // this method creates a copy of an object without maintaining connection to the
    // parent object, all changes in the first object after clone won't affect
    // object created from it
    @Override
    public SudokuBoard clone() {
        // SudokuBoard board = (SudokuBoard) super.clone();

        SudokuBoard newBoard = new SudokuBoard(this.sudokuSolver.clone());
        for (int rowId = 0; rowId < boardFieldsArray.length; rowId++) {
            for (int columnId = 0; columnId < boardFieldsArray[rowId].length; columnId++) {
                newBoard.set(rowId, columnId, this.get(rowId, columnId));
            }
        }
        return newBoard;
    }
}
