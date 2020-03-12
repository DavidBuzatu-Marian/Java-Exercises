import java.util.*;

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
        if (node == null) {
            return 0;
        }
        node.size = findSize(node.left) + findSize(node.right) + 1;
        return node.size;
    }

    public boolean isPerfectlyBalanced(Node node) {
        if (node == null) {
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
        if (node == null) {
            return false;
        }

        if (CheckExistTwoNodesWithSum(sum, node.left, sums)) {
            return true;
        }

        if (sums.contains(sum - node.key)) {
            return true;
        } else {
            sums.add(node.key);
        }
        return CheckExistTwoNodesWithSum(sum, node.right, sums);
    }


    public boolean findPath(Node root, Node node, List<Integer> path) {
        if (root == null) {
            return false;
        }

        path.add(root.key);
        if (root.key == node.key) {
            return true;
        }
        if (findPath(root.left, node, path) || findPath(root.right, node, path)) {
            return true;
        }

        path.remove(Integer.valueOf(root.key));
        return false;
    }

    public void PrintPathFromTo(Node root, Node node1, Node node2) {
        List<Integer> path1 = new ArrayList<>();
        List<Integer> path2 = new ArrayList<>();
        if (findPath(root, node1, path1) && findPath(root, node2, path2)) {
            int i = 0, j = 0, ancestor = 0;
            while (i < path1.size() && j < path2.size()) {
                if (i == j && path1.get(i).equals(path2.get(j))) {
                    i++;
                    j++;
                } else {
                    ancestor = j - 1;
                    break;
                }
            }
            for (i = path1.size() - 1; i > ancestor; i--) {
                System.out.print(path1.get(i) + " -> ");
            }
            for (j = ancestor; j < path2.size() - 1; j++) {
                System.out.print(path2.get(j) + " -> ");
            }
            System.out.println(path2.get(j));
        }
    }

    public void PrintPathsWithSum(Node node, List<Node> nodes, int s, int nodesSum) {
        nodesSum += node.key;
        nodes.add(node);
        if (nodesSum == s) {
            printPath(nodes);
        } else {
            if (node.left != null && nodesSum + node.left.key <= s) {
                PrintPathsWithSum(node.left, nodes, s, nodesSum);
            }
            if (node.right != null && nodesSum + node.right.key <= s) {
                PrintPathsWithSum(node.right, nodes, s, nodesSum);
            }
        }
        nodes.remove(node);
    }

    private void printPath(List<Node> nodes) {
        for (Node node : nodes) {
            System.out.print(node.key + " ");
        }
        System.out.println();
    }

    public void PrintLevels(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            System.out.print(curNode.key + " ");
            if (curNode.left != null) {
                queue.offer(curNode.left);
            }
            if (curNode.right != null) {
                queue.offer(curNode.right);
            }
        }
    }


    public Node CreateBalancedBSTfromSortedArray(int[] array, int left, int right) {
        if (left > right) {
            return null;
        }
        int middle = (left + right) / 2;
        Node root = new Node(array[middle]);

        root.left = CreateBalancedBSTfromSortedArray(array, left, middle - 1);
        root.right = CreateBalancedBSTfromSortedArray(array, middle + 1, right);

        return root;
    }


    public boolean IsPostOrderArray(int[] array, int start, int end) {
        if(end - start < 1) {
            return true;
        }
        int leftSubtree = start;
        while(array[leftSubtree] < array[end]) {
            leftSubtree++;
        }

        int rightSubtree = leftSubtree;
        while(rightSubtree < end && array[rightSubtree] > array[end]) {
            rightSubtree++;
        }
        if(rightSubtree < end) {
            return false;
        }


        return IsPostOrderArray(array, start, leftSubtree - 1) && IsPostOrderArray(array, leftSubtree, end - 1);

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


        st1.findSize(st1.root);

//        st1.inorder(st1.root);
        //System.out.println(st1.height(st1.root));

//        System.out.println(st1.isPerfectlyBalanced(st1.root));
//        st1.PrintPathFromTo(st1.root, new Node(4), new Node(18));
        //System.out.println(st1.CheckExistTwoNodesWithSum(20, st1.root, new HashSet<Integer>()));

//        st1.PrintPathsWithSum(st1.root, new ArrayList<>(), 65, 0);
//        st1.PrintLevels(st1.root);

        int[] sortedArr = new int[]{2, 4, 7, 10, 11, 34, 46};
        int[] postOrder = new int[]{1, 15, 2, 5, 8};
        Node balanced = st1.CreateBalancedBSTfromSortedArray(sortedArr, 0, sortedArr.length - 1);
        System.out.println(st1.IsPostOrderArray(postOrder, 0, postOrder.length - 1));
    }

}

