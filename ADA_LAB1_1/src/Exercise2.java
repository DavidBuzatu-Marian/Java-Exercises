import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {

        int[] A;
        File input = new File("input2.txt");
        A = readArray(input);

        long startTime = System.nanoTime();
        System.out.println(CountSmallerTriplets(A,10));
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


    public static int CountSmallerTriplets(int[] elems, Integer V) {
        int  result = 0;
        Arrays.sort(elems);
        for(int i = 0; i < elems.length - 2; i++) {
            int left = i + 1;
            int right = elems.length - 1;
            while(left < right) {
                int curResult = elems[left] + elems[right] + elems[i];
                if(curResult < V) {
                    result+= right - left;
                    left++;
                    right--;
                } else if(curResult > V) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }
}
