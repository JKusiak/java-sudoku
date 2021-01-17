import solver.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;
import sudoku.SudokuStructure;
import sudoku.SudokuField;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {
    @Test
    public void boardCheckAll() {
        SudokuBoard testBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        testBoard.solveGame();
        assertTrue(testBoard.checkBoard());
    }

    @Test
    public void consecutiveBoardsDifferenceCheck() {
        SudokuBoard firstBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        firstBoard.solveGame();
        SudokuBoard secondBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        secondBoard.solveGame();
        assertNotEquals(firstBoard, secondBoard);
    }

    @Test
    public void consecutiveBoardsCopySameCheck() {
        SudokuBoard firstBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard secondBoard = firstBoard;
        firstBoard.solveGame();
        assertEquals(firstBoard, secondBoard);
    }

    @Test
    public void boardSetValueGetValue() {
        SudokuBoard testBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        testBoard.set(1, 1, 9);
        assertEquals(9, testBoard.get(1, 1));
        assertNotEquals(1, testBoard.get(1, 1));
    }


    @Test
    public void getRowNonEmptyOnFilledBoard() {
        SudokuBoard testBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        testBoard.solveGame();
        SudokuStructure testRowFilled = testBoard.getRow(1);
        SudokuStructure testRowEmpty = new SudokuStructure(Arrays.asList(
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0)
        ));
        assertNotEquals(testRowFilled, testRowEmpty);
    }

    @Test
    public void getColumnNonEmptyOnFilledBoard() {
        SudokuBoard testBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        testBoard.solveGame();
        SudokuStructure testColumnFilled = testBoard.getColumn(1);
        SudokuStructure testColumnEmpty = new SudokuStructure(Arrays.asList(
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0)
        ));
        assertNotEquals(testColumnFilled, testColumnEmpty);
    }

    @Test
    public void getBoxNonEmptyOnFilledBoard() {
        SudokuBoard testBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        testBoard.solveGame();
        SudokuStructure testBoxFilled = testBoard.getBox(1, 1);
        SudokuStructure testBoxEmpty = new SudokuStructure(Arrays.asList(
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0),
                new SudokuField(0)
        ));
        assertNotEquals(testBoxFilled, testBoxEmpty);
    }

    @Test
    public void getRowDifferentBoardsDifferentResults() {
        SudokuBoard testBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        testBoard1.solveGame();
        SudokuBoard testBoard2 = new SudokuBoard(new BacktrackingSudokuSolver());
        testBoard2.solveGame();
        SudokuStructure testRow1 = testBoard1.getRow(1);
        SudokuStructure testRow2 = testBoard2.getRow(1);
        assertNotEquals(testRow1, testRow2);
    }

    @Test
    public void getColumnDifferentBoardsDifferentResults() {
        SudokuBoard testBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        testBoard1.solveGame();
        SudokuBoard testBoard2 = new SudokuBoard(new BacktrackingSudokuSolver());
        testBoard2.solveGame();
        SudokuStructure testColumn1 = testBoard1.getColumn(1);
        SudokuStructure testColumn2 = testBoard2.getColumn(1);
        assertNotEquals(testColumn1, testColumn2);
    }

    @Test
    public void getBoxDifferentBoardsDifferentResults() {
        SudokuBoard testBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        testBoard1.solveGame();
        SudokuBoard testBoard2 = new SudokuBoard(new BacktrackingSudokuSolver());
        testBoard2.solveGame();
        SudokuStructure testBox1 = testBoard1.getBox(1, 1);
        SudokuStructure testBox2 = testBoard2.getBox(1, 1);
        assertNotEquals(testBox1, testBox2);
    }

    @Test
    public void getBoxApproximatesToLayoutBoxCorrectly() {
        SudokuBoard testBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuStructure testBox1 = testBoard.getBox(2, 2);
        SudokuStructure testBox2 = testBoard.getBox(1, 1);
        assertEquals(testBox1, testBox2);
    }

    @Test
    public void differentBoardsDifferentHashCodesCheck() {
        SudokuBoard testBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard testBoard2= new SudokuBoard(new BacktrackingSudokuSolver());

        assertNotEquals(testBoard1.hashCode(), testBoard2.hashCode());
    }

    @Test
    public void sameBoardSameHashCodeCheck() {
        SudokuBoard testBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard testBoard2 = testBoard1;

        assertEquals(testBoard1.hashCode(), testBoard2.hashCode());
    }

    @Test
    public void differentBoardsEqualsFalseCheck() {
        SudokuBoard testBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard testBoard2= new SudokuBoard(new BacktrackingSudokuSolver());
        testBoard1.solveGame();
        testBoard2.solveGame();
        assertFalse(testBoard1.equals(testBoard2));
    }

    @Test
    public void sameBoardEqualsTrueCheck() {
        SudokuBoard testBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard testBoard2 = testBoard1;
        testBoard1.solveGame();
        assertTrue(testBoard1.equals(testBoard2));
    }

    @Test
    public void sudokuBoardCloneCorrect() throws CloneNotSupportedException {
        SudokuBoard testBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        testBoard1.solveGame();
        SudokuBoard testBoard2 = testBoard1.clone();

        assertNotSame(testBoard1, testBoard2);
        assertEquals(testBoard1, testBoard2);

        testBoard2.set(5, 5, 0);

        assertNotEquals(testBoard2, testBoard1);
    }

    @Test
    public void listToArraySame() {
        SudokuBoard testBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        testBoard.solveGame();

        SudokuField[][] testArray = testBoard.getBoardFieldsArray();

        List<SudokuField> testList = new ArrayList<>();
        testList = testBoard.twoDimensionArrayToList(testArray);
        SudokuField[][] finalArray = testBoard.listToTwoDimensionArray(testList);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(testArray[i][j], finalArray[i][j]);
            }
        }
    }

    @Test
    public void setBoardFieldsArray() {
        SudokuBoard testBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        testBoard1.solveGame();

        SudokuBoard testBoard2 = new SudokuBoard(new BacktrackingSudokuSolver());
        testBoard2.solveGame();

        SudokuField[][] testArray = testBoard1.getBoardFieldsArray();

        testBoard2.setBoardFieldsArray(testArray);

        assertEquals(testBoard1.get(0, 0), testBoard2.get(0, 0));
        assertEquals(testBoard1.get(5, 5), testBoard2.get(5, 5));
        assertEquals(testBoard1.get(8, 8), testBoard2.get(8, 8));

    }
}