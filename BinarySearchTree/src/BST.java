import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BST {

    private Node root;

    public BST() {
        root = null;
    }


    public void insert(int k) {
        Node x, y;
        Node z = new Node(k);
        y = null;
        x = root;
        while (x != null) {
            y = x;
            if (x.key - z.key < 0)
                x = x.right;
            else
                x = x.left;
        }
        z.p = y;
        if (y == null)
            root = z;
        else if (y.key - z.key < 0)
            y.right = z;
        else
            y.left = z;

    }

    public void inorder(Node node) {
        if (node == null)
            return;
        inorder(node.left);
        System.out.println(node.key);
        inorder(node.right);

    }

    public void preorder(Node node) {
        if (node == null)
            return;
        System.out.println(node.key);
        inorder(node.left);
        inorder(node.right);

    }

    public int height(Node node) {
        if (node == null)
            return 0;

        int lh = height(node.left) + 1;
        int rh = height(node.right) + 1;
        return Math.max(lh, rh);
    }


    public Node searchKey(Node node, Node searched) {
        if (node.key == searched.key)
            return node;

        if (node.key - searched.key > 0) {
            return searchKey(node.left, searched);
        }

        if (node.key - searched.key < 0) {
            return searchKey(node.right, searched);
        }
        return null;
    }


    public Node successor(Node node) {
        Node parent = node;
        if (node.right != null) {
            Node successor = node.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor;
        }

        while (parent.p != null) {
            if (parent == parent.p.left) {
                return parent.p;
            }
            parent = parent.p;
        }

        return null;
    }


    public int findSize(Node node) {
        if(node == null) {
            return 0;
        }
        node.size = findSize(node.left) + findSize(node.right) + 1;
        return node.size;
    }

    public boolean isPerfectlyBalanced(Node node) {
        if(node == null) {
            return true;
        }
        boolean isBalLeft = isPerfectlyBalanced(node.left);
        boolean isBalRight = isPerfectlyBalanced(node.right);

        int leftSize = node.left == null ? 0 : node.left.size;
        int rightSize = node.right == null ? 0 : node.right.size;
        return isBalLeft && isBalRight && (Math.abs(leftSize - rightSize) < 2);
    }

    public Node SearchClosest(Node node, Node closest, int k) {
        if (node == null) {
            return closest;
        }
        if (node.key > k) {
            if (closest == null) {
                closest = node;
            } else if (Math.abs(node.key - k) < Math.abs(closest.key - k)) {
                closest = node;
            }
            return SearchClosest(node.left, closest, k);
        } else if (node.key < k) {
            if (closest == null) {
                closest = node;
            } else if (Math.abs(k - node.key) < Math.abs(k - closest.key)) {
                closest = node;
            }
            return SearchClosest(node.right, closest, k);
        }
        return node;
    }

    public boolean CheckExistTwoNodesWithSum(int sum, Node node, Set<Integer> sums) {
        if(node == null) {
            return false;
        }

        if(CheckExistTwoNodesWithSum(sum, node.left, sums)) {
            return true;
        }

        if(sums.contains(sum - node.key)) {
            return true;
        } else {
            sums.add(node.key);
        }
        return CheckExistTwoNodesWithSum(sum, node.right, sums);
    }


    public boolean findPath(Node root, Node node, List<Integer> path) {
        if(root == null) {
            return false;
        }

        path.add(root.key);
        if(root.key == node.key) {
            return true;
        }
        if(findPath(root.left, node, path) || findPath(root.right, node, path)) {
            return true;
        }

        path.remove(Integer.valueOf(root.key));
        return false;
    }

    public void PrintPathFromTo(Node root, Node node1, Node node2) {
        List<Integer> path1 = new ArrayList<>();
        List<Integer> path2 = new ArrayList<>();
        if(findPath(root, node1, path1) && findPath(root, node2, path2) ) {
            int i = 0, j = 0, ancestor = 0;
            while(i < path1.size() && j < path2.size()) {
                if(i == j && path1.get(i) == path2.get(j)) {
                    i++;
                    j++;
                } else {
                    ancestor = j - 1;
                    break;
                }
            }
            for(i = path1.size() - 1; i > ancestor; i--) {
                System.out.print(path1.get(i) + " -> ");
            }
            for(j = ancestor; j < path2.size() - 1; j++) {
                System.out.print(path2.get(j) +  " -> ");
            }
            System.out.println(path2.get(j));
        }
    }

    public static void main(String[] args) {

        BST st1 = new BST();
        st1.insert(8);
        st1.insert(2);
        st1.insert(15);
        st1.insert(1);
        st1.insert(5);
        st1.insert(4);
        st1.insert(7);
        st1.insert(3);
        st1.insert(10);
        st1.insert(20);
        st1.insert(18);
        st1.insert(22);
        st1.inorder(st1.root);


        //System.out.println(st1.height(st1.root));
        st1.findSize(st1.root);
        System.out.println(st1.isPerfectlyBalanced(st1.root));
        st1.PrintPathFromTo(st1.root, new Node(4), new Node(18));
        //System.out.println(st1.CheckExistTwoNodesWithSum(20, st1.root, new HashSet<Integer>()));
    }

}

