package com.shuangpeng.Problem.p2801_2900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2874MaximumValueOfAnOrderedTripletII（有序三元组中的最大值II）
 * @date 2023/12/21 10:23 PM
 */
public class Problem2874MaximumValueOfAnOrderedTripletII {

    public long maximumTripletValue(int[] nums) {
        int n = nums.length, maxNum = Math.max(nums[0], nums[1]), minNum = Math.min(nums[0], nums[1]);
        long ans = 0, maxDiff = nums[0] - nums[1], minDiff = maxDiff;
        for (int i = 2; i < n; i++) {
            ans = Math.max(ans, Math.max(maxDiff * nums[i], minDiff * nums[i]));
            maxDiff = Math.max(maxDiff, maxNum - nums[i]);
            minDiff = Math.min(minDiff, minNum - nums[i]);
            maxNum = Math.max(maxNum, nums[i]);
            minNum = Math.min(minNum, nums[i]);
        }
        return ans;
    }
}
