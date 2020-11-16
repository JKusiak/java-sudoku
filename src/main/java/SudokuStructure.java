import java.util.HashSet;
import java.util.Set;

// This class works as column, row and box at once, as all of them are in fact 9 elements long
// arrays of SudokuField objects

public class SudokuStructure {
    protected SudokuField[] values;

    SudokuStructure(SudokuField[] values) {
        this.values = values.clone();
    }

    SudokuStructure() {
        values = new SudokuField[SudokuBoard.dimension];
        for (int i = 0; i < values.length; i++) {
            values[i] = new SudokuField();
        }
    }

    public boolean verify() {
        boolean uniqueStructure = true;
        Set<SudokuField> testSet = new HashSet<>();

        for (int i = 0; i < SudokuBoard.dimension; i++) {
            if (testSet.add(values[i]) == false) {
                uniqueStructure = false;
            }
        }
        return uniqueStructure;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        SudokuStructure objStructure = (SudokuStructure) obj;

        for (int i = 0; i < values.length; i++) {
            if (objStructure.values[i].getFieldValue() != this.values[i].getFieldValue()) {
                return false;
            }
        }

        return true;
    }
}
