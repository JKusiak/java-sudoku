import dao.FileSudokuBoardDao;
import dao.SudokuBoardDaoFactory;
import solver.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class SudokuBoardDaoTest {

    static String tempDirectory = "Desktop/Temp";
    static String tempFile = tempDirectory + "/daoTest.txt";

    static boolean deleteDirectory(File directoryToDelete) {
        File[] allFiles = directoryToDelete.listFiles();
        if (allFiles != null) {
            for (File file : allFiles) {
                deleteDirectory(file);
            }
        }
        return directoryToDelete.delete();
    }

    @BeforeAll
    static void initialization() {
        boolean createDirectory = new File(tempDirectory).mkdirs();
    }

    @AfterAll
    static void finalization() {
        deleteDirectory(new File(tempDirectory));
    }



    @Test
    public void writeToFileCreatesDirectory() {
        FileSudokuBoardDao dao = new FileSudokuBoardDao(tempFile);
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        try {
            dao.write(board);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(true, new File(tempFile).isFile());
    }

    @Test
    public void classFromFileProgramClassVerifySame() {
        assertTrue(SudokuBoardDaoFactory.getFileDao(tempFile).getClass() == FileSudokuBoardDao.class);
    }

    @Test
    public void readWriteBoardToFileCompareBoardsVerifySame() {
        FileSudokuBoardDao dao = new FileSudokuBoardDao(tempFile);
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard boardFromFile = null;

        board.solveGame();
        try {
            dao.write(board);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            boardFromFile = dao.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(board.equals(boardFromFile));
    }

    @Test
    public void wrongDirectoryWriteThrowsIOExceptionCheck() {
//        Assertions.assertThrows(FileSudokuBoardDao.MyIoException.class, () -> {
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            //here a path to directory, not file, to cause IOException
            FileSudokuBoardDao dao = new FileSudokuBoardDao(tempDirectory);
            SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
            dao.write(board);
        });
    }

    @Test
    public void wrongDirectoryReadThrowsIOExceptionCheck() {
//        Assertions.assertThrows(FileSudokuBoardDao.MyIoException.class, () -> {
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            //here a path to directory, not file, to cause IOException
            FileSudokuBoardDao dao = new FileSudokuBoardDao(tempDirectory);
            dao.read();
        });
    }

}
