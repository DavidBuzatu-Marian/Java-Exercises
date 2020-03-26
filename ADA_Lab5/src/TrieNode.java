import java.util.ArrayList;
import java.util.List;

public class TrieNode {
    private boolean isWord;
    private Character letter;
    private List<TrieNode> children = new ArrayList<>();

    public boolean isWord() {
        return isWord;
    }

    public List<TrieNode> getChildren() {
        return children;
    }

    public void addChildren(TrieNode child) {
        children.add(child);
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    public void setWord(boolean word) {
        isWord = word;
    }
}
