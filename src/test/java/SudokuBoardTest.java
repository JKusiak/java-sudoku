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
    public void BoardCheckRow() {
        SudokuBoard checkBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        checkBoard.solveGame();
        int[][] boardCopy = checkBoard.getBoardCopy();
        Set<Integer>testSet = new HashSet<>();
        boolean uniqueRow = true;

        for(int i=0; i<3; i++) {
            for(int j=0; j < 9; j++){
                if(testSet.add(boardCopy[i][j]) == false){
                    uniqueRow = false;
                }
            }
            assertTrue(uniqueRow);
            uniqueRow = true;
            testSet.clear();
        }
    }

    @Test
    public void BoardCheckColumn() {
        SudokuBoard checkBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        checkBoard.solveGame();
        int[][] boardCopy = checkBoard.getBoardCopy();
        Set<Integer>testSet = new HashSet<>();
        boolean uniqueColumn = true;

        for(int i=0; i<3; i++) {
            for(int j=0; j < 9; j++){
                if(testSet.add(boardCopy[j][i]) == false){
                    uniqueColumn = false;
                }
            }
            assertTrue(uniqueColumn);
            uniqueColumn = true;
            testSet.clear();
        }
    }

    @Test
    void BoardCheckSquare() {
        SudokuBoard checkBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        checkBoard.solveGame();
        int[][] boardCopy = checkBoard.getBoardCopy();
        Set<Integer>testSet = new HashSet<>();
        boolean uniqueSquare = true;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (testSet.add(boardCopy[3 * (i / 3) + j][3 * (i % 3) + k]) == false) {
                        uniqueSquare = false;
                    }
                }
            }
            assertTrue(uniqueSquare);
            uniqueSquare = true;
            testSet.clear();
        }
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

}