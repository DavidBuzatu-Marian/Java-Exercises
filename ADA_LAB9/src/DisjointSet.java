public class DisjointSet {
    private DSet[] sets;

    public DisjointSet(int nrVertices) {
        sets = new DSet[nrVertices];
    }

    public void makeSet(int pos) {
        sets[pos] = new DSet(pos, 0);
    }

    public void union(int u, int v) {
        link(findSet(u), findSet(v));
    }

    public int findSet(int u) {
        if(u != sets[u].getParent()) {
            sets[u].setParent(findSet(sets[u].getParent()));
        }
        return sets[u].getParent();
    }

    private void link(int u, int v) {
        if(sets[u].getRank() > sets[v].getRank()) {
            sets[v].setParent(u);
        } else {
            sets[u].setParent(v);
        }
        if(sets[u].getRank() == sets[v].getRank()) {
            sets[v].setRank(sets[v].getRank() + 1);
        }
    }

}
