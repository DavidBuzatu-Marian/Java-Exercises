public class LCS_Simple_Recursive implements ILCS_Simple {

    private String X;
    private String Y;
    private int[][] DP;
    private int[][] DP2;

    @Override
    public int LCS_length(String x, String y) {
        X = x;
        Y = y;
        DP = new int[x.length()][y.length()];
        DP2 = new int[2][y.length()];
        return LCS_Simple_DynProgr(X.length(), Y.length());
    }

    private int lcs_length(int i, int j) {
        if (i == -1 || j == -1) {
            return 0;
        } else if (X.charAt(i) == Y.charAt(j)) {
            return 1 + lcs_length(i - 1, j - 1);
        } else {
            return Math.max(lcs_length(i - 1, j), lcs_length(i, j - 1));
        }
    }

    public int LCS_Simple_DynProgr(int len1, int len2) {
        if (len1 == -1 || len2 == -1) {
            return 0;
        }
        for (int i = 0; i < len1; ++i) {
            if (Y.charAt(0) == X.charAt(i)) {
                DP[i][0]++;
            }
        }
        for (int i = 1; i < len2; ++i) {
            if (X.charAt(0) == Y.charAt(i)) {
                DP[0][i]++;
            }
        }
        for (int i = 1; i < len1; ++i) {
            // characters of x are represented by i
            for (int j = 1; j < len2; ++j) {
                // characters of y are represented by j
                if (X.charAt(i) == Y.charAt(j)) {
                    DP[i][j] = 1 + DP[i - 1][j - 1];
                } else {
                    DP[i][j] = Math.max(DP[i - 1][j], DP[i][j - 1]);
                }
            }
        }
        return DP[len1 - 1][len2 - 1];
    }

    public int  LCS_Simple_DynProgMemEff(int len1, int len2) {
        if (len1 == -1 || len2 == -1) {
            return 0;
        }
        for (int i = 0; i < len2; ++i) {
            if (X.charAt(0) == Y.charAt(i)) {
                DP2[0][i]++;
            }
        }
        if (X.charAt(1) == Y.charAt(0)) {
            DP2[1][0]++;
        }
        for(int i = 1; i < len1; ++i) {
            for(int j = 1; j < len2; ++j) {
                if(i % 2 == 1) {
                    // complete line 1
                    if (X.charAt(i) == Y.charAt(j)) {
                        DP2[1][j] = 1 + DP2[0][j - 1];
                    } else {
                        DP2[1][j] = Math.max(DP2[0][j], DP2[1][j - 1]);
                    }
                } else {
                    // complete line 2
                    if (X.charAt(i) == Y.charAt(j)) {
                        DP2[0][j] = 1 + DP2[1][j - 1];
                    } else {
                        DP2[0][j] = Math.max(DP2[1][j], DP2[0][j - 1]);
                    }
                }
            }
        }
        if(len1 % 2 == 0) {
            return DP2[1][len2 - 1];
        } else {
            return DP2[0][len2 - 1];
        }

    }
}
