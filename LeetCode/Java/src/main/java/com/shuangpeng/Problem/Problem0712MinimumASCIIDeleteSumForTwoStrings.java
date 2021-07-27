package com.shuangpeng.Problem;

public class Problem0712MinimumASCIIDeleteSumForTwoStrings {

    public int minimumDeleteSum(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        int[] dp = new int[n2 + 1];
        for (int i = 1; i <= n2; i++) {
            dp[i] = dp[i - 1] + s2.charAt(i - 1);
        }
        int sum = 0;
        for (int i = 1; i <= n1; i++) {
            char c1 = s1.charAt(i - 1);
            sum += c1;
            int[] temp = new int[n2 + 1];
            temp[0] = sum;
            for (int j = 1; j <= n2; j++) {
                char c2 = s2.charAt(j - 1);
                if (c1 == c2) {
                    temp[j] = dp[j - 1];
                } else {
                    temp[j] = Math.min(temp[j - 1] + c2, dp[j] + c1);
                }
            }
            dp = temp;
        }
        return dp[n2];
    }
}
