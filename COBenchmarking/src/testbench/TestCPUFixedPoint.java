package testbench;

import benchmark.IBenchmark;
import benchmark.cpu.CPUFixedPoint;
import benchmark.cpu.CPUFixedPointMethod;
import benchmark.cpu.CPUFixedVsFloatingPoint;
import benchmark.cpu.NumberRepresentation;
import logging.ConsoleLogger;
import logging.ILogger;
import timing.ITimer;
import timing.TimeUnit;
import timing.Timer;

public class TestCPUFixedPoint {
    public static void main(String[] args) {
        ITimer timer = new Timer();
        ILogger log = new ConsoleLogger();
        TimeUnit timeUnit = TimeUnit.MS;

        IBenchmark bench = new CPUFixedPoint();
        int size = 10000000;
        bench.initialize(size);
        bench.warmUp();

        for(int i = 0; i < 10000;i ++) {
            timer.start();
//        bench.run(CPUFixedPointMethod.ACCESSIBILITY);
            bench.run(CPUFixedPointMethod.DYNAMIC);
//        bench.run(CPUFixedPointMethod.OPERATORS);
            long time = timer.stop();
            log.writeTime("Finished in", time, timeUnit);
            log.write("MOPS = ", (double) (CPUFixedPoint.DYNAMIC_OP * size) / (time * 1E6));
        }
//        timer.start();
//        bench.run(CPUFixedPointMethod.ACCESSIBILITY);
//        bench.run(CPUFixedPointMethod.DYNAMIC);
//        bench.run(CPUFixedPointMethod.OPERATORS);
//        long time = timer.stop();
//        log.writeTime("Finished in", time, timeUnit);
//        log.write("Result is", bench.getResult());
//        log.write("MOPS = ", (double) (CPUFixedPoint.DYNAMIC_OP * size) / (time * 1E6));
        bench.clean();
        log.close();
    }
}
