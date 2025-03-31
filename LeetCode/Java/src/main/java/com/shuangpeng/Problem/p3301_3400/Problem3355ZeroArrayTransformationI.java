package com.shuangpeng.Problem.p3301_3400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3355ZeroArrayTransformationI（零数组变换I）
 * @date 2025/3/31 19:20
 */
public class Problem3355ZeroArrayTransformationI {

    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] diff = new int[n + 1];
        for (int[] q : queries) {
            diff[q[0]]++;
            diff[q[1] + 1]--;
        }
        for (int i = 0, s = 0; i < n; i++) {
            s += diff[i];
            if (nums[i] > s) {
                return false;
            }
        }
        return true;
    }
}
