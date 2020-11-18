import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class SudokuStructureTest {
    @Test
    public void consecutiveStructuresDefault_Same() {
        SudokuStructure firstStructure = new SudokuStructure();
        SudokuStructure secondStructure = new SudokuStructure();
        assertEquals(firstStructure, secondStructure);
    }

    @Test
    public void consecutiveStructuresInput_Different() {
        SudokuStructure firstStructure = new SudokuStructure();
        SudokuStructure secondStructure = new SudokuStructure(Arrays.asList(
           new SudokuField(1),
           new SudokuField(2),
           new SudokuField(3),
           new SudokuField(4),
           new SudokuField(5),
           new SudokuField(6),
           new SudokuField(7),
           new SudokuField(8),
           new SudokuField(9)
        ));
        assertNotEquals(firstStructure, secondStructure);
    }
}
