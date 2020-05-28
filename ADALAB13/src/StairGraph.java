import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StairGraph {
    private List<List<String>> adjList;
    private Map<String, Integer> indexMap;
    private Map<Integer, String> indexStringMap;
    private int counter = 0;
    private int nrVertices;

    public StairGraph(int nrVertices) {
        this.nrVertices = nrVertices;
        adjList = new ArrayList<>();
        for (int i = 0; i < nrVertices; ++i) {
            adjList.add(new ArrayList<>());
        }
        indexMap = new HashMap<>();
        indexStringMap = new HashMap<>();
    }

    public void addEdge(String s, String e) {
        adjList.get(getIndex(s)).add(e);
        adjList.get(getIndex(e)).add(s);
    }

    public int getIndex(String s) {
        return indexMap.getOrDefault(s, -1);
    }

    public List<List<String>> getAdjList() {
        return adjList;
    }

    public int getNrVertices() {
        return nrVertices;
    }

    public void getGraphFromList(List<String> words) {
        int wordsLen = words.size();
        putWordsInMap(words);
        for (int i = 0; i < wordsLen - 1; ++i) {
            for (int j = i + 1; j < wordsLen; ++j) {
                if (stairWords(words.get(i), words.get(j))) {
                    addEdge(words.get(i), words.get(j));
                }
            }
        }
    }

    private void putWordsInMap(List<String> words) {
        for (String word : words) {
            indexMap.put(word, counter);
            indexStringMap.put(counter, word);
            counter++;
        }
    }

    private boolean stairWords(String s1, String s2) {
        int cnt = 1; // 1 mistake allowed
        if (s1.length() != s2.length()) {
            return false;
        }
        for(int i = 0; i < s2.length(); ++i) {
            if(s1.charAt(i) != s2.charAt(i)) {
                --cnt;
            }
            if(cnt < 0) {
                return false;
            }
        }
        return true;
    }

    public String getVertex(int index) {
        return indexStringMap.get(index);
    }

}
