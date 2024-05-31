package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3151SpecialArrayI（特殊数组I）
 * @date 2024/5/31 10:52 AM
 */
public class Problem3151SpecialArrayI {

    public boolean isArraySpecial(int[] nums) {
        for (int n = nums.length, i = 1; i < n; i++) {
            if (((nums[i - 1] ^ nums[i]) & 1) == 0) {
                return false;
            }
        }
        return true;
    }
}
