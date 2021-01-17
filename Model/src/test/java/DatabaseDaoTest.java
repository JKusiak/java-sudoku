import dao.JdbcSudokuBoardDao;
import exceptions.NameExistsException;
import exceptions.NoSuchFileException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import solver.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;
import sudoku.SudokuBoardRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseDaoTest {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Sudokus");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Test
    public void writeReadDBBoardSame() {
        SudokuBoard testBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        testBoard1.solveGame();
        SudokuBoard testBoard2 = null;

        JdbcSudokuBoardDao dao = new JdbcSudokuBoardDao("TestGame1");

        try {
            dao.write(testBoard1);
        } catch (NameExistsException e) {

        }
        testBoard2 = dao.read();

        assertEquals(testBoard1, testBoard2);
    }

    @Test
    public void writeToDBChangeDifferentBoards() {
        SudokuBoard testBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        testBoard1.solveGame();
        SudokuBoard testBoard2 = null;

        JdbcSudokuBoardDao dao = new JdbcSudokuBoardDao("TestGame2");

        try {
            dao.write(testBoard1);
        } catch (NameExistsException e) {

        }
        testBoard2 = dao.read();

        testBoard1.set(1,1, 0);
        assertNotEquals(testBoard1, testBoard2);
    }

    @Test
    public void checkFindByNameDifferentNames() {
        SudokuBoard testBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        testBoard1.solveGame();
        SudokuBoard testBoard2;

        JdbcSudokuBoardDao dao = new JdbcSudokuBoardDao("TestGame3");
        try {
            dao.write(testBoard1);
        } catch (NameExistsException e) {

        }
        testBoard2 = dao.read();

        assertEquals("TestGame3", testBoard2.getGameName());
    }


    @Test
    public void nameAlreadyInDBThrowsExceptionCorrect() {
        Assertions.assertThrows(NameExistsException.class, () -> {
            SudokuBoard testBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
            testBoard1.solveGame();
            SudokuBoard testBoard2 = new SudokuBoard(new BacktrackingSudokuSolver());
            testBoard1.solveGame();

            JdbcSudokuBoardDao dao1 = new JdbcSudokuBoardDao("TestGame4");

            dao1.write(testBoard1);
            dao1.write(testBoard2);
        });
    }
}
