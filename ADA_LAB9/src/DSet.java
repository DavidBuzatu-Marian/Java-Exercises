import java.util.Objects;

public class DSet {
    private int parent, rank;

    public DSet(int parent, int rank) {
        this.parent = parent;
        this.rank = rank;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DSet dSet = (DSet) o;
        return parent == dSet.parent &&
                rank == dSet.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, rank);
    }
}
