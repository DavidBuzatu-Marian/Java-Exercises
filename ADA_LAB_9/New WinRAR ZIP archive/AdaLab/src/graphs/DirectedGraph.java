package graphs;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * DirectedGraph implements ISimpleGraph. It has a fixed number N of nodes.
 * Nodes are numbered from 0 to N-1.
 * 
 * A DirectedGraph can be created either as a graph with N nodes and no edges,
 * or as a graph with nodes and edges as read from a file. Edges can be added
 * between existing nodes.
 * 
 */
public class DirectedGraph implements ISimpleGraph {

	protected int N; // number of nodes
	protected int M; // number of edges

	/**
	 * The graph is represented as an adjacency structure.
	 * 
	 * At g[i] there is the collection of nodes j that are adjacent to i, each
	 * node j with its distance from i (the weight of the edge i->j)
	 */
	protected Map<Integer, Double>[] g = null;

	/**
	 * Constructs a graph having N nodes and no edges.
	 * 
	 * @param N
	 */
	public DirectedGraph(int N) {
		this.N = N;
		M = 0;
		g = (Map<Integer, Double>[]) new Map[N];
		for (int v = 0; v < N; v++) {
			g[v] = new HashMap<Integer, Double>();
		}
	}

	/**
	 * Constructs a graph with N nodes and M edges, reading from file.
	 * 
	 * The expected format of the file is:
	 * 
	 * On the first line, the number of nodes.
	 * 
	 * On the next lines, the edges given as node1 node2 weight
	 * 
	 * @param file
	 * @throws IOException
	 */
	public DirectedGraph(String file) throws IOException {
		readGraph(file);
	}

	/**
	 * Adds a directed edge
	 */
	public void addEdge(int from, int to, double weight) {
		if (hasNode(from) && hasNode(to)) {
			g[from].put(to, weight);
			M++;
		}
	}

	public int getNrNodes() {
		return N;
	}

	public int getNrEdges() {
		return M;
	}

	public boolean hasNode(int node) {
		if ((node >= 0) && (node < N))
			return true;
		else
			return false;
	}

	public boolean hasEdge(int from, int to) {
		if (hasNode(from) && hasNode(to))
			if (g[from].containsKey(to))
				return true;
		return false;
	}

	public void readGraph(String file) throws IOException {
		File input = new File(file);
		Scanner is = new Scanner(input);

		N = is.nextInt();
		M = 0;
		g = (Map<Integer, Double>[]) new Map[N];
		for (int v = 0; v < N; v++) {
			g[v] = new HashMap<Integer, Double>();
		}

		System.out.println("Reading graph with " + N + " nodes from file "
				+ file + " ...");

		int from, to;
		double weight;

		while (is.hasNext()) {
			from = is.nextInt();
			to = is.nextInt();

			weight = is.nextFloat();

			addEdge(from, to, weight);

		}
	}

	public void printGraphAdjStructure() {
		for (int s = 0; s < N; s++) {
			System.out.print(s + " : ");
			for (Integer t : g[s].keySet()) {
				System.out.print(t + " ");
			}
			System.out.println();
		}
	}

	public Iterable<Integer> nodesAdiacentTo(int node) {
		if ((node >= 0) && (node < N))
			return g[node].keySet();
		return null;
	}

	public Iterable<Edge> edgesOutgoingFrom(int node) {
		Set<Edge> edgeSet = new HashSet<Edge>();
		for (Map.Entry<Integer, Double> e : g[node].entrySet()) {
			Edge ed = new Edge(node, e.getKey(), e.getValue());
			edgeSet.add(ed);
		}
		return edgeSet;
	}

	public Iterable<Edge> allEdges() {
		Set<Edge> edgeSet = new HashSet<Edge>();
		for (int node = 0; node < N; node++)
			for (Map.Entry<Integer, Double> e : g[node].entrySet()) {
				Edge ed = new Edge(node, e.getKey(), e.getValue());
				edgeSet.add(ed);
			}
		return edgeSet;
	}
	
	public UndirectedGraph getAsUndirectedGraph(){
		UndirectedGraph ug=new UndirectedGraph(N);
		
		for (int node = 0; node < N; node++)
			for (Map.Entry<Integer, Double> e : g[node].entrySet()) {
				ug.addEdge(node, e.getKey(), e.getValue());
			}
		return ug;
	}
}
