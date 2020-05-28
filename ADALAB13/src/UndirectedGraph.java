import java.util.ArrayList;
import java.util.List;

public class UndirectedGraph implements Graph {
    private int nrVertices;
    List<List<Integer>> adjList;

    public UndirectedGraph(int nrVertices) {
        adjList = new ArrayList<>();
        for(int i = 0; i < nrVertices; ++i) {
            adjList.add(new ArrayList<>());
        }
        this.nrVertices = nrVertices;
    }

    public void addEdge(int s, int e) {
        adjList.get(s).add(e);
        adjList.get(e).add(s);
    }

    public List<List<Integer>> getAdjList() {
        return adjList;
    }

    public int getNrVertices() {
        return nrVertices;
    }
}
