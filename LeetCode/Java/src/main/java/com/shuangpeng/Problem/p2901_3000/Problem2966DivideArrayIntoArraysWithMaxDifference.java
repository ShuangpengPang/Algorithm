package com.shuangpeng.Problem.p2901_3000;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2966DivideArrayIntoArraysWithMaxDifference（划分数组并满足最大差限制）
 * @date 2024/1/11 4:47 PM
 */
public class Problem2966DivideArrayIntoArraysWithMaxDifference {

    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, m = n / 3;
        int[][] ans = new int[m][];
        for (int i = 0; i < n; i += 3) {
            if (nums[i + 2] - nums[i] > k) {
                return new int[0][];
            }
            ans[i / 3] = new int[]{nums[i], nums[i + 1], nums[i + 2]};
        }
        return ans;
    }
}
