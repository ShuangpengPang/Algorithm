package com.shuangpeng.Problem.p3101_3200;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3194MinimumAverageOfSmallestAndLargestElements（最小元素和最大元素的最小平均值）
 * @date 2024/7/24 12:16 AM
 */
public class Problem3194MinimumAverageOfSmallestAndLargestElements {

    public double minimumAverage(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, sum = nums[0] + nums[n - 1];
        for (int i = 1, h = n >> 1; i < h; i++) {
            sum = Math.min(sum, nums[i] + nums[n - i - 1]);
        }
        return sum / 2.0;
    }
}
