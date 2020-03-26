package benchmark.cpu;

import benchmark.IBenchmark;

public class CPUFixedPoint implements IBenchmark {
    private int size, i, j, k, l, jump;
    private int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    private int[] arr1, arr2, arr3, result;

    public static final int OPERATOR_OP = 39, DYNAMIC_OP = 17, ACCESSIBILITY_OP = 35;

    @Override
    public void initialize(Object... parameters) {
        size = (Integer) parameters[0];
        arr1 = new int[size];
        arr2 = new int[size];
        arr3 = new int[size];
        result = new int[size];
    }

    @Deprecated
    @Override
    public void run() {

    }

    @Override
    public void run(Object... parameters) {
        i = 1;
        j = 1;
        k = 1;
        l = 1;
        jump = 1;

        switch ((CPUFixedPointMethod) parameters[0]) {
            case OPERATORS:
                for (int i = 0; i < size; i++) {
                    j = numbers[1] * (k - j) * (l - k);
                    k = numbers[7] * k - (l - j) * numbers[1];
                    l = (l - k) * (numbers[5] + j);
                    if (l - 2 < size && l - 2 > 0) {
                        result[l - 2] = j + k + l;
                    }
                    if (k - 2 < size && k - 2 > 0) {
                        result[k - 2] = j * k + 1;
                    }
                }
                break;
            case DYNAMIC:
                while (i++ < size) {
                    if (k == 1) {
                        k = numbers[2];
                    } else {
                        k = numbers[3];
                    }

                    if (k > 2) {
                        k = numbers[0];
                    } else {
                        k = numbers[5];
                    }
                    if (k < 6) {
                        k = numbers[4];
                    } else {
                        k = numbers[7];
                    }
                }
                break;
            case ACCESSIBILITY:
                for (i = 0; i < size; i++) {
                    arr1[i] = i;
                    arr2[i] = i + 1;
                    arr3[i] = i - 1;
                }
                for (i = 0; i < size; i++) {
                    jump = arr1[i];
                    if (jump < size) { // 17
                        swap(arr1, arr2, jump, jump); // 7
                        swap(arr3, arr1, jump, jump); // 7
                        arr2[jump] = arr2[arr2[jump]]; // 4
                    }
                }
                break;
            default:
                break;
        }
    }

    public void swap(int[] a, int[] b, int i, int j) {
        int aux = a[i];
        a[i] = b[j];
        b[j] = aux;
    }

    @Override
    public void warmUp() {
        System.out.println("Warming Up...");
        for (int i = 0; i < 100; i++) {
            run(CPUFixedPointMethod.OPERATORS);
            clean();
            run(CPUFixedPointMethod.DYNAMIC);
            clean();
            run(CPUFixedPointMethod.ACCESSIBILITY);
            clean();
        }
        System.out.println("Warmed Up!");
    }

    @Override
    public void clean() {
        for (int i = 0; i < size; i++) {
            result[i] = 0;
            arr1[i] = 0;
            arr2[i] = 0;
            arr3[i] = 0;
        }
    }

    @Override
    public void cancel() {

    }

    @Override
    public String getResult(int type) {
        return null;
    }
}
