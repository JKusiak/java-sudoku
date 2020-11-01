public class SudokuBoard {
    public static final int dimension = 9;
    public static final int empty = 0;
    private int[][] board = new int[dimension][dimension];
    private SudokuSolver sudokuSolver;

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    SudokuBoard(SudokuSolver solver) {
        sudokuSolver = solver;
    }

    public int get(int x, int y) {
        return board[x][y];
    }

    public void set(int x, int y, int value) {
        board[x][y] = value;
    }

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
