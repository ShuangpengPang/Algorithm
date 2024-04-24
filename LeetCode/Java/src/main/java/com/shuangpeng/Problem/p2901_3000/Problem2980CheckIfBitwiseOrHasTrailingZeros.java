package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2980CheckIfBitwiseOrHasTrailingZeros（检查按位或是否存在尾随零）
 * @date 2024/4/24 3:05 PM
 */
public class Problem2980CheckIfBitwiseOrHasTrailingZeros {

    public boolean hasTrailingZeros(int[] nums) {
        int cnt = 0;
        for (int num : nums) {
            if ((cnt += num & 1 ^ 1) > 1) {
                return true;
            }
        }
        return false;
    }
}
