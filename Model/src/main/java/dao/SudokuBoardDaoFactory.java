package dao;

import sudoku.SudokuBoard;

public class SudokuBoardDaoFactory {
    private SudokuBoardDaoFactory() {

    }

    public static Dao<SudokuBoard> getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    public static Dao<SudokuBoard> getDatabaseDao(String gameName) {
        return new JdbcSudokuBoardDao(gameName);
    }

}
