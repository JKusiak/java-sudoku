import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {
//    @Test
//    public void initialStateFilledWithZeroes() {
//        SudokuBoard zeroBoard = new SudokuBoard(new BacktrackingSudokuSolver());
//        zeroBoard.fillWithZeroes();
//        int[][] boardCopy = zeroBoard.getBoardCopy();
//
//        for(int i=0; i < 9; i++) {
//            for(int j=0; j < 9; j++) {
//                assertEquals(0, boardCopy[i][j]);
//            }
//        }
//    }

    @Test
    public void boardCheckAll() {
        SudokuBoard testBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        testBoard.solveGame();
        assertEquals(true, testBoard.checkBoard());
    }

    @Test
    public void consecutiveBoardsDifferenceCheck() {
        SudokuBoard firstBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        firstBoard.solveGame();
        SudokuBoard secondBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        secondBoard.solveGame();
        assertEquals(false, firstBoard.equals(secondBoard));
    }

    @Test
    public void consecutiveBoardsCopySameCheck() {
        SudokuBoard firstBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard secondBoard = firstBoard;
        firstBoard.solveGame();
        assertEquals(true, firstBoard.equals(secondBoard));
    }

    @Test
    public void boardSetValueGetValue() {
        SudokuBoard testBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        testBoard.set(1, 1, 9);
        assertEquals(9, testBoard.get(1, 1));
        assertNotEquals(1, testBoard.get(1, 1));
    }
}