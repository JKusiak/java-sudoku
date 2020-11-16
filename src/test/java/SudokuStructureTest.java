import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;


public class SudokuStructureTest {
    @Test
    public void consecutiveStructuresDefault_Same() {
        SudokuStructure firstStructure = new SudokuStructure();
        SudokuStructure secondStructure = new SudokuStructure();
        assertEquals(true, firstStructure.equals(secondStructure));
    }

    @Test
    public void consecutiveStructuresInput_Different() {
        SudokuStructure firstStructure = new SudokuStructure();
        SudokuStructure secondStructure = new SudokuStructure(new SudokuField[] {
           new SudokuField(1),
           new SudokuField(2),
           new SudokuField(3),
           new SudokuField(4),
           new SudokuField(5),
           new SudokuField(6),
           new SudokuField(7),
           new SudokuField(8),
           new SudokuField(9)
        });
        assertEquals(false, firstStructure.equals(secondStructure));
    }

}
