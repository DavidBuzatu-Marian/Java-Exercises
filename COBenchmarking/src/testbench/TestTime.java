package testbench;

import benchmark.IBenchmark;
import benchmark.TimeBenchmark;
import logging.ConsoleLogger;
import logging.ILogger;
import timing.ITimer;
import timing.TimeUnit;
import timing.Timer;

public class TestTime {

    public static void main(String[] args) {
        ITimer timer = new Timer();
        ILogger log = new ConsoleLogger();
        IBenchmark benchmark = new TimeBenchmark();
        TimeUnit timeUnit = TimeUnit.SEC;
        final int workload = 1000;
        benchmark.initialize(workload);
        for(int i = 0; i < 15; i++) {
            timer.resume();
            benchmark.run();
            double workloadTransf = workload * Math.pow(10, 6);
            long elapsed = timer.pause();
            double offset = (workloadTransf - elapsed) / workloadTransf;
            log.write("Run " + i + " : ", offset);
        }

        log.writeTime("Finished in ", timer.stop(), timeUnit);
    }
}
