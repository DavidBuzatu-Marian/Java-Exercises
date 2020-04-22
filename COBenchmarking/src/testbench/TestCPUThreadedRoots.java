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
        int workload = 1000;
        bench.initialize(workload);
        bench.warmUp();

        float z = -10;
        z += 11;
        double j = z / 2;
        System.out.println(j);

        for(int i = 1; i <= 32; i *= 2) {
            timer.start();
            bench.run(i);
            long time = timer.stop();
            log.writeTime("[t= " + i + "] Result " + bench.getResult(0) + "; Finished in", time, timeUnit);
            double score = (double)workload / (time * i);
            log.write("Score for " + i + " threads is: " + score);
            bench.clean();
        }
        bench.clean();
    }
}
