import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SudokuBoard {
    public static final int dimension = 9;
    public static final int empty = 0;

    private int[][] board = new int[dimension][dimension];

    // storing all values from the board in it's copy
    public int[][] getBoardCopy() {
        int[][] boardCopy = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                boardCopy[i][j] = board[i][j];
            }
        }
        return boardCopy;
    }

    // initializing sudoku board by filling it with 0's
    public void fillWithZeroes() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                board[i][j] = empty;
            }
        }
    }

    // filling empty board with 9 random numbers taken from a shuffled list
    public void fillInitialRandom() {
        List<Integer> randomNumbers = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
            add(7);
            add(8);
            add(9);
        }};
        Collections.shuffle(randomNumbers);

        // setting up random coordinates to which a number from a shuffled list is placed
        for (int numberIndex = 0; numberIndex < dimension; numberIndex++) {
            int randomX = ThreadLocalRandom.current().nextInt(1, 9);
            int randomY = ThreadLocalRandom.current().nextInt(1, 9);

            // setting consecutive numbers from a shuffled list to random coordinates
            board[randomX][randomY] = randomNumbers.get(numberIndex);
            }
        }

    // bool checking if each sudoku row is correct
    private boolean isRowOk(int row, int number) {
        for (int i = 0; i < dimension; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    // bool checking if each sudoku column is correct
    private boolean isColOk(int col, int number) {
        for (int i = 0; i < dimension; i++) {
            if (board[i][col] == number) {
                return true;
            }
        }
        return false;
    }

    // bool checking if each sudoku square is correct
    private boolean isSquareOk(int row, int col, int number) {
        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    // combined bool checking if the overall setting of the board is correct
    private boolean isOk(int row, int col, int number) {
        return !isRowOk(row, number)  &&  !isColOk(col, number)  &&  !isSquareOk(row, col, number);
    }

    //solving the board using backtracking algorithm
    public boolean solve() {
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                if (board[row][col] == empty) {
                    // trying each number and checking if possible
                    for (int number = 1; number <= dimension; number++) {
                        if (isOk(row, col, number)) {
                            board[row][col] = number;

                            if (solve()) { // recursive backtracking
                                return true;
                            } else { // if not proper value, set cell to 0 and repeat
                                board[row][col] = empty;
                            }
                        }
                    }
                    return false; // sudoku unsolvable
                }
            }
        }
        return true; // sudoku solved
    }

    // final function that creates empty board, initializes it with random numbers and solves
    public void fillBoard() {
        fillWithZeroes();
        fillInitialRandom();
        solve();
    }

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
                if (board[rowId][columnId] != objBoard.getBoardCopy()[rowId][columnId]) {
                    return false;
                }
            }
        }
        return true;
    }
}
