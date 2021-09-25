package com.shuangpeng.Problem;

public class Problem1035UncrossedLines {

    public int maxUncrossedLines0(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 0; i < n1; ++i) {
            for (int j = 0; j < n2; ++j) {
                int temp = dp[i][j];
                int idx1 = getPreIndex(nums2, nums1[i], j);
                if (idx1 >= 0) {
                    temp = Math.max(temp, dp[i][idx1] + 1);
                }
                int idx2 = getPreIndex(nums1, nums2[j], i);
                if (idx2 >= 0) {
                    temp = Math.max(temp, dp[idx2][j] + 1);
                }
                dp[i + 1][j + 1] = temp;
            }
        }
        return dp[n1][n2];
    }

    private int getPreIndex(int[] nums, int num, int i) {
        while (i >= 0 && nums[i] != num) {
            --i;
        }
        return i;
    }

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 0; i < n1; ++i) {
            for (int j = 0; j < n2; ++j) {
                if (nums1[i] == nums2[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        return dp[n1][n2];
    }
}
