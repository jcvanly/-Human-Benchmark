package MainPackage;

import java.util.Objects;

public class KeyValue {
    public int row;
    public int col;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyValue keyValue = (KeyValue) o;
        return row == keyValue.row && col == keyValue.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
