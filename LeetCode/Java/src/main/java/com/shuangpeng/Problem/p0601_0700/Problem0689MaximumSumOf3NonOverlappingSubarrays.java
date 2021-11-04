package com.shuangpeng.Problem.p0601_0700;

public class Problem0689MaximumSumOf3NonOverlappingSubarrays {

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        if (n < 3) {
            return null;
        }
        int[] sums = new int[n - k + 1];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            int j = i - k + 1;
            if (j > 0) {
                sum -= nums[j - 1];
            }
            if (j >= 0) {
                sums[j] = sum;
            }
        }
        int[] left = new int[sums.length];
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = 0; i < left.length; i++) {
            if (sums[i] > max) {
                max = sums[i];
                maxIndex = i;
            }
            left[i] = maxIndex;
        }
        int[] right = new int[sums.length];
        max = Integer.MIN_VALUE;
        maxIndex = 0;
        for (int i = right.length - 1; i >= 0; i--) {
            if (sums[i] >= max) {
                max = sums[i];
                maxIndex = i;
            }
            right[i] = maxIndex;
        }
        int maxSum = Integer.MIN_VALUE;
        int a = 0, b = 0, c = 0;
        for (int i = k; i <= n - (k << 1); i++) {
            int add = sums[i] + sums[left[i - k]] + sums[right[i + k]];
            if (add > maxSum) {
                maxSum = add;
                a = left[i - k];
                b = i;
                c = right[i + k];
            }
        }
        return new int[]{a, b, c};
    }
}
