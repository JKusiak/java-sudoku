import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BacktrackingSudokuSolver implements SudokuSolver {

    // final function that creates empty board, initializes it with random numbers and solves
    public void solve(SudokuBoard board) {
        fillWithZeroes(board);
        fillInitialRandom(board);
        solveAlgorithm(board);
    }

    // initializing sudoku board by filling it with 0's
    private void fillWithZeroes(SudokuBoard board) {
        for (int i = 0; i < SudokuBoard.dimension; i++) {
            for (int j = 0; j < SudokuBoard.dimension; j++) {
                board.set(i, j, SudokuBoard.empty);
            }
        }
    }

    // filling empty board with 9 random numbers taken from a shuffled list
    private void fillInitialRandom(SudokuBoard board) {
        List<Integer> randomNumbers = new ArrayList<>() {{
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
        for (int numberIndex = 0; numberIndex < SudokuBoard.dimension; numberIndex++) {
            int randomX = ThreadLocalRandom.current().nextInt(1, 9);
            int randomY = ThreadLocalRandom.current().nextInt(1, 9);

            // setting consecutive numbers from a shuffled list to random coordinates
            board.set(randomX, randomY, randomNumbers.get(numberIndex));
        }
    }

    // bool checking if each sudoku row is correct
    private boolean isRowOk(SudokuBoard board, int row, int number) {
        for (int i = 0; i < SudokuBoard.dimension; i++) {
            if (board.get(row, i) == number) {
                return true;
            }
        }
        return false;
    }

    // bool checking if each sudoku column is correct
    private boolean isColOk(SudokuBoard board, int col, int number) {
        for (int i = 0; i < SudokuBoard.dimension; i++) {
            if (board.get(i, col) == number) {
                return true;
            }
        }
        return false;
    }

    // bool checking if each sudoku square is correct
    private boolean isSquareOk(SudokuBoard board, int row, int col, int number) {
        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (board.get(i, j) == number) {
                    return true;
                }
            }
        }
        return false;
    }

    // combined bool checking if the overall setting of the board is correct
    private boolean isOk(SudokuBoard board, int row, int col, int number) {
        return !isRowOk(board, row, number)
                &&  !isColOk(board, col, number)
                &&  !isSquareOk(board, row, col, number);
    }

    //solving the board using backtracking algorithm
    private boolean solveAlgorithm(SudokuBoard board) {
        for (int row = 0; row < SudokuBoard.dimension; row++) {
            for (int col = 0; col < SudokuBoard.dimension; col++) {
                if (board.get(row, col) == SudokuBoard.empty) {
                    // trying each number and checking if possible
                    for (int number = 1; number <= SudokuBoard.dimension; number++) {
                        if (isOk(board, row, col, number)) {
                            board.set(row, col, number);

                            if (solveAlgorithm(board)) { // recursive backtracking
                                return true;
                            } else { // if not proper value, set cell to 0 and repeat
                                board.set(row, col, SudokuBoard.empty);
                            }
                        }
                    }
                    return false; // sudoku unsolvable
                }
            }
        }
        return true; // sudoku solved
    }

}
