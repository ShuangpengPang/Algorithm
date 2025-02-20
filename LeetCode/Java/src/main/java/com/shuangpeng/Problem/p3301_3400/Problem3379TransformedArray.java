package com.shuangpeng.Problem.p3301_3400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3379TransformedArray（转换数组）
 * @date 2025/2/20 7:21 PM
 */
public class Problem3379TransformedArray {

    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = nums[((i + nums[i]) % n + n) % n];
        }
        return ans;
    }
}
