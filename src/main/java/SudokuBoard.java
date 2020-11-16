import java.util.HashSet;
import java.util.Set;

public class SudokuBoard {
    public static final int dimension = 9;
    public static final int empty = 0;
    private SudokuField[][] board = new SudokuField[dimension][dimension];
    private SudokuSolver sudokuSolver;

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    SudokuBoard(SudokuSolver solver) {
        this();
        sudokuSolver = solver;
    }

    SudokuBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new SudokuField();
            }
        }
    }

    public int get(int x, int y) {
        return board[x][y].getFieldValue();
    }

    public void set(int x, int y, int value) {
        board[x][y].setFieldValue(value);
    }

    public SudokuStructure getRow(int x) {
        SudokuField[] row = board[x].clone();
        return new SudokuStructure(row);
    }

    public SudokuStructure getColumn(int y) {
        SudokuField[] column = new SudokuField[dimension];
        for     (int i = 0; i < dimension; i++) {
            column[i] = new SudokuField(this.get(i, y));
        }
        return new SudokuStructure(column);
    }

    public SudokuStructure getBox(int x, int y) {
        int squareSize = 3;
        int xboundary = (int) Math.floor(x / squareSize) * squareSize;
        int yboundary = (int) Math.floor(y / squareSize) * squareSize;
        SudokuField[] boxArray = new SudokuField[dimension];
        int i = 0;

        for (int xbox = xboundary; xbox < xboundary + squareSize; xbox++) {
            for (int ybox = yboundary; ybox < yboundary + squareSize; ybox++) {
                boxArray[i] = new SudokuField(this.get(xbox, ybox));
                i++;
            }
        }
        return new SudokuStructure(boxArray);
    }

    public boolean checkBoard() {
        Set<Integer> testSet = new HashSet<>();
        // check rows
        for (int x = 0; x < dimension; x++) {
            testSet.clear();
            for (int y = 0; y < dimension; y++) {
                if (testSet.add(this.get(x, y)) == false) {
                    return false; // repetition in a row
                }
            }
        }

        // check columns
        for (int y = 0; y < dimension; y++) {
            testSet.clear();
            for (int x = 0; x < dimension; x++) {
                if (testSet.add(this.get(x, y)) == false) {
                    return false; // repetition in a column
                }
            }
        }

        // check boxes
        int squareSize = 3;
        for (int x = 0; x < dimension; x += squareSize) {
            for (int y = 0; y < dimension; y += squareSize) {
                testSet.clear();
                for (int xinternal = x; xinternal < squareSize; xinternal++) {
                    for (int yinternal = y; yinternal < squareSize; yinternal++) {
                        if (testSet.add(this.get(xinternal, yinternal)) == false) {
                            return false;
                        }
                    }
                }
            }
        }

    return true;
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
                if (this.get(rowId, columnId) != objBoard.get(rowId, columnId)) {
                    return false;
                }
            }
        }
        return true;
    }
}
