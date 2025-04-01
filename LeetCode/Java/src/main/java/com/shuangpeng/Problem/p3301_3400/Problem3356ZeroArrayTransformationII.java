package com.shuangpeng.Problem.p3301_3400;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3356ZeroArrayTransformationII（零数组变换II）
 * @date 2025/4/1 11:40
 */
public class Problem3356ZeroArrayTransformationII {

    public int minZeroArray(int[] nums, int[][] queries) {
        int left = -1, right = queries.length - 1;
        int n = nums.length;
        int[] diff = new int[n + 1];
        while (left <= right) {
            int mid = left + (right - left >> 1);
            Arrays.fill(diff, 0);
            if (!check(nums, queries, diff, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left == queries.length ? -1 : left + 1;
    }

    private boolean check(int[] nums, int[][] queries, int[] diff, int k) {
        for (int i = 0; i <= k; i++) {
            diff[queries[i][0]] += queries[i][2];
            diff[queries[i][1] + 1] -= queries[i][2];
        }
        for (int i = 0, s = 0, n = nums.length; i < n; i++) {
            s += diff[i];
            if (nums[i] > s) {
                return false;
            }
        }
        return true;
    }
}
