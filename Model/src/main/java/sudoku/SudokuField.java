package sudoku;

import com.google.common.base.Objects;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "FIELDS")
public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {
    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "VALUE")
    private int value;

    @ManyToOne
    private SudokuBoard sudokuBoard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int newValue) {
        value = newValue;
    }

    public void setSudokuBoard(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    public SudokuBoard getSudokuBoard(SudokuBoard sudokuBoard) {
        return sudokuBoard;
    }

    public SudokuField() {

    }

    //----------------------------------------------------------------------------------

    // parametrized constructor adding value passed to it to the field object
    public SudokuField(int initVal) {
        setValue(initVal);
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
        return "SudokuBoard.SudokuField{"
                + "value="
                + value
                + '}';
    }

    // this method creates a copy of an object without maintaining connection to the
    // parent object, all changes in the first object after clone won't affect
    // object created from it
    @Override
    public SudokuField clone() throws CloneNotSupportedException {
        return (SudokuField) super.clone();
        //        return new SudokuField(value);
    }

    // compares two objects and returns values 1, 0, -1 depending on which object has higher value
    // implementing this interface allows to sort also arrays of objects, not numbers
    @Override
    public int compareTo(SudokuField sudokuField) {
        return Integer.compare(value, sudokuField.value);

        //        if (value > sudokuField.value){
        //            return 1;
        //        } else if (value == sudokuField.value) {
        //            return 0;
        //        }else {
        //            return -1;
        //        }
    }

}
