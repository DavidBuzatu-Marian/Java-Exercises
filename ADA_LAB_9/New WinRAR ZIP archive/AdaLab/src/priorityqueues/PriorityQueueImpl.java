package priorityqueues;

public class PriorityQueueImpl implements MyPriorityQueue {

	private int NMAX;
	private int N;
	private int[] pq;
	private int[] qp;
	private double[] keys;

	public PriorityQueueImpl(int NMAX) {
		this.NMAX = NMAX;
		keys = new double[NMAX + 1];
		pq = new int[NMAX + 1];
		qp = new int[NMAX + 1];
		for (int i = 0; i <= NMAX; i++)
			qp[i] = -1;

	}

	/**
	 * Decreases the priority of key n to a new priority p. If p is not smaller
	 * than the current priority of n, do nothing
	 */
	public void decreaseKey(Integer n, double p) {
		if (p < keys[n]) {
			keys[n] = p;
			swim(qp[n]);
		}
	}

	/**
	 * Insert key n with priority p
	 */
	public void insert(Integer n, double p) {
		N++;
		qp[n] = N;
		pq[N] = n;
		keys[n] = p;
		swim(N);
	}

	/**
	 * Removes the key with minimum priority and returns it
	 */
	public int extractMin() {
		int min = pq[1];
		exch(1, N--);
		sink(1);
		qp[min] = -1;

		pq[N + 1] = -1;
		return min;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public boolean contains(Integer n) {
		return qp[n] != -1;
	}

	public double priority(Integer n) {
		return keys[n];
	}

	private void swim(int k) {
		while (k > 1 && greater(k / 2, k)) {
			exch(k, k / 2);
			k = k / 2;
		}
	}

	private void sink(int k) {
		while (2 * k <= N) {
			int j = 2 * k;
			if (j < N && greater(j, j + 1))
				j++;
			if (!greater(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}

	private void exch(int i, int j) {
		int swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}

	private boolean greater(int i, int j) {
		return (keys[pq[i]] > keys[pq[j]]);
	}

}
