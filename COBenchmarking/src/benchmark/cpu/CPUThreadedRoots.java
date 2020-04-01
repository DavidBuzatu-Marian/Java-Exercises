package benchmark.cpu;

import benchmark.IBenchmark;

public class CPUThreadedRoots implements IBenchmark {
    private double result;
    private int size, nrCores;
    private boolean running;

    @Override
    public void initialize(Object... parameters) {
        size = (int) parameters[0];
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Method not implemented. Use run(Objects...) instead");
    }

    @Override
    public void run(Object... parameters) {
        int nrThreads = (int) parameters[0];
        final int jobPerThread = size / nrThreads;
        Thread[] threads = new Thread[nrThreads];
        running = true;
        for (int i = 0; i < nrThreads; i++) {
            threads[i] = new Thread(new SquareRootTask(i * jobPerThread + 1, (i + 1) * jobPerThread));
            threads[i].start();
        }

        for (int i = 0; i < nrThreads; i++) {
            // join threads
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void warmUp() {
        run(2);
        nrCores = Runtime.getRuntime().availableProcessors();
        System.out.println("Nr. of cores: " + nrCores);
    }

    @Override
    public void clean() {

    }

    @Override
    public void cancel() {
        running = false;
    }

    public synchronized void setResult(double result) {
        this.result += result;
    }

    @Override
    public String getResult(int type) {
        return String.valueOf(result);
    }


    class SquareRootTask implements Runnable {
        private int from, to;
        private final double precision = 1e-4;
        private double result = 0.0;

        public SquareRootTask(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public void run() {
            for (int i = from; i <= to && running; ++i) {
                result += getNewtonian(i);
            }

            setResult(result);
        }

        private double getNewtonian(double x) {
            double sum = x;
            while (Math.abs(Math.pow(sum, 2) - x) > precision) {
                sum = (x / sum + sum) / 2;
            }

            return sum;
        }


    }
}
