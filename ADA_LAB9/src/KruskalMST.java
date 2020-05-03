import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KruskalMST implements IMSTFinder {
    private DisjointSet disjointSet;
    private Edge[] edgeList;
    private int counter;

    @Override
    public Set<Edge> FindMST(UndirectedGraph g) {
        Set<Edge> result = new HashSet<>();
        System.out.println("KruskalMST with Disjoint Set");

        disjointSet = new DisjointSet(g.getNrNodes());
        edgeList = new Edge[g.getNrEdges()];
        for (Edge e : g.allEdges()) {
            edgeList[counter++] = e;
        }

        for(int i = 0; i < g.getNrNodes(); ++i) {
            disjointSet.makeSet(i);
        }
        // sort edges
        quickSort(edgeList, 0, counter - 1);
        for(int i = 0; i < counter; ++i) {
            Edge edge = edgeList[i];
            int u = edge.source();
            int v = edge.other(u);
            if(disjointSet.findSet(u) != disjointSet.findSet(v)) {
                result.add(edge);
                disjointSet.union(u, v);
            }
        }

        return result;
    }

    public void quickSort(Edge[] edgeList, int low, int high) {
        if (low < high) {
            int part = partition(edgeList, low, high);
            quickSort(edgeList, low, part - 1);
            quickSort(edgeList, part, high);
        }
    }

    private int partition(Edge[] edgeList, int low, int high) {
        int i = low, j = high;
        Edge pivot = edgeList[low + (high - low) / 2];
        while (i <= j) {
            while (edgeList[i].weight() < pivot.weight()) {
                ++i;
            }
            while (edgeList[j].weight() > pivot.weight()) {
                --j;
            }
            if (i <= j) {
                swap(edgeList, i, j);
                ++i;
                --j;
            }
        }
        return i;
    }

    private void swap(Edge[] edgeList, int e1, int e2) {
        Edge aux = edgeList[e1];
        edgeList[e1] = edgeList[e2];
        edgeList[e2] = aux;
    }
}
