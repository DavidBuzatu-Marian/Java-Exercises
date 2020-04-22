package graphs;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * 
 * UndirectedGraph is a SimpleGraph. It is a specialization of the DirectedGraph, 
 * because every undirected edge is implemented by two directed edges.
 * 
 */
public class UndirectedGraph extends DirectedGraph {

	public UndirectedGraph(int N) {
		super(N);
	}

	public UndirectedGraph(String file) throws IOException {
		super(file);
	}

	/**
	 * Adds an edge in a undirected graph by adding two directed edges.
	 */
	public void addEdge(int from, int to, double weight) {
		if (hasNode(from) && hasNode(to)) {
			g[from].put(to, weight);
			g[to].put(from, weight); // in undirected graphs add 2
										// entries in the adjacency structure
			M++;
		}
	}

	/**
	 * Returns all edges of an undirected graph. An edge i->j is 
	 * reported only once, when i<j. 
	 */
	public Iterable<Edge> allEdges() {
		Set<Edge> edgeSet = new HashSet<Edge>();
		for (int node = 0; node < N; node++)
			for (Map.Entry<Integer, Double> e : g[node].entrySet()) {
				if (e.getKey() > node) { // in undirected graphs make sure to 
										 // list every edge only once
					Edge ed = new Edge(node, e.getKey(), e.getValue());
					edgeSet.add(ed);
				}
			}
		return edgeSet;
	}

}
