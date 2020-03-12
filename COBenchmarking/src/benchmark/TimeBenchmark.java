package benchmark;

public class TimeBenchmark implements IBenchmark {
    private int sleepVal;
    private boolean running;

    @Override
    public void initialize(Object... parameters) {
        sleepVal = (int) parameters[0];
    }

    @Override
    public void run() {
        try {
            running = true;
            Thread.sleep(sleepVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(Object... parameters) {
        run();
    }

    @Override
    public void warmUp() {

    }

    @Override
    public void clean() {

    }

    @Override
    public void cancel() {
        running = false;
    }
}
