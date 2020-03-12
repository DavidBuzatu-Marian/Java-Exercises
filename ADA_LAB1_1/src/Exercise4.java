import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Exercise4 {


    public static void main(String[] args) {
        int[] A;
        File input = new File("input4.txt");
        A = readArray(input);

        long startTime = System.nanoTime();
        System.out.println(CountPossibleArrays(A));
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


    public static int CountPossibleArrays(int[] A) {
        int result = 0;
        Arrays.sort(A);
        for (int i = 0; i < A.length - 2; i++) {
            int left = i + 1;
            int right = A.length - 1;
            int val = A[i];

            int side1 = A[left] + A[right];
            int side2 = A[left] + val;
            int side3 = A[right] + val;
            while (left < right) {
                if (val >= side1) {
                    left++;
                } else if (A[right] >= side2) {
                    left++;
                } else if (A[left] >= side3) {
                    break;
                } else {
//                    System.out.println("( " + val + ", " + A[left] + ", " + A[right] + ")");
                    right--;
                    result++;

                }
                side1 = A[left] + A[right];
                side2 = A[left] + val;
                side3 = A[right] + val;
            }
        }

        return result;
    }

    public static int CountPossibleArrays2(int[] A) {
        int result = 0;
        Arrays.sort(A);
        for(int i = 0; i < A.length - 2; i++) {
            int b = i + 1;
            int c = i + 2;

            int side = A[i] + b;
            while(c < A.length) {
                if(c > side) {
                    b++;
                } else if(b == c) {
                    c++;
                }
                    else if( c < side) {
                    result++;
                    b++;
                }
            }
        }

        return result;
    }
}
