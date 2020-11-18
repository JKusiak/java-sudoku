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
}
