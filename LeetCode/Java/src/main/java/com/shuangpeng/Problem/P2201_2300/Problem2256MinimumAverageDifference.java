package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2256MinimumAverageDifference（最小平均差）
 * @date 2023/11/16 11:16 PM
 */
public class Problem2256MinimumAverageDifference {

    public int minimumAverageDifference(int[] nums) {
        int n = nums.length;
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        long diff = sum / n, right = 0;
        int index = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            right += nums[i + 1];
            long d = Math.abs((sum - right) / (i + 1) - right / (n - i - 1));
            if (d <= diff) {
                diff = d;
                index = i;
            }
        }
        return index;
    }
}
