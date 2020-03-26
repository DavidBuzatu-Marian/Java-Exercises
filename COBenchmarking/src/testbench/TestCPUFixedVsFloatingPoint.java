package testbench;

import benchmark.IBenchmark;
import benchmark.cpu.CPUFixedVsFloatingPoint;
import benchmark.cpu.NumberRepresentation;
import logging.ConsoleLogger;

import logging.ILogger;
import timing.ITimer;
import timing.TimeUnit;
import timing.Timer;

public class TestCPUFixedVsFloatingPoint {

	public static void main(String[] args) {
		ITimer timer = new Timer();
		ILogger log = new ConsoleLogger();
		TimeUnit timeUnit = TimeUnit.MS;

		IBenchmark bench = new CPUFixedVsFloatingPoint();
		bench.initialize(10000000);
		bench.warmUp();

		timer.start();
		bench.run(NumberRepresentation.FIXED);
//		bench.run(NumberRepresentation.FLOATING);
		long time = timer.stop();
		log.writeTime("Finished in", time, timeUnit);
		log.write("Result is", bench.getResult(0));

		bench.clean();
		log.close();
	}
}
