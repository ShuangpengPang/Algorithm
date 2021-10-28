package com.shuangpeng.Problem.p0001_0100;

public class Problem0097InterleavingString {

    public boolean isInterleave0(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        int[][][] memo = new int[s1.length() + 1][s2.length() + 1][s3.length() + 1];
        return checkInterleave(s1, s2, s3, 0, 0, 0, true, memo)
                || checkInterleave(s2, s1, s3, 0, 0, 0, false, memo);
    }

    private boolean checkInterleave(String s1, String s2, String s3, int i1, int i2, int i3, boolean flag, int[][][] memo) {
        int i11 = i1;
        int i22 = i2;
        if (!flag) {
            i11 = i2;
            i22 = i1;
        }
        if ((i11 != 0 || i22 != 0) && memo[i11][i22][i3] != 0) {
            return memo[i11][i22][i3] == 1;
        }
        if (isEqual(s1, s3, i1, i3)) {
            memo[i11][i22][i3] = 1;
            return true;
        }
        if (i1 >= s1.length() || s1.charAt(i1) != s3.charAt(i3)) {
            memo[i11][i22][i3] = -1;
            return false;
        }
        int i = 0;
        while (i1 + i < s1.length() && s1.charAt(i1 + i) == s3.charAt(i3 + i)) {
            if (checkInterleave(s2, s1, s3, i2, i1 + i + 1, i3 + i + 1, !flag, memo)) {
                memo[i11][i22][i3] = 1;
                return true;
            }
            i++;
        }
        memo[i11][i22][i3] = -1;
        return false;
    }

    private boolean isEqual(String s1, String s2, int i1, int i2) {
        int n1 = s1.length();
        int n2 = s2.length();
        if (n1 - i1 != n2 - i2) {
            return false;
        }
        for (int i = 0; i1 + i < n1; i++) {
            if (s1.charAt(i1 + i) != s2.charAt(i2 + i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isInterleave1(String s1, String s2, String s3) {
        int n1 = s1.length();
        int n2 = s2.length();
        if (n1 + n2 != s3.length()) {
            return false;
        }
        boolean[][] dp = new boolean[n1 + 1][n2 + 1];
        for (int i = 0; i <= n1; i++) {
            for (int j = 0; j <= n2; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                    continue;
                }
                if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                    continue;
                }
                dp[i][j] = (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1))
                        || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[n1][n2];
    }

    public boolean isInterleave2(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();

        if (n + m != t) {
            return false;
        }

        boolean[][] f = new boolean[n + 1][m + 1];

        f[0][0] = true;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                int p = i + j - 1;
                if (i > 0) {
                    f[i][j] = f[i][j] || (f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                if (j > 0) {
                    f[i][j] = f[i][j] || (f[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }

        return f[n][m];
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        int n1 = s1.length();
        int n2 = s2.length();
        if (n1 + n2 != s3.length()) {
            return false;
        }
        boolean[] dp = new boolean[n2 + 1];
        dp[0] = true;
        for (int i = 0; i <= n1; i++) {
            for (int j = 0; j <= n2; j++) {
                int p = i + j - 1;
                if (i > 0) {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(p);
                }
                if (j > 0) {
                    dp[j] = dp[j] || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }
        return dp[n2];
    }

//    public static void main(String[] args) {
//        Problem0097InterleavingString a = new Problem0097InterleavingString();
//        a.isInterleave("aabcc", "dbbca", "aadbbcbcac");
//    }
}
