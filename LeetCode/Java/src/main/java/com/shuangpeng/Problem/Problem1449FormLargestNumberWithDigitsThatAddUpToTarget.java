package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem1449FormLargestNumberWithDigitsThatAddUpToTarget {

    public String largestNumber0(int[] cost, int target) {
        int n = cost.length;
        String[][] dp = new String[target + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[0][i] = "";
        }
        for (int i = 0; i <= target; i++) {
            dp[i][0] = "";
        }
        for (int i = 1; i <= target; i++) {
            for (int j = 1; j <= n; j++) {
                int value = cost[j - 1];
                if (i < value || ((i > value) && dp[i - value][j].isEmpty())) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    dp[i][j] = maxString(dp[i][j - 1], j + dp[i - value][j]);
                }
            }
        }
        return dp[target][n].isEmpty() ? "0" : dp[target][n];
    }

    private String maxString(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return str1.length() > str2.length() ? str1 : str2;
        }
        int n = str1.length();
        for (int i = 0; i < n; i++) {
            if (str2.charAt(i) > str1.charAt(i)) {
                return str2;
            }
        }
        return str1;
    }

    public String largestNumber1(int[] cost, int target) {
        int[][] dp = new int[10][target + 1];
        for (int i = 0; i < 10; ++i) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        int[][] from = new int[10][target + 1];
        dp[0][0] = 0;
        for (int i = 0; i < 9; ++i) {
            int c = cost[i];
            for (int j = 0; j <= target; ++j) {
                if (j < c) {
                    dp[i + 1][j] = dp[i][j];
                    from[i + 1][j] = j;
                } else {
                    if (dp[i][j] > dp[i + 1][j - c] + 1) {
                        dp[i + 1][j] = dp[i][j];
                        from[i + 1][j] = j;
                    } else {
                        dp[i + 1][j] = dp[i + 1][j - c] + 1;
                        from[i + 1][j] = j - c;
                    }
                }
            }
        }
        if (dp[9][target] < 0) {
            return "0";
        }
        StringBuffer sb = new StringBuffer();
        int i = 9, j = target;
        while (i > 0) {
            if (j == from[i][j]) {
                --i;
            } else {
                sb.append(i);
                j = from[i][j];
            }
        }
        return sb.toString();
    }

    public String largestNumber2(int[] cost, int target) {
        int n = cost.length;
        int[][] dp = new int[n + 1][target + 1];
        int[][] from = new int[n + 1][target + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                int c = cost[i];
                if (j >= c) {
                    if (dp[i][j] > dp[i + 1][j - c] + 1) {
                        dp[i + 1][j] = dp[i][j];
                        from[i + 1][j] = j;
                    } else {
                        dp[i + 1][j] = dp[i + 1][j - c] + 1;
                        from[i + 1][j] = j - c;
                    }
                } else {
                    dp[i + 1][j] = dp[i][j];
                    from[i + 1][j] = j;
                }
            }
        }
        if (dp[n][target] < 0) {
            return "0";
        }
        StringBuilder builder = new StringBuilder();
        int i = n, j = target;
        while (i > 0) {
            if (from[i][j] == j) {
                i--;
            } else {
                builder.append(i);
                j = from[i][j];
            }
        }
        return builder.toString();
    }

    public String largestNumber3(int[] cost, int target) {
        int n = cost.length;
        int[] dp = new int[target + 1];
        for (int i = 1; i <= target; i++) {
            dp[i] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < n; i++) {
            for (int j = cost[i]; j <= target; j++) {
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + 1);
            }
        }
        if (dp[target] <= 0) {
            return "0";
        }
        StringBuilder builder = new StringBuilder();
        int i = target, j = n;
        while (j > 0) {
            if ((i - cost[j - 1] >= 0) && (dp[i] == dp[i - cost[j - 1]] + 1)) {
                builder.append(j);
                i -= cost[j - 1];
            } else {
                j--;
            }
        }
        return builder.toString();
    }

    public String largestNumber(int[] cost, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for (int c : cost) {
            for (int j = c; j <= target; ++j) {
                dp[j] = Math.max(dp[j], dp[j - c] + 1);
            }
        }
        if (dp[target] < 0) {
            return "0";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 8, j = target; i >= 0; i--) {
            for (int c = cost[i]; j >= c && dp[j] == dp[j - c] + 1; j -= c) {
                sb.append(i + 1);
            }
        }
        return sb.toString();
    }
}
