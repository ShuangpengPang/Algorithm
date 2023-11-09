package com.shuangpeng.Problem.p1901_2000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1906MinimumAbsoluteDifferenceQueries（查询差绝对值的最小值）
 * @date 2023/11/9 9:56 AM
 */
public class Problem1906MinimumAbsoluteDifferenceQueries {

    public int[] minDifference(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length, N = Integer.MAX_VALUE >> 1;
        int[][] preSum = new int[n + 1][101];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= 100; j++) {
                preSum[i][j] = preSum[i - 1][j];
            }
            preSum[i][nums[i - 1]]++;
        }
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int start = queries[i][0], end = queries[i][1] + 1;
            int[] cnt = new int[101];
            int d = N, last = -N;
            for (int j = 0; j <= 100; j++) {
                cnt[j] = preSum[end][j] - preSum[start][j];
                if (cnt[j] > 0) {
                    d = Math.min(d, j - last);
                    last = j;
                }
            }
            ans[i] = d == N ? -1 : d;
        }
        return ans;
    }
}
