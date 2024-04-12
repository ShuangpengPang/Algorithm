package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2932MaximumStrongPairXORI（找出强数对的最大异或值I）
 * @date 2024/4/12 5:28 PM
 */
public class Problem2932MaximumStrongPairXORI {

    public int maximumStrongPairXor(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] << 1 >= nums[j] && nums[j] << 1 >= nums[i]) {
                    ans = Math.max(ans, nums[i] ^ nums[j]);
                }
            }
        }
        return ans;
    }
}
