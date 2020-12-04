package sudoku;

import com.google.common.base.Objects;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// This class works as column, row and box at once, as all of them are in fact 9 elements long
// arrays of SudokuBoard.SudokuField objects
public class SudokuStructure implements Serializable {
    private List<SudokuField> values = Arrays.asList(new SudokuField[SudokuBoard.dimension]);

    // parametrized constructor that adds values passed to it to the structure object
    public SudokuStructure(List<SudokuField> values) {
        if (this.values.size() != values.size()) {
            throw new IllegalArgumentException(
                    "Sudoku structure must contain exactly " + this.values.size() + "elements"
            );
        }

        Collections.copy(this.values, values);
    }

    // non - parametrized constructor passing ordered values from 1 to 9 to the structure object
    public SudokuStructure() {
        for (int i = 0; i < values.size(); i++) {
            values.set(i, new SudokuField());
        }
    }

    // add all 9 elements from the list to a hash set, if any repeats not possible and return false
    public boolean verify() {
        boolean uniqueStructure = true;
        Set<Integer> testSet = new HashSet<>();

        for (int i = 0; i < SudokuBoard.dimension; i++) {
            if (testSet.add(values.get(i).getFieldValue()) == false) {
                uniqueStructure = false;
            }
        }

        return uniqueStructure;
    }


    // overridden method for testing purposes

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SudokuStructure)) {
            return false;
        }
        SudokuStructure that = (SudokuStructure) o;
        return Objects.equal(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(values);
    }

    @Override
    public String toString() {
        return "SudokuBoard.SudokuBoard.SudokuStructure{"
                + "values="
                + values
                + '}';
    }
}
