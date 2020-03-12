package testbench;

import benchmark.IBenchmark;
import benchmark.cpu.CPUDigitsOfPi;
import logging.ConsoleLogger;
import logging.ILogger;
import timing.ITimer;
import timing.TimeUnit;
import timing.Timer;

public class TestCPU {
    public static void main(String[] args) {
        ITimer timer = new Timer();
        ILogger log = new ConsoleLogger();
        IBenchmark benchmark = new CPUDigitsOfPi();
        TimeUnit timeUnit = TimeUnit.SEC;
        final int limit = 10000;
        benchmark.initialize(limit);
        benchmark.warmUp();
        for (int i = 0; i < 20; i++) {
            timer.start();
            benchmark.run(0);

            log.writeTime("Finished in ", timer.stop(), timeUnit);
            log.write(((CPUDigitsOfPi) benchmark).getPI_1());
            benchmark.clean();
        }
        for (int i = 0; i < 20; i++) {
            timer.start();
            benchmark.run(1);

            log.writeTime("Finished in ", timer.stop(), timeUnit);
            log.write(((CPUDigitsOfPi) benchmark).getPI_2());
            benchmark.clean();
        }
    }
}
