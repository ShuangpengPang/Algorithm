package com.shuangpeng.Problem.p1001_1100;

/**
 * @description: 两个非重叠子数组的最大和
 * @date 2023/4/26 10:33 AM
 **/
public class Problem1031MaximumSumOfTwoNonOverlappingSubarrays {

    public int maxSumTwoNoOverlap0(int[] nums, int firstLen, int secondLen) {
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

    public int maxSumTwoNoOverlap1(int[] nums, int firstLen, int secondLen) {
        int n = nums.length, ans = 0;
        int[] m1 = new int[n + 1], m2 = new int[n + 1];
        for (int i = 1, s1 = 0, s2 = 0; i <= n; i++) {
            int num = nums[i - 1];
            s1 += num;
            s2 += num;
            if (i > firstLen) {
                s1 -= nums[i - firstLen - 1];
            }
            if (i > secondLen) {
                s2 -= nums[i - secondLen - 1];
            }
            if (i >= firstLen) {
                m1[i] = Math.max(m1[i - 1], s1);
                ans = Math.max(ans, s1 + m2[i - firstLen]);
            }
            if (i >= secondLen) {
                m2[i] = Math.max(m2[i - 1], s2);
                ans = Math.max(ans, s2 + m1[i - secondLen]);
            }
        }
        return ans;
    }

    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        return Math.max(getMaxSum(nums, firstLen, secondLen), getMaxSum(nums, secondLen, firstLen));
    }

    private int getMaxSum(int[] nums, int n1, int n2) {
        int n = nums.length, m = 0, s1 = 0, s2 = 0, t = n1 + n2;
        for (int i = 0; i < n1; i++) {
            s1 += nums[i];
        }
        for (int i = n1; i < t; i++) {
            s2 += nums[i];
        }
        m = s1;
        int ans = s1 + s2;
        for (int i = n1, j = t; j < n; i++, j++) {
            s1 += nums[i] - nums[i - n1];
            s2 += nums[j] - nums[j - n2];
            m = Math.max(m, s1);
            ans = Math.max(ans, s2 + m);
        }
        return ans;
    }
}
