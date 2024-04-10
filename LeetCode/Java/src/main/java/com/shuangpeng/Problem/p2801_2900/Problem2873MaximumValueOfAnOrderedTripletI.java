package com.shuangpeng.Problem.p2801_2900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2873MaximumValueOfAnOrderedTripletI（有序三元组中的最大值I）
 * @date 2024/4/10 3:38 PM
 */
public class Problem2873MaximumValueOfAnOrderedTripletI {

    public long maximumTripletValue0(int[] nums) {
        int n = nums.length;
        int[] suffix = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = Math.max(nums[i], suffix[i + 1]);
        }
        long ans = 0;
        for (int i = 0, max = 0; i < n - 1; i++) {
            ans = Math.max(ans, (long) (max - nums[i]) * suffix[i + 1]);
            max = Math.max(max, nums[i]);
        }
        return ans;
    }

    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        long minValue = Math.min(nums[0], nums[1]), maxValue = Math.max(nums[0], nums[1]);
        long minDiff = nums[0] - nums[1], maxDiff = minDiff;
        long ans = 0;
        for (int i = 2; i < n; i++) {
            ans = Math.max(ans, Math.max(nums[i] * minDiff, nums[i] * maxDiff));
            long d1 = minValue - nums[i], d2 = maxValue - nums[i];
            minDiff = Math.min(minDiff, Math.min(d1, d2));
            maxDiff = Math.max(maxDiff, Math.max(d1, d2));
            minValue = Math.min(minValue, nums[i]);
            maxValue = Math.max(maxValue, nums[i]);
        }
        return ans;
    }
}
