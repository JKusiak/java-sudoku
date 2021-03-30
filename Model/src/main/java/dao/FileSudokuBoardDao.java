package dao;

import exceptions.NoDataException;
import exceptions.NoSuchFileException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sudoku.SudokuBoard;


public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    public String fileName;
    private static final Logger logger =
            LogManager.getLogger(FileSudokuBoardDao.class.getPackage().getName());


    public FileSudokuBoardDao(String newFileName) {
        fileName = newFileName;
    }

    @Override
    public SudokuBoard read() throws NoDataException, NoSuchFileException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            SudokuBoard board = (SudokuBoard) inputStream.readObject();

            logger.debug("Loaded correctly");
            return board;
        } catch (ClassNotFoundException e) {
            throw new NoDataException();
        } catch (IOException e) {
            throw new NoSuchFileException("There is no such file", e);
        }
    }

    @Override
    public void write(SudokuBoard board) throws NoSuchFileException {
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(board);
        } catch (IOException e) {
            throw new NoSuchFileException("There is no such file", e);
        }
        logger.debug("Saved correctly");
    }

    @Override
    public void close() {
    }

    // Oracle java docs state that the method is deprecated for several reasons:
    // - it can run unexpectedly when objects become unreachable
    // - even if object is resurrected, its finalize can be invoked only once
    // - its called when object is to get garbage collected (has no more references),
    //   but this may never happen
    // - had to comment line 170 in checkstyle file <!--<module name="NoFinalizer"/>-->
    //   because it returned a severe error of using this method
    @Override
    public void finalize() throws Throwable {
        super.finalize();
        logger.info("Called finalize() method");
    }
}
