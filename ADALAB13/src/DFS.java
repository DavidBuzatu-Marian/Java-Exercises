import java.util.Stack;

public class DFS {
    private static Integer[] parent;

    public static void dfs(int startIndex, Graph graph) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.getNrVertices()];
        parent = new Integer[graph.getNrVertices()];

        // print path
        System.out.println("Path in DFS:");

        stack.push(startIndex);
        parent[startIndex] = null;

        while(!stack.isEmpty()) {
            int curNode = stack.pop();
            if(!visited[curNode]) {
                // print node
                System.out.print(curNode + " ");
                visited[curNode] = true;
            }
            for(int i: graph.getAdjList().get(curNode)) {
                if(!visited[i]) {
                    stack.push(i);
                    parent[i] = curNode;
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
}
