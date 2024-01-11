package com.shuangpeng.Problem.p2901_3000;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2971FindPolygonWithTheLargestPerimeter（找到最大周长的多边形）
 * @date 2024/1/11 10:31 PM
 */
public class Problem2971FindPolygonWithTheLargestPerimeter {

    public long largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        for (int i = nums.length - 1; i >= 2; i--) {
            if (nums[i] << 1 < sum) {
                return sum;
            }
            sum -= nums[i];
        }
        return -1;
    }
}
