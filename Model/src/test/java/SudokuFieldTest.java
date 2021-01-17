import sudoku.SudokuField;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuFieldTest {
    @Test
    public void SudokuFieldConstructorValueCorrect() {
        SudokuField field = new SudokuField();
        assertEquals(0, field.getValue());
    }

    @Test
    public void SudokuFieldConstructorParameterizedValueCorrect() {
        SudokuField field = new SudokuField(1);
        assertEquals(1, field.getValue());
    }

    @Test
    public void differentFieldsEqualsFalseCheck() {
        SudokuField testField1 = new SudokuField(1);
        SudokuField testField2 = new SudokuField(5);
        assertFalse(testField1.equals(testField2));
    }

    @Test
    public void sameFieldEqualsTrueCheck() {
        SudokuField testField1 = new SudokuField(1);
        SudokuField testField2 = testField1;
        assertTrue(testField1.equals(testField2));
    }

    @Test
    public void differentFieldsSameValueEqualsTrueCheck() {
        SudokuField testField1 = new SudokuField(1);
        SudokuField testField2 = new SudokuField(1);
        assertTrue(testField1.equals(testField2));
    }

    @Test
    public void differentFieldsDifferentValuesEqualsFalseCheck() {
        SudokuField testField1 = new SudokuField(1);
        SudokuField testField2 = new SudokuField(7);
        assertFalse(testField1.equals(testField2));
    }

    @Test
    public void differentFieldsDifferentHashCodesCheck() {
        SudokuField testField1 = new SudokuField(1);
        SudokuField testField2 = new SudokuField(5);
        assertNotEquals(testField1.hashCode(), testField2.hashCode());
    }

    @Test
    public void sameFieldSameHashCodeCheck() {
        SudokuField testField1 = new SudokuField(1);
        SudokuField testField2 = testField1;
        assertEquals(testField1.hashCode(), testField2.hashCode());
    }

    @Test
    public void toStringSudokuFieldCheck() {
        SudokuField testField = new SudokuField(1);
        String testString = "SudokuBoard.SudokuField{value=1}";
        assertEquals(testString, testField.toString());
    }

    @Test
    public void sudokuFieldCloneCorrect() throws CloneNotSupportedException {
        SudokuField testField1 = new SudokuField(1);
        SudokuField testField2= testField1.clone();

        assertNotSame(testField1, testField2);
        assertEquals(testField1, testField2);
    }

    @Test
    public void sudokuFieldComparisonCorrect() {
        SudokuField testField1 = new SudokuField(1);
        SudokuField testField2 = new SudokuField(9);

        assertEquals(1, testField2.compareTo(testField1));
        assertEquals(0, testField1.compareTo(testField1));
        assertEquals(-1, testField1.compareTo(testField2));
    }
}
