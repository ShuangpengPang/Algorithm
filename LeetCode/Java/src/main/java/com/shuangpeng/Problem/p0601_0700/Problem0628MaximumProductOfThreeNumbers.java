package com.shuangpeng.Problem.p0601_0700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0628MaximumProductOfThreeNumbers（三个数的最大乘积）
 * @date 2023/12/14 3:16 PM
 */
public class Problem0628MaximumProductOfThreeNumbers {

    public int maximumProduct(int[] nums) {
        int n = nums.length;
        int one1 = Math.min(nums[0], Math.min(nums[1], nums[2]));
        int one2 = Math.max(nums[0], Math.max(nums[1], nums[2]));
        int two1 = Math.min(nums[0] * nums[1], Math.min(nums[0] * nums[2], nums[1] * nums[2]));
        int two2 = Math.max(nums[0] * nums[1], Math.max(nums[0] * nums[2], nums[1] * nums[2]));
        int ans = nums[0] * nums[1] * nums[2];
        for (int i = 3; i < n; i++) {
            ans = Math.max(ans, Math.max(two1 * nums[i], two2 * nums[i]));
            int p1 = one1 * nums[i], p2 = one2 * nums[i];
            two1 = Math.min(two1, Math.min(p1, p2));
            two2 = Math.max(two2, Math.max(p1, p2));
            one1 = Math.min(one1, nums[i]);
            one2 = Math.max(one2, nums[i]);
        }
        return ans;
    }
}
