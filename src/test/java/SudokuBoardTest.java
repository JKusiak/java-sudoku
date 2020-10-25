import org.junit.jupiter.api.Test;
import java.util.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {
    @Test
    public void initialStateFilledWithZeroes() {
        SudokuBoard zeroBoard = new SudokuBoard();
        zeroBoard.fillWithZeroes();
        int[][] boardCopy = zeroBoard.getBoardCopy();

        for(int i=0; i < 9; i++) {
            for(int j=0; j < 9; j++) {
                assertEquals(0, boardCopy[i][j]);
            }
        }
    }

    @Test
    public void BoardCheckRow() {
        SudokuBoard checkBoard = new SudokuBoard();
        checkBoard.fillBoard();
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
        SudokuBoard checkBoard = new SudokuBoard();
        checkBoard.fillBoard();
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
        SudokuBoard checkBoard = new SudokuBoard();
        checkBoard.fillBoard();
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
        SudokuBoard firstBoard = new SudokuBoard();
        firstBoard.fillBoard();
        SudokuBoard secondBoard = new SudokuBoard();
        secondBoard.fillBoard();
        assertEquals(false, firstBoard.equals(secondBoard));
    }

    @Test
    public void consecutiveBoardsCopySameCheck() {
        SudokuBoard firstBoard = new SudokuBoard();
        SudokuBoard secondBoard = firstBoard;
        firstBoard.fillBoard();
        assertEquals(true, firstBoard.equals(secondBoard));
    }


}