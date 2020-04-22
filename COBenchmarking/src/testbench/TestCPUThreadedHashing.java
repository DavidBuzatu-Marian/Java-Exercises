package testbench;

import benchmark.IBenchmark;
import benchmark.cpu.CPUThreadedHashing;
import benchmark.cpu.HashManager;
import logging.ConsoleLogger;
import logging.ILogger;
import timing.ITimer;
import timing.TimeUnit;
import timing.Timer;

public class TestCPUThreadedHashing {
    public static void main(String[] args) {
        ITimer timer = new Timer();
        ILogger log = new ConsoleLogger();
        TimeUnit timeUnit = TimeUnit.SEC;
        IBenchmark bench = new CPUThreadedHashing();

        int maxLength = 10;
        int nThreads = 16;
        int hashCode = 276111076;

        timer.start();
        bench.run(maxLength, nThreads, hashCode);
        long time = timer.stop();
        log.writeTime("Finished in", time, timeUnit);

        log.write("Result is:", bench.getResult(0));
    }

}
