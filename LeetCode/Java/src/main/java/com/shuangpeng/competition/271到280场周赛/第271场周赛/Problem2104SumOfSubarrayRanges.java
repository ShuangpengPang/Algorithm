package com.shuangpeng.competition.第271场周赛;

public class Problem2104SumOfSubarrayRanges {

    // 比赛时写法
    public long subArrayRanges0(int[] nums) {
        int n = nums.length;
        int[][] dp1 = new int[n][n];
        int[][] dp2 = new int[n][n];
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            dp1[i][i] = nums[i];
            dp2[i][i] = nums[i];
            for (int j = i + 1; j < n; ++j) {
                dp1[i][j] = Math.min(dp1[i][j - 1], nums[j]);
                dp2[i][j] = Math.max(dp2[i][j - 1], nums[j]);
                ans += dp2[i][j] - dp1[i][j];
            }
        }
        return ans;
    }

    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            int minValue = nums[i], maxValue = nums[i];
            for (int j = i + 1; j < n; ++j) {
                minValue = Math.min(minValue, nums[j]);
                maxValue = Math.max(maxValue, nums[j]);
                ans += maxValue - minValue;
            }
        }
        return ans;
    }
}
