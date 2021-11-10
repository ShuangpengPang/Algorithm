package com.shuangpeng.Problem.p1001_1100;

public class Problem1031MaximumSumOfTwoNonOverlappingSubarrays {

    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int[] sum1 = new int[n];
        int[] sum2 = new int[n];
        calculateSum(nums, sum1, firstLen);
        calculateSum(nums, sum2, secondLen);
        int[] left = new int[n];
        int maxSum = 0;
        for (int i = firstLen; i <= n - secondLen; ++i) {
            maxSum = Math.max(maxSum, sum1[i - firstLen]);
            left[i] = maxSum;
        }
        int[] right = new int[n];
        maxSum = 0;
        for (int i = n - firstLen - 1; i >= secondLen - 1; --i) {
            maxSum = Math.max(maxSum, sum1[i + 1]);
            right[i] = maxSum;
        }
        int ans = 0;
        for (int i = 0; i <= n - secondLen; ++i) {
            int sum = sum2[i] + Math.max(left[i], right[i + secondLen - 1]);
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    private void calculateSum(int[] nums, int[] sum, int length) {
        int n = sum.length;
        int s = 0;
        for (int i = 0, j = 0; j < n; ++j) {
            s += nums[j];
            if (j >= length) {
                s -= nums[i];
                ++i;
            }
            sum[i] = s;
        }
    }
}
