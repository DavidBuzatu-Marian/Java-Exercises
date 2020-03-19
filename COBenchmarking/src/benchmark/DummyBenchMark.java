package benchmark;


import java.util.Random;

public class DummyBenchMark implements IBenchmark {
    private int param;
    private Random random = new Random();
    private int[] numbers;
    private boolean running;

    @Override
    public void initialize(Object... parameters) {
        param = (int) parameters[0];
        numbers = random.ints(param, 10, 100000).toArray();
    }

    @Override
    public void run() {
        running = true;
        for(int i = 0; i < param && running; i++) {
            for(int j = i + 1; j < param && running; j++) {
                if(numbers[i] > numbers[j]) {
                    int aux = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = aux;
                }
            }
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
        random = null;
        param = 0;
        numbers = null;
        running = false;
    }

    @Override
    public void cancel() {
        running = false;
    }

    @Override
    public String getResult() {
        return null;
    }
}
