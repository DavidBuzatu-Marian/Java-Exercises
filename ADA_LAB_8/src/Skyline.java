import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Skyline {
    private static List<Coords> buildings = new ArrayList<>();
    private static List<SkylineCoords> skylines = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("input3.txt"));
            while(scanner.hasNextLine()) {
                String[] split = scanner.nextLine().split(" ");
                Coords buildingStart = new Coords(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                Coords buildingEnd = new Coords(Integer.parseInt(split[2]), 0);
                buildings.add(buildingStart);
                buildings.add(buildingEnd);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        getSkylines(buildings);
//        System.out.println(skylines.toString());

        SkylineCoords res = SkylineSolve(0, skylines.size() - 1);
        System.out.println(res.toString());
    }

    private static void getSkylines(List<Coords> buildings) {
        for(int i = 0; i < buildings.size(); i += 2) {
            SkylineCoords sk = new SkylineCoords();
            sk.add(buildings.get(i));
            sk.add(buildings.get(i + 1));
            skylines.add(sk);
        }
    }

    private static SkylineCoords SkylineSolve(int left, int right) {
        if(left == right) {
            return skylines.get(left);
        } else {
            int middle = (left + right) / 2;
            SkylineCoords sk1 = SkylineSolve(left, middle);
            SkylineCoords sk2 = SkylineSolve(middle + 1, right);
            return MergeSkylines(sk1.get(), sk2.get());
        }
    }

    private static SkylineCoords MergeSkylines(List<Coords> sk1, List<Coords> sk2) {
        SkylineCoords res = new SkylineCoords();
        int i = 0, j = 0, counter = 0;
        int h1 = 0, h2 = 0;
        while(i < sk1.size() && j < sk2.size()) {
            if(sk1.get(i).getBegin() < sk2.get(j).getBegin()) {
                h1 = sk1.get(i).getHeight();
                Coords newCoord = new Coords(sk1.get(i).getBegin(), Math.max(h1, h2));
                // REMOVE UNNECESSARY COPIES (LIKE (2, 11) AFTER (1, 11)
                if(counter > 0) {
                    if(newCoord.getHeight() != res.get().get(counter - 1).getHeight()) {
                        if(newCoord.getBegin() == res.get().get(counter - 1).getBegin()) {
                            res.get().get(counter - 1).setHeight(Math.max(res.get().get(counter - 1).getHeight(), newCoord.getHeight()));
                        } else {
                            res.add(newCoord);
                            counter++;
                        }
                    }
                } else {
                    res.add(newCoord);
                    counter++;
                }

                i++;
            } else {
                h2 = sk2.get(j).getHeight();
                Coords newCoord = new Coords(sk2.get(j).getBegin(), Math.max(h1, h2));
                // REMOVE UNNECESSARY COPIES (LIKE (2, 11) AFTER (1, 11)
                if(counter > 0) {
                    if(newCoord.getHeight() != res.get().get(counter - 1).getHeight()) {
                        if(newCoord.getBegin() == res.get().get(counter - 1).getBegin()) {
                            res.get().get(counter - 1).setHeight(Math.max(res.get().get(counter - 1).getHeight(), newCoord.getHeight()));
                        } else {
                            res.add(newCoord);
                            counter++;
                        }
                    }
                } else {
                    res.add(newCoord);
                    counter++;
                }
                j++;
            }
        }

        while(i < sk1.size()) {
            res.add(new Coords(sk1.get(i).getBegin(), sk1.get(i).getHeight()));
            i++;
        }
        while(j < sk2.size()) {
            res.add(new Coords(sk2.get(j).getBegin(), sk2.get(j).getHeight()));
            j++;
        }

        return res;
    }
}
