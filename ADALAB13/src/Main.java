import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int nrVertices;
        Graph graph;
        try {
            Scanner input = new Scanner(new File("input_dfs.txt"));
            nrVertices = input.nextInt();
            graph = new UndirectedGraph(nrVertices);
            while(input.hasNext()) {
                int from = input.nextInt();
                int to = input.nextInt();
                graph.addEdge(from, to);
            }

//            BFS.bfs(2, graph);
//            BFS.printPath(2, 7);

            DFS.dfs(2, graph);
            DFS.printPath(2, 5);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
