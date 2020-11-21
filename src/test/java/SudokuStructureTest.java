import org.junit.jupiter.api.Assertions;
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

    @Test
    public void tooManyElementsThrowExceptionCheck() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SudokuStructure secondStructure = new SudokuStructure(Arrays.asList(
                    new SudokuField(1),
                    new SudokuField(2),
                    new SudokuField(3),
                    new SudokuField(4),
                    new SudokuField(5),
                    new SudokuField(6),
                    new SudokuField(7),
                    new SudokuField(8),
                    new SudokuField(9),
                    new SudokuField(1)
            ));
        });
    }

    @Test
    public void differentStructuresEqualsFalseCheck() {
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

        assertFalse(firstStructure.equals(secondStructure));
    }

    @Test
    public void sameStructureEqualsTrueCheck() {
        SudokuStructure firstStructure = new SudokuStructure();
        SudokuStructure secondStructure = firstStructure;
        assertTrue(firstStructure.equals(secondStructure));
    }

    @Test
    public void differentStructuresSameValuesEqualsTrueCheck() {
        SudokuStructure firstStructure = new SudokuStructure(Arrays.asList(
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

        assertTrue(firstStructure.equals(secondStructure));
    }

    @Test
    public void differentStructureContentDifferentHashCodes() {
        SudokuStructure firstStructure = new SudokuStructure(Arrays.asList(
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
        SudokuStructure secondStructure = new SudokuStructure(Arrays.asList(
                new SudokuField(9),
                new SudokuField(8),
                new SudokuField(7),
                new SudokuField(6),
                new SudokuField(5),
                new SudokuField(4),
                new SudokuField(3),
                new SudokuField(2),
                new SudokuField(1)
        ));

        assertNotEquals(firstStructure.hashCode(), secondStructure.hashCode());
    }

    @Test
    public void sameStructureContentSameHashCodes() {
        SudokuStructure firstStructure = new SudokuStructure();
        SudokuStructure secondStructure = new SudokuStructure();

        assertEquals(firstStructure.hashCode(), secondStructure.hashCode());
    }

    @Test
    public void structureToStringCheck() {
        SudokuStructure structure = new SudokuStructure(Arrays.asList(
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

        String testString = "SudokuStructure{values=[SudokuField{value=1}, SudokuField{value=2}, "
        + "SudokuField{value=3}, SudokuField{value=4}, SudokuField{value=5}, SudokuField{value=6}, "
        + "SudokuField{value=7}, SudokuField{value=8}, SudokuField{value=9}]}";
        assertEquals(testString, structure.toString());
    }
}
