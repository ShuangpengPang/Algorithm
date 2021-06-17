package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem0410SplitArrayLargestSum {

    public int splitArray0(int[] nums, int m) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        sum[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        int[][][] dp = new int[n][n][m];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[i][j][0] = sum[j + 1] - sum[i];
            }
        }
        for (int j = 1; j < n; j++) {
            for (int i = j - 1; i >= 0; i--) {
                for (int k = 1; k < m && k <= j - i; k++) {
                    int min = Integer.MAX_VALUE;
                    for (int s = i; s < j; s++) {
                        // k - 1
                        int p = k - 1 - Math.min(k - 1, j - s - 1);
                        int q = Math.min(k - 1, s - i);
                        for (int c = p; c <= q; c++) {
                            min = Math.min(min, Math.max(dp[i][s][c], dp[s + 1][j][k - c - 1]));
                        }
                    }
                    dp[i][j][k] = min;
                }
            }
        }
        return dp[0][n - 1][m - 1];
    }

    public int splitArray1(int[] nums, int m) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        sum[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            dp[i][0] = sum[i + 1];
        }
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m && j <= i; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = i - 1; k >= j - 1; k--) {
                    min = Math.min(min, Math.max(dp[k][j - 1], sum[i + 1] - sum[k + 1]));
                }
                dp[i][j] = min;
            }
        }
        return dp[n - 1][m - 1];
    }

    public int splitArray2(int[] nums, int m) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                for (int k = j - 1; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], sum[i] - sum[k]));
                }
            }
        }
        return dp[n][m];
    }

    public int splitArray3(int[] nums, int m) {
        int left = 0, right = 0;
        for (int i = 0; i < nums.length; i++) {
            right += nums[i];
            if (left < nums[i]) {
                left = nums[i];
            }
        }
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (check(nums, mid, m)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean check(int[] nums, int x, int m) {
        int sum = 0;
        int cnt = 1;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] > x) {
                cnt++;
                sum = nums[i];
            } else {
                sum += nums[i];
            }
        }
        return cnt <= m;
    }

    public int splitArray(int[] nums, int m) {
        int left = 0, right = 0;
        for (int i = 0; i < nums.length; i++) {
            right += nums[i];
            left = Math.max(left, nums[i]);
        }
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (isValid(nums, mid, m)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean isValid(int[] nums, int t, int m) {
        int c = 1, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] <= t) {
                sum += nums[i];
            } else {
                c++;
                sum = nums[i];
            }
        }
        return c <= m;
    }
}
