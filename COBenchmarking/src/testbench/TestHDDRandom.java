package testbench;

import benchmark.IBenchmark;
import benchmark.hdd.HDDRandomAccess;
import benchmark.hdd.HDDWriteSpeed;
import logging.ConsoleLogger;
import logging.ILogger;
import timing.ITimer;
import timing.TimeUnit;
import timing.Timer;

public class TestHDDRandom {
    public static void main(String[] args) {
        ITimer timer = new Timer();
        ILogger log = new ConsoleLogger();
        TimeUnit timeUnit = TimeUnit.SEC;
        IBenchmark bench = new HDDRandomAccess();
        long fileSize = 500000000;
        bench.initialize(fileSize);
        bench.run("r", "ft", 4 * 1024);
        log.write(bench.getResult(0) );
    }
}
