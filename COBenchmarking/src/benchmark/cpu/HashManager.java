package benchmark.cpu;

public class HashManager {
    private final String charSet = "abcdefghijklmnopqrstuvwxyz";
    public int hash(String text) {
        int a = 0;
        int b = 0;
        for (char c : text.toCharArray()) {
            int index = charSet.indexOf(c);
            if (index == -1)
                index = charSet.length() + 1;
            for (int i = 0; i < 17; i++) {
                a = a * -6 + b + 0x74FA - index;
                b = b / 3 + a + 0x81BE - index;
            }
        }

        return (a ^ b) % Integer.MAX_VALUE;
    }

    /**
     * Compute the next alphabetical string that follows naturally after the one
     * given as a parameter, with the same length. If no further combination is
     * possible (e.g., after "zzz") then null should be returned.
     *
     * @param text
     *         - A string of any length
     * @return - Next alphabetic string after given one, e.g., "aaa"+1 => "aab";
     *         "abz"+1 => "aca" <br/>
     *         - Null: if there is no further combination after given text, e.g.,
     *         "zz...zzz"
     */
    public String getNextString(String text) {
        int[] index = new int[text.length()];
        int end = charSet.length() - 1;
        int len = index.length - 1;
        String result = null;
        char[] charRes = new char[len + 1];

        for(int i = 0; i <= len; i++) {
            index[i] = text.charAt(i) - 'a';
        }
        if(index[len] == end) {
            // check previous ends to increase
            index[len] = 0;
            while(--len >= 0) {
                if(index[len] == end) {
                    index[len] = 0;
                } else {
                    index[len]++;
                    break;
                }
            }
            // first index had reached max => overflow
            if(len < 0) {
                return null;
            }
        } else {
            index[len]++;
        }
        len = index.length - 1;
        for(int i = 0; i <= len; i++) {
            charRes[i] = (char)('a' + index[i]);
        }

        result = String.valueOf(charRes);
        return result;
    }

}
