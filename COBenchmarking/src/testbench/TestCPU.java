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
        final int limit = 123456;
        benchmark.initialize(limit);
        benchmark.warmUp();
//        for (int i = 0; i < 1; i++) {
//            timer.start();
//            benchmark.run(0);
//
//            log.writeTime("PI1 -> Finished in ", timer.stop(), timeUnit);
//            log.write(benchmark.getResult(1));
//            benchmark.clean();
//        }
//        for (int i = 0; i < 1; i++) {
//            timer.start();
//            benchmark.run(1);
//
//            log.writeTime("PI2 -> Finished in ", timer.stop(), timeUnit);
//            log.write(benchmark.getResult(2));
//            benchmark.clean();
//        }
        for (int i = 0; i < 1; i++) {
            timer.start();
            benchmark.run(2);

            log.writeTime("PI3 -> Finished in ", timer.stop(), timeUnit);
            log.write(benchmark.getResult(3));
            benchmark.clean();
        }
    }
}
