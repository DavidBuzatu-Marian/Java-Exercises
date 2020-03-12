import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Exercise1 {
    public static void main(String[] args) {
        Integer nrEl = 0;
        int[] elems;
        int counter = 0;
        File input = new File("input1.txt");
        try {
            Scanner reader = new Scanner(input);
            if(reader.hasNext()) {
                nrEl = reader.nextInt();
            }
            elems = new int[nrEl];
            while(reader.hasNext()) {
                Integer number = reader.nextInt();
                elems[counter++] = number;
            }
            long startTime = System.nanoTime();
            System.out.println(CountTripletsSum(elems, 0));
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000;
            System.out.println(duration);

         } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int CountTripletsSumSet(int[] elems, Integer V) {
        int res = 0;
        for(int i = 0; i < elems.length - 1;  i++) {
            Set<Integer> sums = new HashSet<>();
            for(int j = i + 1; j < elems.length; j++) {
                int sum = -(elems[i] + elems[j]);
                if(sums.contains(sum)) {
                    res++;
                } else {
                    sums.add(elems[j]);
                }
            }
        }

        return res;
    }

    public static int CountTripletsSum(int[] elems, Integer V) {
        int  result = 0;
        Arrays.sort(elems);
        for(int i = 0; i < elems.length - 2; i++) {
            int left = i + 1;
            int right = elems.length - 1;
            int curOpposite = elems[i] * - 1;
            while(left < right) {
                int curResult = elems[left] + elems[right];
                if(curResult == curOpposite) {
                    result++;
                    left++;
                    right--;
                } else if(curResult > curOpposite) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }



}
