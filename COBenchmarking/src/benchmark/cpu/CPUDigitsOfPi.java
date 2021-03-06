package benchmark.cpu;

import benchmark.IBenchmark;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;

public class CPUDigitsOfPi implements IBenchmark {
    // Simple Pi computation
    private BigInteger increment = BigInteger.ONE;
    private BigInteger even = BigInteger.TWO;
    private BigInteger zero = BigInteger.ZERO;
    private BigDecimal partRes, PI_1_Div = BigDecimal.ONE;


    private BigDecimal PI_1 = new BigDecimal(0);
    private BigDecimal PI_2 = new BigDecimal(0);
    private BigDecimal PI_3 = new BigDecimal(0);
    private BigInteger denominator = new BigInteger("0");

    //Gauss
    private BigDecimal aN = BigDecimal.ONE;
    private BigDecimal bN = BigDecimal.valueOf(1 / Math.sqrt(2));
    private BigDecimal tN = BigDecimal.valueOf(1 / 4);
    private BigDecimal pN = BigDecimal.ONE;
    private BigDecimal accuracy = BigDecimal.ONE;
    private final BigDecimal TWO = BigDecimal.valueOf(2);
    private final BigDecimal FOUR = BigDecimal.valueOf(4);
    private BigDecimal auxA, auxB;
    private MathContext mc;

    // MonteCarlo
    private BigDecimal xPos, yPos, nrHits;
    private MathContext mth = new MathContext(10);


    //Limits
    private int limitInt;
    private BigInteger limit = new BigInteger("0");

    @Override
    public void initialize(Object... parameters) {
        // Limits init
        limit = new BigInteger(String.valueOf(parameters[0]));
        limitInt = (int) parameters[0];
        accuracy = BigDecimal.valueOf(Math.pow(10, -limitInt));
        mc = new MathContext(limitInt);
    }

    @Override
    public void run() {

    }

    @Override
    public void run(Object... parameters) {
        int option = (Integer) parameters[0];

        switch (option) {
            case 0:
                computePiLeibniz();
                break;
            case 1:
                computePiGaussLegendre();
                break;
            case 2:
                computePiMonteCarlo();
                break;
            default:
                throw new IllegalArgumentException("Option is invalid. It must be between 0-2");
        }
    }

    @Override
    public void warmUp() {
        System.out.println("Warming Up...");
//        for(int i = 1; i < 10; i++) {
//            run(0);
//            clean();
//        }
//        for(int i = 1; i < 10; i++) {
//            run(1);
//            clean();
//        }
        for(int i = 1; i < 10; i++) {
            run(2);
            clean();
        }
        System.out.println("Warmed Up!");
    }

    private void computePiLeibniz() {
        for (; denominator.compareTo(limit) <= 0; denominator = denominator.add(increment)) {
            partRes = new BigDecimal(denominator.multiply(even).subtract(increment));
            if (denominator.mod(even).equals(zero)) {
                PI_1 = PI_1.subtract(PI_1_Div.divide(partRes, limitInt, RoundingMode.CEILING));
            } else {
                PI_1 = PI_1.add(PI_1_Div.divide(partRes, limitInt, RoundingMode.CEILING));
            }
        }
    }


    private void computePiGaussLegendre() {
        while ((aN.subtract(bN)).compareTo(accuracy) > 0) {
            auxA = aN;
            auxB = bN;
            aN = (aN.add(bN)).divide(TWO, limitInt, RoundingMode.CEILING);
            bN = (auxA.multiply(auxB)).sqrt(mc);
            tN = tN.subtract(pN.multiply((auxA.subtract(aN)).multiply(auxA.subtract(aN))));
            pN = pN.multiply(TWO);
        }

        PI_2 = ((aN.add(bN)).multiply(aN.add(bN))).divide(tN.multiply(FOUR), limitInt, RoundingMode.CEILING);
    }

    private void computePiMonteCarlo() {
        Random randomNr = new Random(System.currentTimeMillis());
        nrHits = BigDecimal.valueOf(0);
        for(int i = 1; i < limitInt; i++) {
            xPos = BigDecimal.valueOf((randomNr.nextDouble() * 2) - 1.0);
            yPos = BigDecimal.valueOf((randomNr.nextDouble() * 2) - 1.0);


            if(distance(xPos.multiply(xPos), yPos.multiply(yPos))) {
                nrHits = nrHits.add(BigDecimal.valueOf(1));
            }
        }
        PI_3 = BigDecimal.valueOf(4).multiply(nrHits.divide(BigDecimal.valueOf(limitInt), limitInt, RoundingMode.CEILING ));
    }

    private boolean distance(BigDecimal xPos2, BigDecimal yPos2) {
        BigDecimal dist = xPos2.add(yPos2);
        return dist.sqrt(mth).compareTo(BigDecimal.ONE) <= 0;
    }


    @Override
    public void clean() {
        // Simple Pi init
        PI_1 = BigDecimal.ZERO;
        PI_2 = BigDecimal.ZERO;
        PI_3 = BigDecimal.ZERO;
        denominator = BigInteger.ONE;

        // Gauss init
        aN = BigDecimal.ONE;
        bN = BigDecimal.valueOf(1.0 / Math.sqrt(2));
        tN = BigDecimal.valueOf(1.0 / 4.0);
        pN = BigDecimal.ONE;
    }

    @Override
    public void cancel() {

    }

    @Override
    public String getResult(int pi_type) {
        switch (pi_type) {
            case 1:
                return String.valueOf(PI_1.multiply(BigDecimal.valueOf(4)));
            case 2:
                return String.valueOf(PI_2);
            case 3:
                return String.valueOf(PI_3);
            default:
                return "0";
        }
    }
}
