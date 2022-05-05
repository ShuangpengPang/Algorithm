package com.shuangpeng.Problem.p0901_1000;

/**
 * @Description: Problem0908SmallestRangeI
 * @Date 2022/5/5 5:32 PM
 * @Version 1.0
 */
public class Problem0908SmallestRangeI {

    public int smallestRangeI0(int[] nums, int k) {
        int minValue = Integer.MAX_VALUE, maxValue = Integer.MIN_VALUE;
        for (int num : nums) {
            minValue = Math.min(minValue, num);
            maxValue = Math.max(maxValue, num);
        }
        int ans = maxValue - minValue - (k << 1);
        return ans < 0 ? 0 : ans;
    }

    public int smallestRangeI(int[] nums, int k) {
        int max = nums[0], min = nums[0];
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }
        int ans = max - min - (k << 1);
        return ans < 0 ? 0 : ans;
    }
}
