package graphs;

public class Edge implements Comparable<Edge> {
	private int from;
	private int to;
	private double weight;

	public Edge(int from, int to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public int source() {
		return from;

	}

	public int other(int v) {
		if (v == from)
			return to;
		else
			return from;
	}

	public double weight() {
		return weight;
	}

	public String toString() {
		String result = new String(from + "->" + to);
		return result;
	}

	@Override
	public int compareTo(Edge that) {
		if (this.weight() < that.weight())
			return -1;
		else if (this.weight() > that.weight())
			return 1;
		else
			return 0;
	}
}
