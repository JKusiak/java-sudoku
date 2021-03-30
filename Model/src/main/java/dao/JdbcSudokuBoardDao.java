package dao;

import exceptions.NameExistsException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sudoku.SudokuBoard;
import sudoku.SudokuBoardRepository;
import sudoku.SudokuField;


public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    private String gameName;
    private static final Logger logger =
            LogManager.getLogger(FileSudokuBoardDao.class.getPackage().getName());

    EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("Sudokus");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    SudokuBoardRepository sudokuBoardRepository = new SudokuBoardRepository(entityManager);

    public JdbcSudokuBoardDao(String gameName) {
        this.gameName = gameName;
    }

    @Override
    public SudokuBoard read() {
        SudokuBoard sudokuBoard = sudokuBoardRepository.findByName(gameName);
        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard sudokuBoard) throws NameExistsException {
        if (sudokuBoardRepository.findIfExists(gameName)) {
            throw new NameExistsException("Such game is already in the database");
        }

        SudokuBoard newBoard = sudokuBoard.clone();
        newBoard.setGameName(gameName);

        List<SudokuField> fields = newBoard.twoDimensionArrayToList(newBoard.getBoardFieldsArray());

        for (SudokuField field : fields) {
            newBoard.addField(field);
        }

        sudokuBoardRepository.save(newBoard);
    }

    @Override
    public void close() {
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
        logger.info("Called finalize() method");
    }


}
