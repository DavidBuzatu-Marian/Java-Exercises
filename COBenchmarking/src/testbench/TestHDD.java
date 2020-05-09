package testbench;

import benchmark.DummyBenchMark;
import benchmark.IBenchmark;
import benchmark.cpu.CPUThreadedHashing;
import benchmark.hdd.HDDWriteSpeed;
import logging.ConsoleLogger;
import logging.ILogger;
import timing.ITimer;
import timing.TimeUnit;
import timing.Timer;

public class TestHDD {
    public static void main(String[] args) {
        ITimer timer = new Timer();
        ILogger log = new ConsoleLogger();
        TimeUnit timeUnit = TimeUnit.SEC;
        IBenchmark bench = new HDDWriteSpeed();

        timer.start();
        bench.run("fb", true);
        long time = timer.stop();
        log.writeTime("Finished in", time, timeUnit);
    }
}
