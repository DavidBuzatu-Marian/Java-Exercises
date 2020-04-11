import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Celebrity {


    public static void main(String[] args) {
        int[][] knows = null;
        int n = 0, m = 0, lCounter = 0;
        try {
            Scanner scanner = new Scanner(new File("input2.txt"));
            if (scanner.hasNextLine()) {
                String[] split = scanner.nextLine().split(" ");
                n = Integer.parseInt(split[0]);
                m = Integer.parseInt(split[1]);
                knows = new int[n][m];
            }
            while (scanner.hasNextLine()) {
                String[] split = scanner.nextLine().split(" ");
                for (int j = 0; j < m; j++) {
                    knows[lCounter][j] = Integer.parseInt(split[j]);
                }
                lCounter++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int res = CelebritySolve(knows, n, m);
        if (res != 0) {
            System.out.println("Celebrity is: " + CelebritySolve(knows, n, m));
        }
        else {
            System.out.println("No Celebrity found!");
        }
    }

    private static int CelebritySolve(int[][] knows, int n, int m) {
        Stack<Integer> persons = new Stack<>();
        for (int i = 0; i < n; i++) {
            persons.push(i);
        }

        while (persons.size() > 1) {
            int stays;
            int p1 = persons.pop();
            int p2 = persons.pop();
            if (knows[p1][p2] == 1) {
                stays = p2;
            } else {
                stays = p1;
            }
            persons.push(stays);
        }
        int p = persons.pop();
        // check if it is okay for n
        for (int i = 0; i < n; i++) {
            if (i != p && (knows[p][i] == 1 || knows[i][p] == 0)) {
                return 0;
            }
        }
        return p;
    }
}
