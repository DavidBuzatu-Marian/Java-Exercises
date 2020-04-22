package priorityqueues;

public interface MyPriorityQueue {
    void decreaseKey(Integer n, double p);
    void insert(Integer n, double p);
    int extractMin();
    boolean isEmpty();
    boolean contains(Integer n);
    double priority(Integer n);
}
