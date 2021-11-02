package com.shuangpeng.Problem.p0501_0600;

public class Problem0583DeleteOperationForTwoStrings {

    public int minDistance0(String word1, String word2) {
        int n1 = word1.length(), n2 = word2.length();
        int[] dp = new int[n2 + 1];
        for (int i = 0; i <= n2; i++) {
            dp[i] = i;
        }
        for (int i = 1; i <= n1; i++) {
            int[] temp = new int[n2 + 1];
            temp[0] = i;
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    temp[j] = dp[j - 1];
                } else {
                    temp[j] = Math.min(dp[j], temp[j - 1]) + 1;
                }
            }
            dp = temp;
        }
        return dp[n2];
    }

    public int minDistance1(String word1, String word2) {
        int n1 = word1.length(), n2 = word2.length();
        int[] dp = new int[n2 + 1];
        for (int i = 1; i <= n1; i++) {
            int[] temp = new int[n2 + 1];
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    temp[j] = dp[j - 1] + 1;
                } else {
                    temp[j] = Math.max(dp[j], temp[j - 1]);
                }
            }
            dp = temp;
        }
        return n1 + n2 - (dp[n2] << 1);
    }

    public int minDistance(String word1, String word2) {
        int n1 = word1.length(), n2 = word2.length();
        int[] dp = new int[n2 + 1];
        for (int i = 1; i <= n2; ++i) {
            dp[i] = i;
        }
        for (int i = 1; i <= n1; ++i) {
            int[] temp = new int[n2 + 1];
            temp[0] = i;
            for (int j = 1; j <= n2; ++j) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    temp[j] = dp[j - 1];
                } else {
                    temp[j] = Math.min(temp[j - 1], dp[j]) + 1;
                }
            }
            dp = temp;
        }
        return dp[n2];
    }

//    public static void main(String[] args) {
//        Problem0583DeleteOperationForTwoStrings a = new Problem0583DeleteOperationForTwoStrings();
//        a.minDistance("sea", "eat");
//    }
}
