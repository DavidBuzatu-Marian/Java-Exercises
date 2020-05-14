package testbench;

import benchmark.IBenchmark;
import benchmark.ram.VirtualMemoryBenchmark;
import logging.ConsoleLogger;
import logging.ILogger;

public class TestRAM {
    public static void main(String[] args) {
        ILogger logger = new ConsoleLogger();
        IBenchmark benchmark = new VirtualMemoryBenchmark();

        long fileSize = 12L * 1024 * 1024 * 1024;
        int bufferSize = 64 * 1024;

        benchmark.run(fileSize, bufferSize);
        logger.write(benchmark.getResult(0));
        benchmark.clean();
    }
}
