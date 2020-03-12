import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Exercise5 {
    public static void main(String[] args) {
        int[] A;
        File input = new File("input5.txt");
        A = readArray(input);

        long startTime = System.nanoTime();
        System.out.println(CountPythagoreanTriplets(A));
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


    public static int CountPythagoreanTriplets(int[] A) {
        int result = 0;
        Arrays.sort(A);
        for (int i = 0; i < A.length; i++) {
            int left = i != 0 ? 0 : 1;
            int right = i != A.length - 1 ? A.length - 1 : i - 1;
            double pitVal = Math.pow(A[i], 2);
            while (left < right) {
                double pitSearched = Math.pow(A[left], 2) + Math.pow(A[right], 2);
                if (left == i) {
                    left++;
                    continue;
                }
                if (right == i) {
                    right--;
                    continue;
                }
                if (pitVal < pitSearched) {
                    right--;
                } else if (pitVal > pitSearched) {
                    left++;
                } else {
                    result++;
                    left++;
                    right--;
                }
            }
        }

        return result;
    }
}
