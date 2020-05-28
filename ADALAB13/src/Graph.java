import java.util.List;

public interface Graph {
    public void addEdge(int s, int e);
    public List<List<Integer>> getAdjList();
    public int getNrVertices();
}
