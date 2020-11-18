import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuFieldTest {
    @Test
    public void SudokuFieldConstructorValueCorrect() {
        SudokuField field = new SudokuField();
        assertEquals(0, field.getFieldValue());
    }

    @Test
    public void SudokuFieldConstructorParameterizedValueCorrect() {
        SudokuField field = new SudokuField(1);
        assertEquals(1, field.getFieldValue());
    }
}
