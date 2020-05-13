
public class LCS_Complete_Recursive implements ILCS_Complete {
    private String X;
    private String Y;
    private String[][] DP;
    private String[][] DP2;

    @Override
    public String LCS_string(String x, String y) {
        if (x.length() < y.length()) {
            X = x;
            Y = y;
        } else {
            X = y;
            Y = x;
        }
        DP = new String[X.length()][Y.length()];
        DP2 = new String[2][Y.length()];
        for (int i = 0; i < X.length(); ++i) {
            for (int j = 0; j < Y.length(); ++j) {
                DP[i][j] = "";
                if (i < 2) {
                    DP2[i][j] = "";
                }
            }
        }
        return LCS_Complete_NoTable(X.length(), Y.length());
    }

    private String lcs_string(int i, int j) {

        if (i == -1 || j == -1) {
            return "";
        } else if (X.charAt(i) == Y.charAt(j)) {
            return lcs_string(i - 1, j - 1) + X.charAt(i);
        } else {
            String s1 = lcs_string(i - 1, j);
            String s2 = lcs_string(i, j - 1);
            if (s1.length() > s2.length())
                return s1;
            else return s2;
        }
    }

    private String LCS_Complete_DynProg(int len1, int len2) {
        if (len1 == -1 || len2 == -1) {
            return "";
        }
        for (int i = 0; i < len1; ++i) {
            if (X.charAt(i) == Y.charAt(0)) {
                DP[i][0] += X.charAt(i);
            }
        }
        for (int i = 1; i < len2; ++i) {
            if (X.charAt(0) == Y.charAt(i)) {
                DP[0][i] += Y.charAt(i);
            }
        }
        for (int i = 1; i < len1; ++i) {
            for (int j = 1; j < len2; ++j) {
                if (X.charAt(i) == Y.charAt(j)) {
                    DP[i][j] += (DP[i - 1][j - 1] + Y.charAt(j));
                } else {
                    if (DP[i - 1][j].length() > DP[i][j - 1].length()) {
                        DP[i][j] = DP[i - 1][j];
                    } else {
                        DP[i][j] = DP[i][j - 1];
                    }
                }
            }
        }
        return DP[len1 - 1][len2 - 1];
    }

    private String LCS_Complete_NoTable(int len1, int len2) {
        if (len1 == -1 || len2 == -1) {
            return "";
        }
        for (int i = 0; i < len2; ++i) {
            if (X.charAt(0) == Y.charAt(i)) {
                DP2[0][i] += Y.charAt(i);
            }
        }
        if (X.charAt(1) == Y.charAt(0)) {
            DP2[1][0] += X.charAt(1);
        }
        for (int i = 1; i < len1; ++i) {
            int idx1, idx2;
            idx1 = i % 2;
            idx2 = idx1 == 1 ? 0 : 1;
            for (int j = 1; j < len2; ++j) {
                if (X.charAt(i) == Y.charAt(j)) {
                    DP2[idx1][j] += (DP2[idx2][j - 1] + Y.charAt(j));
                } else {
                    if (DP2[idx2][j].length() > DP2[idx1][j - 1].length()) {
                        DP2[idx1][j] = DP2[idx2][j];
                    } else {
                        DP2[idx1][j] = DP2[idx1][j - 1];
                    }
                }
                DP2[idx2][j - 1] = "";
            }
            DP2[idx2][len2 - 1] = "";
        }
        if (len1 % 2 == 0) {
            return DP2[1][len2 - 1];
        } else {
            return DP2[0][len2 - 1];
        }
    }

}
