
import java.util.HashSet;
import java.util.Set;


public class PrimMSTFinderWithPriorityQueue implements IMSTFinder {
	private boolean visited[];
	private Edge[] parent;
	private int N;
	private PriorityQueueImpl priorityQueue;
	
	public Set<Edge> FindMST(UndirectedGraph g) {
		Set<Edge> result = new HashSet<>();
		System.out.println("PrimMSTFinderWithPriorityQueue");

		N = g.getNrNodes();
		priorityQueue = new PriorityQueueImpl(N);

		visited = new boolean[N];
		parent = new Edge[N];
		for(int v = 0; v < N; ++v) {
			visited[v] = false;
			parent[v] = null;
			priorityQueue.insert(v, Double.POSITIVE_INFINITY);
		}

		// choose starting node
		int root = 0;
		doPrim(g, root);

		for(int v = 0; v < N; ++v) {
				result.add(parent[v]);
			}

		return result;
	}

	private void doPrim(UndirectedGraph g, int root) {
		priorityQueue.decreaseKey(root, 0);

		while(!priorityQueue.isEmpty()) {
			int u = priorityQueue.extractMin();
			visited[u] = true;

			for(Edge e: g.edgesOutgoingFrom(u)) {
				int v = e.other(u);
				if(!visited[v] && e.weight() < priorityQueue.priority(v)) {
					priorityQueue.decreaseKey(v, e.weight());
					parent[v] = e;
				}
			}
		}
	}

}
