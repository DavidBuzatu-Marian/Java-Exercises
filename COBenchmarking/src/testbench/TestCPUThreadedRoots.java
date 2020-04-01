package testbench;

import benchmark.IBenchmark;
import benchmark.cpu.CPUThreadedRoots;
import logging.ConsoleLogger;
import logging.ILogger;
import timing.ITimer;
import timing.TimeUnit;
import timing.Timer;

public class TestCPUThreadedRoots {
    public static void main(String[] args) {
        ITimer timer = new Timer();
        ILogger log = new ConsoleLogger();
        TimeUnit timeUnit = TimeUnit.SEC;

        IBenchmark bench = new CPUThreadedRoots();
        bench.initialize(100000000);
        bench.warmUp();

        for(int i = 1; i <= 8; i *= 2) {
            timer.start();
            bench.run(i);
            long time = timer.stop();
            log.writeTime("[t= " + i + "] Result " + bench.getResult(0) + "; Finished in", time, timeUnit);
        }
        bench.clean();
    }
}
