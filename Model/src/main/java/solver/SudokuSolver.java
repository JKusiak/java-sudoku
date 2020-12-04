package solver;

import sudoku.SudokuBoard;

// interface for passing a proper sudoku solver to the SudokuBoard object upon its creation
public interface SudokuSolver {
    void solve(SudokuBoard board);
}
