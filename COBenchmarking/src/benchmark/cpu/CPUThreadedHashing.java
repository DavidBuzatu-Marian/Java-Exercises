package benchmark.cpu;

import benchmark.IBenchmark;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CPUThreadedHashing implements IBenchmark {
    private String result;
    volatile boolean running = true;

    @Override
    public void initialize(Object... parameters) {

    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Method not implemented. Use run(Object) instead");
    }

    @Override
    public void run(Object... parameters) {
        // maximum text length
        int maxTextLength = (Integer)parameters[0];
        // thread pool size
        int nThreads = (Integer)parameters[1];
        // hash code
        int hashCode = (Integer)parameters[2];

        // try to break these hash codes (in ascending order of difficulty):
        // 524381996
        // 276111076
        // 904300281

        int length = 2;

        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        HashManager hasher = new HashManager();
        String text = "aa";

        while(running) {
            HashBreakerTask worker = new HashBreakerTask(hasher, text, hashCode);
            executor.execute(worker);

            text = hasher.getNextString(text);

            if(length > maxTextLength) {
                running = false;
            }

            if(text == null) {
                ++length;
                text = "aa";
                for(int i = 2; i < length; ++i) {
                    text += "a";
                }
            }
        }

        executor.shutdown();
        while(!executor.isTerminated());
    }

    @Override
    public void warmUp() {
//        run(4, 16, 358807485);
    }

    @Override
    public void clean() {

    }

    @Override
    public void cancel() {

    }

    @Override
    public String getResult(int type) {
        return String.valueOf(result);
    }

    class HashBreakerTask implements Runnable {
        private final HashManager hasher;
        private final String text;
        private final int expectedHash;

        public HashBreakerTask(HashManager hasher, String text, int expectedHash) {
            this.hasher = hasher;
            this.text = text;
            this.expectedHash = expectedHash;
        }

        @Override
        public void run() {
            if(expectedHash == hasher.hash(text)) {
                running = false;
                result = text;
            }
        }
    }
}
