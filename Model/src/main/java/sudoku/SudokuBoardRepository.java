package sudoku;

import java.util.Optional;
import javax.persistence.EntityManager;


public class SudokuBoardRepository {
    private EntityManager entityManager;

    public SudokuBoardRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<SudokuBoard> findById(Integer id) {
        SudokuBoard sudokuBoard = entityManager.find(SudokuBoard.class, id);
        return sudokuBoard != null
                ? Optional.of(sudokuBoard)
                : Optional.empty();
    }

    public SudokuBoard findByName(String gameName) {
        SudokuBoard sudokuBoard = entityManager.createQuery("SELECT board "
                                                + "FROM SudokuBoard board "
                                                + "WHERE board.gameName = :insertGame",
                                                SudokuBoard.class)
                                .setParameter("insertGame", gameName)
                                .getSingleResult();
        return sudokuBoard;
    }

    public boolean findIfExists(String gameName) {
        Boolean exists = entityManager.createQuery("SELECT CASE WHEN (COUNT(board) > 0) "
                + "THEN TRUE ELSE FALSE END "
                + "FROM SudokuBoard board WHERE board.gameName = :insertGame", Boolean.class)
                .setParameter("insertGame", gameName)
                .getSingleResult();
        return exists;
    }

    public void save(SudokuBoard sudokuBoard) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(sudokuBoard);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
