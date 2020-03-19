public class Node {

    public int key, height;

    public Node left, right, parent;

    public Node(int key) {
        this.key = key;
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof Node)) {
            return false;
        }
        return ((Node) obj).key == this.key;
    }
}
