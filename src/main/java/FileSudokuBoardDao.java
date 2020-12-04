import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    public String fileName;

    public FileSudokuBoardDao(String newFileName) {
        fileName = newFileName;
    }

    @Override
    public SudokuBoard read() {
        // try-with-resources works, because we implement java.lang.AutoCloseable.
        // Otherwise, the case should ne written manually like that:
        //        finally {
        //            if (inputStream != null) {
        //                try {
        //                    inputStream.close();
        //                } catch (IOException e) {
        //                    e.printStackTrace();
        //                }
        //            }
        //        }
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            SudokuBoard board = (SudokuBoard) inputStream.readObject();
            //System.out.println(board);
            return board;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new MyIoException("IO Exception on reading board from file");
        }
    }

    @Override
    public void write(SudokuBoard board) {
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(board);
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyIoException("IO Exception on writing board to file");
        }
    }

    @Override
    public void close() throws Exception {
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
        System.out.println("Calling finalize() method");
        super.finalize();
    }

    class MyIoException extends RuntimeException {
        public MyIoException(String message) {
            super(message);
        }
    }
}
