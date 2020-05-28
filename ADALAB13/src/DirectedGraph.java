import java.util.ArrayList;
import java.util.List;

public class DirectedGraph implements Graph {
    private int nrVertices;
    List<List<Integer>> adjList;

    public DirectedGraph(int nrVertices) {
        adjList = new ArrayList<>();
        for(int i = 0; i < nrVertices; ++i) {
            adjList.add(new ArrayList<>());
        }
        this.nrVertices = nrVertices;
    }
    @Override
    public void addEdge(int s, int e) {
        adjList.get(s).add(e);
    }

    @Override
    public List<List<Integer>> getAdjList() {
        return adjList;
    }

    @Override
    public int getNrVertices() {
        return nrVertices;
    }
}
