package benchmark.cpu;

import benchmark.IBenchmark;

public class CPUFixedVsFloatingPoint implements IBenchmark {
    private int result, size;
    private double fResult;

    @Override
    public void initialize(Object... parameters) {
        size = (Integer) parameters[0];
    }

    @Override
    @Deprecated
    public void run() {
    }

    @Override
    public void run(Object... parameters) {
        result = 0;
        fResult = 0;

        switch ((NumberRepresentation) parameters[0]) {
            case FLOATING:
                for (int i = 0; i < size; i++) {
                    fResult += (i / 256.0);
                }
                break;
            case FIXED:
                for (int i = 0; i < size; i++) {
                    result += (i >> 8);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void warmUp() {
        for (int i = 0; i < size; i++) {
            result = i / 256;
            fResult = i / 256.0;
        }
    }

    @Override
    public void clean() {

    }

    @Override
    public void cancel() {

    }

    @Override
    public String getResult() {
        if(result != 0) {
            return String.valueOf(result);
        }
        return String.valueOf(fResult);
    }
}
