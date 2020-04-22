package graphalgo;

import graphs.Edge;
import graphs.ISimpleGraph;
import graphs.UndirectedGraph;

import java.util.HashSet;

import java.util.Set;


public class PrimMSTFinderWithDistArr implements IMSTFinder {

	private double dist[];
	private boolean mark[];
	private Edge[] parent;
	private int N;

	/* 
	 * Find MST applying Prim's algorithm
	 * Uses an implementation with distance arrays
	 */
	public Set<Edge> FindMST(UndirectedGraph g) {

		System.out.println("PrimMSTFinderWithDistArr");
		N = g.getNrNodes();
	
		mark = new boolean[N];
		dist = new double[N];

		parent = new Edge[N];
		for (int v = 0; v < N; v++) {
			parent[v] = null;
			dist[v] = Double.POSITIVE_INFINITY;
			mark[v] = false;
		}

		int root=0; // any node can be picked as root 		
		doPrim(g, root);

		Set<Edge> result = new HashSet<Edge>();
		for (int v = 0; v < N; v++) {
			if (parent[v] != null) {
				result.add(parent[v]);
				}
		}
	
		return result;
	}

	
	 
	private int extractMin() {		
		double valmin = Double.POSITIVE_INFINITY;
		int indmin = -1;
		for (int i = 0; i < N; i++)
			if (!mark[i]){  // select the minimum distance node, from the
							// nodes that are still not marked
				if (dist[i] <= valmin) { // compares for smaller OR EQUAL
										 // to handle unconnected graphs 
					valmin = dist[i];
					indmin = i;
				}
			}
		mark[indmin]=true;
		dist[indmin]=0;
		return indmin;
	}

	private void doPrim(ISimpleGraph g, Integer root) {

		dist[root] = 0;
        int iter=0;
		while  (iter<N){ // iterate until all N nodes are added to the MST 
			iter++;
			
			int u = extractMin(); 
			
			for (Edge e : g.edgesOutgoingFrom(u)) {
				int v = e.other(u);
				if (dist[v] > e.weight()) {
					parent[v] = e;
					dist[v]= e.weight();
				}
			}
		}

	}
}
