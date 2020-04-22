package testprograms;

import graphalgo.IMSTFinder;
import graphalgo.KruskalMSTFinder;
import graphalgo.PrimMSTFinderWithDistArr;
import graphalgo.PrimMSTFinderWithPriorityQueue;
import graphs.Edge;
import graphs.UndirectedGraph;

import java.io.IOException;
import java.util.Set;

public class MSTAlgorithmsAnalysis {

	private static void doMST(IMSTFinder aMSTFinder, UndirectedGraph g) {
		double startTime, endTime;
		System.out.println("\nStart running  a MST algorithm on a graph with "+
									 g.getNrNodes()+" nodes");		
		startTime=System.nanoTime();
		Set<Edge> result = aMSTFinder.FindMST(g);
		endTime=System.nanoTime();		
		System.out.println("Done in "+(endTime-startTime)/1000000 +"milisec");
		if (result != null) {
			double minTotalWeight = 0;
			for (Edge e : result) {
				minTotalWeight = minTotalWeight + e.weight();
				//System.out.println(e.toString());
			}

			System.out.println("Min total weight is " + minTotalWeight);
		}
	}

	
	public static void main(String[] args) {

		UndirectedGraph g=null;

		try {
			g = new UndirectedGraph("gr_dense_1000.txt");
		} catch (IOException e) {
			System.out.println("IO Exception reading graph data from file");
			e.printStackTrace();
		}
//		g.printGraph();
	
		IMSTFinder theMSTFinder;

		theMSTFinder = new PrimMSTFinderWithDistArr();
		doMST(theMSTFinder, g);

		theMSTFinder = new PrimMSTFinderWithPriorityQueue();
		doMST(theMSTFinder, g);

		theMSTFinder = new KruskalMSTFinder();
		doMST(theMSTFinder, g);
	}

}
