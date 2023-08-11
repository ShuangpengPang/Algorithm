package com.shuangpeng.Problem.p2801_2900;

import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2811CheckIfItIsPossibleToSplitArray（判断是否能拆分数组）
 * @date 2023/8/11 10:40 AM
 */
public class Problem2811CheckIfItIsPossibleToSplitArray {

    public boolean canSplitArray(List<Integer> nums, int m) {
        int n = nums.size();
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = true;
            int sum = nums.get(i);
            for (int j = i + 1; j < n; j++) {
                sum += nums.get(j);
                for (int k = i, s = 0; k < j && !dp[i][j]; k++) {
                    s += nums.get(k);
//                    dp[i][j] = i + 1 == j || dp[i][k] && dp[k + 1][j] && (s >= m && sum - s >= m || i == k && sum - s >= m || s >= m && k + 1 == j);
                    dp[i][j] = dp[i][k] && dp[k + 1][j] && (i == k || s >= m) && (k + 1 == j || sum - s >= m);
                }
            }
        }
        return dp[0][n - 1];
    }
}
