import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Exercise3 {

    public static void main(String[] args) {

        int[] A, B, C;
        File input = new File("inputA.txt");
        File input2 = new File("inputB.txt");
        File input3 = new File("inputC.txt");
        A = readArray(input);
        B = readArray(input2);
        C = readArray(input3);

        long startTime = System.nanoTime();
        System.out.println(ExistTriplet(A, B, C, 10));
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; // into millis
        System.out.println(duration);
    }

    private static int[] readArray(File input) {
        Scanner reader;
        int[] A = null;
        int counter = 0, N = 0;
        try {
            reader = new Scanner(input);
            if (reader.hasNext()) {
                N = reader.nextInt();
            }
            A = new int[N];
            while (reader.hasNext()) {
                A[counter++] = reader.nextInt();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return A;
    }

    public static int ExistTriplet(int[] A, int[] B, int[] C, Integer V) {
        int result = 0;
        Set<Integer> elemsC = new HashSet<>();
        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(C);

        for (int value : C) {
            elemsC.add(value);
        }

        for (int valueA : A) {
            for (int valueB : B) {
                int current = valueA + valueB;
                if (elemsC.contains(V - current)) {
//                    System.out.println(valueA + " + " + valueB + " + " + (V - current));
                    result++;
                }
            }
        }
        return result;
    }
}
