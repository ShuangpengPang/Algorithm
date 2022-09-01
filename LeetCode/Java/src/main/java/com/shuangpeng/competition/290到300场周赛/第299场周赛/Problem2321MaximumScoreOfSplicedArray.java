package com.shuangpeng.competition.第299场周赛;

/**
 * @Description: Problem2321MaximumScoreOfSplicedArray（拼接数组的最大分数）
 * @Date 2022/7/7 5:31 PM
 * @Version 1.0
 */
public class Problem2321MaximumScoreOfSplicedArray {

    // 比赛时写法
    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        return Math.max(getMax(nums1, nums2), getMax(nums2, nums1));
    }

    private int getMax(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] dp = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums1[i];
            dp[i] = nums2[i] - nums1[i];
        }
        int max = 0, cur = 0;
        for (int i = 0; i < n; i++) {
            cur = Math.max(dp[i], dp[i] + cur);
            max = Math.max(max, cur);
        }
        return sum + max;
    }
}

class Problem2321MaximumScoreOfSplicedArray0 {

    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        return Math.max(maxSum(nums1, nums2), maxSum(nums2, nums1));
    }

    private int maxSum(int[] nums1, int[] nums2) {
        int n = nums1.length, sum = 0, maxSum = 0;
        for (int i = 0, s = 0; i < n; i++) {
            sum += nums1[i];
            s = Math.max(nums2[i] - nums1[i], s + nums2[i] - nums1[i]);
            maxSum = Math.max(maxSum, s);
        }
        return sum + maxSum;
    }
}
