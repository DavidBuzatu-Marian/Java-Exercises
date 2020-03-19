import java.util.Random;
import java.util.Stack;
import java.util.TreeMap;

public class AVL2 {

    private Node root;

    public AVL2() {
        root = null;
    }


    public Node rotateRight(Node root) {
        Node y = root.left;
//        if (root.parent == null) {
//            this.root = y;
//        } else {
//
//            if (root.equals(root.parent.right)) {
//                root.parent.right = y;
//            } else if (root.equals(root.parent.left)) {
//                root.parent.left = y;
//            }
//
//        }
//        y.parent = root.parent;
//        root.parent = y;
//        if (y.right != null) {
//            y.right.parent = root;
//        }

        root.left = y.right;
        y.right = root;

        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        if (root.equals(this.root)) {
            this.root = y;
        }
        return y;
    }

    public Node rotateLeft(Node root) {
        Node y = root.right;
//        if (root.parent == null) {
//            this.root = y;
//        } else {
//            if (root.equals(root.parent.right)) {
//                root.parent.right = y;
//            } else if (root.equals(root.parent.left)) {
//                root.parent.left = y;
//            }
//        }
//        y.parent = root.parent;
//        root.parent = y;
//        if (y.left != null) {
//            y.left.parent = root;
//        }
        root.right = y.left;
        y.left = root;

        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        if (root.equals(this.root)) {
            this.root = y;
        }
        return y;
    }

//    public int findHeight(Node node) {
//        if (node == null) {
//            return 0;
//        }
//        int leftH = findHeight(node.left) + 1;
//        int rightH = findHeight(node.right) + 1;
//
//        node.height = Math.max(leftH, rightH);
//        return node.height;
//    }

    public int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    public int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    public Node insert(Node node, int k) {
        if (node == null) {
            Node newNode = new Node(k);
            newNode.height = 1;
            return newNode;
        }

        // Just like BST
        if (node.key > k) {
            node.left = insert(node.left, k);
        } else if (node.key < k) {
            node.right = insert(node.right, k);
        } else {
            return node;
        }

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        int balance = getBalanceFactor(node);
        return rebalanceAVL(node, k, balance);
    }

    private Node rebalanceAVL(Node root, int key, int balance) {
        // left - left
        if (balance > 1 && key < root.left.key) {
            return rotateRight(root);
            // right - right
        } else if (balance < -1 && key > root.right.key) {
            return rotateLeft(root);
        } else if (balance > 1 && key > root.left.key) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        } else if (balance < -1 && key < root.right.key) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }
        return root;
    }

    public void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.key + " ");
            inOrder(root.right);
        }

    }


    public static void main(String[] args) {
        AVL2 tree = new AVL2();
        int[] numbers = new int[1000000];
        for(int i = 0; i < 1000000; i++) {
            numbers[i] = i;
        }

//        System.out.println("Got numbers");
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            tree.root = tree.insert(tree.root, numbers[i]);
        }
        long endTime = System.nanoTime();
        System.out.println("Time for AVL " + (double) (endTime - startTime) / 1000000000);
        System.out.println("Height of AVL " + tree.getHeight(tree.root));

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            treeMap.put(numbers[i], numbers[i]);
        }
        endTime = System.nanoTime();
        System.out.println("Time for TreeMap " + (double)(endTime - startTime) / 1000000000);

    }

}
