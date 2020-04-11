import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SuccessfulParty {
    private static ArrayList<Integer> answer = new ArrayList<>();
    public static void main(String[] args) {
        int nrNodes = 0;
        int k = 0;
        List<List<Integer>> nodesNeigh = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            if(scanner.hasNextLine()) {
                k = Integer.parseInt(scanner.nextLine());
            }
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                nodesNeigh.add(new ArrayList<>());
                String[] neighs = line.split(" ");
                for(String nei: neighs) {
                    nodesNeigh.get(nrNodes).add(Integer.parseInt(nei));
                }
                nrNodes++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < nrNodes; i++) {
            answer.add(i);
        }
        List<Integer> result = party(nodesNeigh, k, nrNodes);
        System.out.print("Party is: [ ");
        for(int node: result) {
            System.out.print(node + ", ");
        }
        System.out.println("]");
//        printNeighs(nodesNeigh);

    }

    private static ArrayList<Integer> party(List<List<Integer>> nodesNeigh, int k, int n) {
        int removeP;
        // less people or equal to k
        if(n <= k) {
            return new ArrayList<>();
        }
        // everybody has at least k friends in nodesNeigh
        removeP = findFriendWithLessK(nodesNeigh, k);
        if(removeP == 0) {
            return answer;
        }
        answer.remove(removeP);
        nodesNeigh.remove(removeP);
        removeFromNeighs(nodesNeigh, removeP);
        --n;
        return party(nodesNeigh, k, n);
    }

    private static void removeFromNeighs(List<List<Integer>> nodesNeigh, Integer removeP) {
        for(List<Integer> list: nodesNeigh) {
            list.remove(removeP);
        }
    }

    private static int findFriendWithLessK(List<List<Integer>> nodesNeigh, int k) {
        int counter = 0;
        for(List<Integer> list: nodesNeigh) {
            if(list.size() < k) {
                return counter;
            }
            counter++;
        }
        return 0;
    }


    private static void printNeighs(List<List<Integer>> nodesNeigh) {
        int counter = 0;
        for(List<Integer> list: nodesNeigh) {
            System.out.print("Node: " + counter + "; Neighs: ");
            for(int el: list) {
                System.out.print(el + ",");
            }
            System.out.println();
            counter++;
        }
    }
}
