package com.shuangpeng.Problem.p1101_1200;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 叶值的最小代价生成树
 * @date 2023/5/31 2:53 PM
 **/
public class Problem1130MinimumCostTreeFromLeafValues {

    public int mctFromLeafValues0(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        int[][] maxes = new int[n][n];
        for (int i = 0; i < n; ++i) {
            maxes[i][i] = arr[i];
            for (int j = i + 1; j < n; ++j) {
                maxes[i][j] = Math.max(maxes[i][j - 1], arr[j]);
            }
        }
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                long cost = dp[i + 1][j] + arr[i] * maxes[i + 1][j];
                for (int k = i + 1; k < j; ++k) {
                    cost = Math.min(cost, dp[i][k] + dp[k + 1][j] + maxes[i][k] * maxes[k + 1][j]);
                }
                dp[i][j] = (int) cost;
            }
        }
        return dp[0][n - 1];
    }

    public int mctFromLeafValues1(int[] arr) {
        int n = arr.length;
        int[][][] dp = new int[n][n][2];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i][0] = arr[i];
            for (int j = i + 1; j < n; j++) {
                dp[i][j][0] = Math.max(dp[i][j - 1][0], arr[j]);
                dp[i][j][1] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    dp[i][j][1] = Math.min(dp[i][j][1], dp[i][k][1] + dp[k + 1][j][1] + dp[i][k][0] * dp[k + 1][j][0]);
                }
            }
        }
        return dp[0][n - 1][1];
    }

    public int mctFromLeafValues(int[] arr) {
        List<Integer> stack = new ArrayList<>();
        int ans = 0;
        for (int num : arr) {
            while (!stack.isEmpty() && stack.get(stack.size() - 1) <= num) {
                int x = stack.remove(stack.size() - 1);
                int y = stack.isEmpty() || stack.get(stack.size() - 1) > num ? num : stack.get(stack.size() - 1);
                ans += x * y;
            }
            stack.add(num);
        }
        for (int i = stack.size() - 1; i > 0; i--) {
            ans += stack.get(i) * stack.get(i - 1);
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem1130MinimumCostTreeFromLeafValues a = new Problem1130MinimumCostTreeFromLeafValues();
//        a.mctFromLeafValues(new int[]{14, 11, 3, 9, 3, 2});
//    }
}
