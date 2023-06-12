package com.shuangpeng.Problem.p1101_1200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1191KConcatenationMaximumSum（K次串联后最大子数组和）
 * @date 2023/6/12 4:26 PM
 */
public class Problem1191KConcatenationMaximumSum {

    public int kConcatenationMaxSum(int[] arr, int k) {
        int n = arr.length;
        long M = (long) 1e9 + 7, sum = 0, maxSum = 0, maxLeft = 0, maxRight = 0;
        for (int i = 0, j = n - 1, s1 = 0, s2 = 0; i < n; i++, j--) {
            sum += arr[i];
            s2 += arr[j];
            maxLeft = Math.max(maxLeft, sum);
            maxRight = Math.max(maxRight, s2);
            s1 = Math.max(s1 + arr[i], arr[i]);
            maxSum = Math.max(maxSum, s1);
        }
        if (k == 1) {
            return (int) (maxSum % M);
        }
        return (int) (Math.max(maxSum, maxLeft + maxRight + (k - 2) * Math.max(0, sum)) % M);
    }
}
