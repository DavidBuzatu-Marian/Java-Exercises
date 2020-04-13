import java.util.ArrayList;
import java.util.List;

public class SkylineCoords {

    private List<Coords> skylines = new ArrayList<>();;

    public void add(Coords coord) {
        skylines.add(coord);
    }

    public void remove(Coords coord) {
        skylines.remove(coord);
    }

    public void remove(int pos) {
        skylines.remove(pos);
    }
    public List<Coords> get() {
        return skylines;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Coords coords: skylines) {
            sb.append("(")
                    .append(coords.getBegin())
                    .append("; ")
                    .append(coords.getHeight())
                    .append(") ");
        }
        return sb.toString();
    }
}
