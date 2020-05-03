import java.io.IOException;

/**
 * Represents a simple graph having a fixed number N of nodes
 * Nodes are numbered from 0 to N-1
 * Edges can be added between existing nodes
 */
public interface ISimpleGraph {
	int getNrNodes();
	int getNrEdges();
	void addEdge(int from, int to, double weight);
	Iterable<Integer> nodesAdiacentTo(int node);
	Iterable<Edge> edgesOutgoingFrom(int node);
	Iterable<Edge> allEdges();
	boolean hasNode(int node);
	boolean hasEdge(int from, int to);
	void readGraph(String file) throws IOException;
	void printGraphAdjStructure(); 
	}
