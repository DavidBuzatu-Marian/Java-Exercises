import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Problem1 {
    private static Integer[] parents, visited;
    private static Double[] distances;
    private static PriorityQueueImpl priorityQueue;

    public static void main(String[] args) {
        DirectedGraph directedGraph = null;
        try {
            directedGraph = new DirectedGraph("input1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Graphs has: " + directedGraph.getNrEdges() + " edges");
        int startingNode = 0; // modify this in order to get a different starting point
        int destinationNode = 4; // modify this in order to get a different end point
        dijkstra(directedGraph, startingNode);
        printPath(parents, destinationNode);
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

    private static void dijkstra(DirectedGraph directedGraph, int startingNode) {
        initializeSource(directedGraph, startingNode);
        while (!priorityQueue.isEmpty()) {
            int curNode = priorityQueue.extractMin();
            visited[curNode] = 1;
            for (int neigh : directedGraph.nodesAdiacentTo(curNode)) {
                if (visited[neigh] == 0) {
                    relax(curNode, neigh, directedGraph.getEdgeWeight(curNode, neigh));
                }
            }
        }
    }

    private static void relax(int curNode, int neigh, Double edgeWeight) {
        if (distances[neigh] > distances[curNode] + edgeWeight) {
            distances[neigh] = distances[curNode] + edgeWeight;
            parents[neigh] = curNode;
            priorityQueue.decreaseKey(neigh, distances[neigh]);
        }
    }

    private static void initializeSource(DirectedGraph directedGraph, int startingNode) {
        priorityQueue = new PriorityQueueImpl(directedGraph.getNrNodes());
        distances = new Double[directedGraph.getNrNodes()];
        parents = new Integer[directedGraph.getNrNodes()];
        visited = new Integer[directedGraph.getNrNodes()];

        for (int i = 0; i < directedGraph.getNrNodes(); ++i) {
            distances[i] = Double.POSITIVE_INFINITY;
            parents[i] = null;
            visited[i] = 0;
            if (i != startingNode) {
                priorityQueue.insert(i, distances[i]);
            }
        }
        distances[startingNode] = 0.0;
        priorityQueue.insert(startingNode, 0.0);

    }
}
