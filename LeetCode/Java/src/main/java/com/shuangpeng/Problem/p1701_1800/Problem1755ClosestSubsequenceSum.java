package com.shuangpeng.Problem.p1701_1800;

import java.util.Arrays;

/**
 * @Description: Problem1755ClosestSubsequenceSum（最接近目标值的子序列和）
 * @Date 2022/10/9 4:09 PM
 * @Version 1.0
 */
public class Problem1755ClosestSubsequenceSum {

    public int minAbsDifference(int[] nums, int goal) {
        if (goal == 0) {
            return 0;
        }
        int n = nums.length, h1 = n >> 1, h2 = n - h1, N1 = 1 << h1, N2 = 1 << h2;
        int[] sum1 = new int[N1];
        for (int i = 1; i < N1; i++) {
            for (int j = 0; j < h1; j++) {
                if (((i >> j) & 1) == 1) {
                    sum1[i] = sum1[i ^ (1 << j)] + nums[j];
                    break;
                }
            }
        }
        Arrays.sort(sum1);
        int ans = Math.abs(goal);
        int[] sum2 = new int[N2];
        for (int i = 0; i < N2; i++) {
            for (int j = 0; j < h2; j++) {
                if (((i >> j) & 1) == 1) {
                    sum2[i] = sum2[i ^ (1 << j)] + nums[h1 + j];
                    break;
                }
            }
            int num = goal - sum2[i];
            int k = getIndex(sum1, num);
            if (k > 0) {
                ans = Math.min(ans, num - sum1[k - 1]);
            }
            if (k < N1) {
                ans = Math.min(ans, sum1[k] - num);
            }
            if (ans == 0) {
                return 0;
            }
        }
        return ans;
    }

    private int getIndex(int[] sum, int target) {
        int left = 0, right = sum.length - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (sum[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
