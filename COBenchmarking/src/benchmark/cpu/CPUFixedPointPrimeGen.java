package benchmark.cpu;

import benchmark.IBenchmark;

import java.util.ArrayList;
import java.util.List;

public class CPUFixedPointPrimeGen implements IBenchmark {
    private List<Boolean> isPrime = new ArrayList<>();
    private long primesFound = 0;
    private int limit;
    @Override
    public void initialize(Object... parameters) {
        limit = (int) parameters[0];
    }

    @Override
    public void run() {
        generatePrimes(limit);
    }

    private void generatePrimes(int limit) {
        long number = 3;
        long divisor;
        primesFound++;
        try {
            while(number < limit) {
                divisor = 3;
                if(isPrime(number, divisor)) {
                    primesFound++;
                }
                if(isPrime(number + 2, divisor)) {
                    primesFound++;
                }
                if(isPrime(number + 4, divisor)) {
                    primesFound++;
                }
                if(isPrime(number + 6, divisor)) {
                    primesFound++;
                }
                if(isPrime(number + 8, divisor)) {
                    primesFound++;
                }
                number += 10;

            }
        } catch(StackOverflowError ex) {
            System.out.println(primesFound + "; " + number);
        }
    }

    private boolean isPrime(long number, long div) {
        long halfN = number / 2;
        for(int i = 2; i < halfN; i++) {
           if(number % i == 0) {
               return false;
           }
        }
        return true;
    }

    @Override
    public void run(Object... parameters) {
        generatePrimes((int) parameters[0]);
    }

    @Override
    public void warmUp() {
        System.out.println("Warming up...");
        run(30000);
        System.out.println("Warmed up.");
        clean();
    }

    @Override
    public void clean() {
        primesFound = 0;
    }

    @Override
    public void cancel() {

    }

    @Override
    public String getResult(int type) {
        return String.valueOf(primesFound);
    }
}
