package testbench;

import benchmark.DummyBenchMark;
import benchmark.IBenchmark;
import logging.ConsoleLogger;
import logging.ILogger;
import timing.ITimer;
import timing.TimeUnit;
import timing.Timer;

public class TestDummy {
    public static void main(String[] args) {
        ITimer timer = new Timer();
        ILogger log = new ConsoleLogger();
        IBenchmark benchmark = new DummyBenchMark();

        benchmark.initialize(100000);
        timer.start();
        benchmark.run();
        long totalTime = timer.stop();
        log.writeTime("Finished in", totalTime, TimeUnit.SEC);
    }
}
