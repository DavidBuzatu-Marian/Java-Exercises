package testprograms;

import graphalgo.IMSTFinder;
import graphalgo.PrimMSTFinderWithDistArr;
import graphs.DirectedGraph;
import graphs.Edge;
import graphs.UndirectedGraph;

import java.io.IOException;
import java.util.Set;

public class GraphDemo {

	/**
	 * A graph test program
	 */
	public static void main(String[] args) {
		UndirectedGraph ug=null;
		DirectedGraph dg=null;

		try {
			ug = new UndirectedGraph("demo1.txt");
			dg = new DirectedGraph("demo1.txt");
		} catch (IOException e) {
			System.out.println("IO Exception reading graph data from file");
			e.printStackTrace();
		}

		System.out.println("Undirected graph:");
		ug.printGraphAdjStructure();
		
		System.out.println("Directed graph:");
		dg.printGraphAdjStructure();
		
		Set<Edge> result;
		double minTotalWeight;
		System.out.println("\nMST of undirected graph:");
		IMSTFinder mst=new PrimMSTFinderWithDistArr();
		result=mst.FindMST(ug);
		
		minTotalWeight = 0;
		for (Edge e : result) {
			minTotalWeight = minTotalWeight + e.weight();
			System.out.println(e.toString());
		}
		System.out.println("Min total weight is " + minTotalWeight);
		
		System.out.println("\nMST of directed graph, after transformed into undirected:");
		result=mst.FindMST(dg.getAsUndirectedGraph());
		minTotalWeight = 0;
		for (Edge e : result) {
			minTotalWeight = minTotalWeight + e.weight();
			System.out.println(e.toString());
		}
		System.out.println("Min total weight is " + minTotalWeight);
		
		
	}

}
