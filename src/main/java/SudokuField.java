import com.google.common.base.Objects;

public class SudokuField {
    private int value;

    // non - parametrized constructor passing 0 value to the field object
    SudokuField() {
        value = 0;
    }

    // parametrized constructor adding value passed to it to the field object
    SudokuField(int initVal) {
        setFieldValue(initVal);
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int newValue) {
        value = newValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SudokuField)) {
            return false;
        }
        SudokuField that = (SudokuField) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "SudokuField{"
                + "value="
                + value
                + '}';
    }
}
