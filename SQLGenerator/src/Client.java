import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class Client {
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();
    public static void main(String[] args) {

        byte[] array = new byte[7];
        new Random().nextBytes(array);
        int prev_percentage = 0;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("plantInsert.txt"));
            writer.write("INSERT INTO orgmenu (id,name,domain_id) VALUES ");

            int[] plant_ids = new int[20000];
            ArrayList<String> name = new ArrayList<>();
            int i;
            for(i = 0; i < 20000; i++) {
                plant_ids[i] = i;
                name.add(i, generateRandomString(12));
                writer.write("(" + i + ", '" + name.get(i) + "'," + random.nextInt(19448) + "), ");
                if((i * 100 / 20000 ) % 20 > prev_percentage + 1) {
                    System.out.println("Completed......................." + i * 100 / 20000  + " %");
                    prev_percentage = i * 100 / 20000 % 20;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            sb.append(rndChar);

        }

        return sb.toString();

    }
}
