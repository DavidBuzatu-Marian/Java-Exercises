package testbench;

import benchmark.IBenchmark;
import benchmark.cpu.CPUFixedPointPrimeGen;
import logging.ConsoleLogger;
import logging.ILogger;
import timing.ITimer;
import timing.TimeUnit;
import timing.Timer;

public class TestCPUFixedPointPrime {
    public static void main(String[] args) {
        ITimer timer = new Timer();
        ILogger log = new ConsoleLogger();
        IBenchmark benchmark = new CPUFixedPointPrimeGen();
        TimeUnit timeUnit = TimeUnit.SEC;
        int limit = 400000;

        benchmark.initialize(limit);
        benchmark.warmUp();

        timer.start();
        benchmark.run();
        long timeEnd = timer.stop();
        log.writeTime("Finished in ", timer.stop(), timeUnit);
        log.write(benchmark.getResult(0));
        benchmark.clean();

        double score = (double)(limit) / Math.round((Math.pow(timeEnd, 1.0 / 3.0)));
        log.write("Score is: " + score);
    }
}
