package com.shuangpeng.Problem.p3301_3400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3392CountSubarraysOfLengthThreeWithACondition（统计符合条件长度为3的子数组数目）
 * @date 2025/2/25 11:27 AM
 */
public class Problem3392CountSubarraysOfLengthThreeWithACondition {

    public int countSubarrays(int[] nums) {
        int n = nums.length, ans = 0;
        for (int i = 2; i < n; i++) {
            if ((nums[i - 2] + nums[i]) << 1 == nums[i - 1]) {
                ans++;
            }
        }
        return ans;
    }
}
