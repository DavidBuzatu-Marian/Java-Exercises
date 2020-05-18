import java.io.IOException;

public class Problem2 {
    private static Integer[] parents, visited;
    private static Double[] distances;

    public static void main(String[] args) {
        DirectedGraph directedGraph = null;
        try {
            directedGraph = new DirectedGraph("input2.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Graphs has: " + directedGraph.getNrEdges() + " edges");
        int startingNode = 0; // modify this in order to get a different starting point
        int destinationNode = 2; // modify this in order to get a different end point
        if (!bellmanFord(directedGraph, startingNode)) {
            System.out.println("Graph contains negative cycles!");
        } else {
            printPath(parents, destinationNode);
        }
    }

    private static boolean bellmanFord(DirectedGraph directedGraph, int startingNode) {
        initializeSource(directedGraph, startingNode);
        for(int i = 0; i < directedGraph.getNrNodes(); ++i) {
            for(Edge edge: directedGraph.allEdges()) {
                relax(edge.source(), edge.other(edge.source()), edge.weight());
            }
        }
        for(Edge edge: directedGraph.allEdges()) {
            if(distances[edge.other(edge.source())] > distances[edge.source()] + edge.weight()) {
                return false;
            }
        }

        return true;
    }

    private static void printPath(Integer[] parent, int i) {
        if (parent[i] == null) {
            // base case (initial starting node)
            System.out.println("Node:" + i + "\tCost: " + distances[i]);
            return;
        }
        printPath(parent, parent[i]);
        System.out.println("Node:" + i + "\tCost: " + distances[i]);
    }


    private static void initializeSource(DirectedGraph directedGraph, int startingNode) {
        distances = new Double[directedGraph.getNrNodes()];
        parents = new Integer[directedGraph.getNrNodes()];
        visited = new Integer[directedGraph.getNrNodes()];

        for (int i = 0; i < directedGraph.getNrNodes(); ++i) {
            distances[i] = Double.POSITIVE_INFINITY;
            parents[i] = null;
            visited[i] = 0;
        }
        distances[startingNode] = 0.0;

    }

    private static void relax(int curNode, int neigh, Double edgeWeight) {
        if (distances[neigh] > distances[curNode] + edgeWeight) {
            distances[neigh] = distances[curNode] + edgeWeight;
            parents[neigh] = curNode;
        }
    }
}
