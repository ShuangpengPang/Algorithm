package com.shuangpeng.competition.第229场周赛;

public class Problem5687 {

    long max = 0;
    public int maximumScore0(int[] nums, int[] multipliers) {
        backtrack(nums, multipliers, 0, nums.length - 1, 0, 0);
        return (int) max;
    }

    public void backtrack(int[] nums, int[] multipliers, int start, int end, int i, long result) {
        if (i == multipliers.length) {
            max = Math.max(max, result);
            return;
        }
        long sum = result + multipliers[i] * nums[start];
        backtrack(nums, multipliers, start + 1, end, i + 1, sum);
        sum = result + multipliers[i] * nums[end];
        backtrack(nums, multipliers, start, end - 1, i + 1, sum);
    }

    public int maximumScore1(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        if (n == 0 || m == 0 || n < m) {
            return 0;
        }
        int[][] dp = new int[m + 1][m + 1];
        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[j][i - j] = dp[j][i - j - 1] + nums[n - i + j] * multipliers[i - 1];
                } else if (j == i) {
                    dp[j][i - j] = dp[j - 1][i - j] + nums[j - 1] * multipliers[i - 1];
                } else {
                    dp[j][i - j] = Math.max(dp[j - 1][i - j] + nums[j - 1] * multipliers[i - 1],
                            dp[j][i - j - 1] + nums[n - i + j] * multipliers[i - 1]);
                }
            }
        }
        for (int i = 0; i <= m; i++) {
            answer = Math.max(answer, dp[i][m - i]);
        }
        return answer;
    }

    public int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        if (n == 0 || m == 0 || n < m) {
            return 0;
        }
        int[][] dp = new int[m + 1][m + 1];
        int answer = Integer.MIN_VALUE;
        for (int c = 1; c <= m; c++) {
            for (int l = 0; l <= c; l++) {
                int r = c - l;
//                if (l != 0) {
//                    dp[l][r] = Math.max(dp[l][r], dp[l - 1][r] + nums[l - 1] * multipliers[c - 1]);
//                }
//                if (r != 0) {
//                    dp[l][r] = Math.max(dp[l][r], dp[l][r - 1] + nums[n - r] * multipliers[c - 1]);
//                }

                if (l == 0) {
                    dp[l][r] = dp[l][r - 1] + nums[n - r] * multipliers[c - 1];
                } else if (r == 0) {
                    dp[l][r] = dp[l - 1][r] + nums[l - 1] * multipliers[c - 1];
                } else {
                    dp[l][r] = dp[l][r - 1] + nums[n - r] * multipliers[c - 1];
                    dp[l][r] = Math.max(dp[l][r], dp[l - 1][r] + nums[l - 1] * multipliers[c - 1]);
                }

//                if (l == 0) {
//                    dp[l][r] = dp[l][r - 1] + nums[n - r] * multipliers[c - 1];
//                } else if (r == 0) {
//                    dp[l][r] = dp[l - 1][r] + nums[l - 1] * multipliers[c - 1];
//                } else {
//                    dp[l][r] = dp[l - 1][r] + nums[l - 1] * multipliers[c - 1];
//                    dp[l][r] = Math.max(dp[l][r], dp[l][r - 1] + nums[n - r] * multipliers[c - 1]);
//                }
            }
        }
        for (int i = 0; i <= m; i++) {
            answer = Math.max(answer, dp[i][m - i]);
        }
        return answer;
    }
}
