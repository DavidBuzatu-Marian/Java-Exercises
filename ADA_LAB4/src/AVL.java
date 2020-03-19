import java.util.Stack;

public class AVL {

    private Node root;

    public AVL() {
        root = null;
    }


    public Node rotateRight(Node root) {
        Node y = root.left;
        if (root.parent == null) {
            this.root = y;
        } else {

            if (root.equals(root.parent.right)) {
                root.parent.right = y;
            } else if (root.equals(root.parent.left)) {
                root.parent.left = y;
            }

        }
        y.parent = root.parent;
        root.parent = y;
        if (y.right != null) {
            y.right.parent = root;
        }
        root.left = y.right;
        y.right = root;

        root.height = Math.max(findHeight(root.left), findHeight(root.right));
        y.height = Math.max(findHeight(y.left), findHeight(y.right));
        return y;
    }

    public Node rotateLeft(Node root) {
        Node y = root.right;
        if (root.parent == null) {
            this.root = y;
        } else {
            if (root.equals(root.parent.right)) {
                root.parent.right = y;
            } else if (root.equals(root.parent.left)) {
                root.parent.left = y;
            }
        }
        y.parent = root.parent;
        root.parent = y;
        if (y.left != null) {
            y.left.parent = root;
        }
        root.right = y.left;
        y.left = root;

        root.height = Math.max(findHeight(root.left), findHeight(root.right));
        y.height = Math.max(findHeight(y.left), findHeight(y.right));
        return y;
    }

    public int findHeight(Node node) {
        if (node == null) {
            return 0;
        }
        int leftH = findHeight(node.left) + 1;
        int rightH = findHeight(node.right) + 1;

        node.height = Math.max(leftH, rightH);
        return node.height;
    }

    public int getBalanceFactor(Node node) {
        int leftH, rightH;
        if (node == null) {
            return 0;
        }
        leftH = node.left == null ? 0 : node.left.height;
        rightH = node.right == null ? 0 : node.right.height;
        return leftH - rightH;
    }

    public void insert(Node root, int k) {
        Node x, y = null;
        Stack<Node> nodes = new Stack<>();

        x = root;
        if(x == null) {
            this.root = new Node(k);
            return;
        }

        while(x != null) {
            y = x;
            nodes.add(x);

            if(x.key > k) {
                x = x.left;
            }
            if(x.key < k) {
                x = x.right;
            }
        }

        if(y.key > k) {
            y.left = new Node(k);
        } else {
            y.right = new Node(k);
        }
        findHeight(root);


        while(!nodes.isEmpty()) {
            Node s = nodes.pop();
            int balance = getBalanceFactor(s);
            if(s != null) {
                rebalanceAVL(s, k, balance);
            }
        }

    }

    private void rebalanceAVL(Node root, int key, int balance) {
        // left - left
        if (balance > 1 && key < root.left.key) {
            rotateRight(root);
            // right - right
        } else if (balance < -1 && key > root.right.key) {
            rotateLeft(root);
        } else if (balance > 1 && key > root.left.key) {
            root.left = rotateLeft(root.left);
            rotateRight(root);
        } else if (balance < -1 && key < root.right.key) {
            root.right = rotateRight(root.right);
            rotateLeft(root);
        }

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
        AVL tree = new AVL();

        tree.insert(tree.root, 10);
        tree.insert(tree.root, 20);
        tree.insert(tree.root, 30);
        tree.insert(tree.root, 40);
        tree.insert(tree.root, 50);

        tree.preOrder(tree.root);
        System.out.println();
        tree.inOrder(tree.root);
    }

}
