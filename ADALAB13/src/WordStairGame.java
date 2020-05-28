import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordStairGame {

    public static void main(String[] args) {
        int nrVertices;
        StairGraph graph;
        List<String> words = new ArrayList<>();

        try {
            Scanner input = new Scanner(new File("english_words.txt"));
            nrVertices = input.nextInt();
            graph = new StairGraph(nrVertices);
            while (input.hasNext()) {
                words.add(input.next());
            }
            System.out.println("Constructing graph...");
            graph.getGraphFromList(words);
            System.out.println("Finished making graph");
            Scanner keyInput = new Scanner(System.in);
            System.out.println("Introduce words from the dictionary:");
            while (keyInput.hasNext()) {
                String word1 = keyInput.next();
                String word2 = keyInput.next();
                if (graph.getIndex(word1) == -1 || graph.getIndex(word2) == -1) {
                    System.out.println("Words not in dictionary");
                } else {
                    BFS.bfs(graph.getIndex(word1), graph);
                    BFS.printPath(graph.getIndex(word1), graph.getIndex(word2), graph);
                }
                System.out.println("Introduce words from the dictionary:");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
