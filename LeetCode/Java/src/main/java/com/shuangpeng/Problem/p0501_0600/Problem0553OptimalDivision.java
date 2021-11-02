package com.shuangpeng.Problem.p0501_0600;

import javafx.util.Pair;

public class Problem0553OptimalDivision {

    public String optimalDivision0(int[] nums) {
        int n = nums.length;
        Pair<Double, String>[][] dp = new Pair[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = new Pair((double) nums[i], Integer.toString(nums[i]));
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                int maxK = -1;
                double maxValue = 0;
                for (int k = j; k > i; k--) {
                    double left = dp[i][k - 1].getKey();
                    double right = nums[k];
                    for (int c = k + 1; c <= j; c++) {
                        right /= nums[c];
                    }
                    double value = left / right;
                    if (value >= maxValue) {
                        maxValue = value;
                        maxK = k;
                    }
                }
                String expression = "";
                if (maxK == j) {
                    expression = Integer.toString(nums[maxK]);
                    dp[i][j] = new Pair<>(maxValue, dp[i][j - 1].getValue() + '/' + nums[j]);
                } else {
                    StringBuilder builder = new StringBuilder();
                    builder.append('(');
                    builder.append(Integer.toString(nums[maxK]));
                    for (int k = maxK + 1; k <= j; k++) {
                        builder.append('/').append(Integer.toString(nums[k]));
                    }
                    builder.append(')');
                    expression = builder.toString();
                }
                dp[i][j] = new Pair<>(maxValue, dp[i][maxK - 1].getValue() + '/' + expression);
            }
        }
        return dp[0][n - 1].getValue();
    }

    public String optimalDivision(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0] + "";
        }
        if (n == 2) {
            return nums[0] + "/" + nums[1];
        }
        StringBuilder builder = new StringBuilder(nums[0] + "/(" + nums[1]);
        for (int i = 2; i < n; i++) {
            builder.append("/" + nums[i]);
        }
        builder.append(')');
        return builder.toString();
    }
}
