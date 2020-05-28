import java.util.PriorityQueue;

public class BFS {
    private static Integer[] parent;

    public static void bfs(int startIndex, Graph graph) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        boolean[] visited = new boolean[graph.getNrVertices()];
        parent = new Integer[graph.getNrVertices()];

        // print path from BFS
        System.out.println("Path in BFS: ");
        System.out.print(startIndex);

        priorityQueue.add(startIndex);
        visited[startIndex] = true;
        parent[startIndex] = null;

        while(!priorityQueue.isEmpty()) {
            int curNode = priorityQueue.remove();

            for(int i: graph.getAdjList().get(curNode)) {
                if(!visited[i]) {
                    parent[i] = curNode;
                    visited[i] = true;
                    priorityQueue.add(i);

                    //print next
                    System.out.print(" " + i);
                }
            }
        }

        //print newline
        System.out.println();
    }

    public static void printPath(int startIndex, int endIndex) {
        if(startIndex == endIndex) {
            System.out.println(startIndex);
        } else if(parent[endIndex] == null) {
            System.out.println("No path from " + startIndex + " to " + endIndex);
        } else {
            printPath(startIndex, parent[endIndex]);
            System.out.println(endIndex);
        }
    }

    public static void bfs(int startIndex, StairGraph graph) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        boolean[] visited = new boolean[graph.getNrVertices()];
        parent = new Integer[graph.getNrVertices()];


        priorityQueue.add(startIndex);
        visited[startIndex] = true;
        parent[startIndex] = null;

        while(!priorityQueue.isEmpty()) {
            int curNode = priorityQueue.remove();

            for(String neigh: graph.getAdjList().get(curNode)) {
                int i = graph.getIndex(neigh);
                if(!visited[i]) {
                    parent[i] = curNode;
                    visited[i] = true;
                    priorityQueue.add(i);
                }
            }
        }
    }

    public static void printPath(int startIndex, int endIndex, StairGraph graph) {
        if(startIndex == endIndex) {
            System.out.println(graph.getVertex(startIndex));
        } else if(parent[endIndex] == null) {
            System.out.println("No path from " + graph.getVertex(startIndex) + " to " + graph.getVertex(endIndex));
        } else {
            printPath(startIndex, parent[endIndex], graph);
            System.out.println(graph.getVertex(endIndex));
        }
    }
}
