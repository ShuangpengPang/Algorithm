package com.shuangpeng.Problem.p3301_3400;

import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3314ConstructTheMinimumBitwiseArrayI（构造最小位运算数组I）
 * @date 2025/3/20 18:58
 */
public class Problem3314ConstructTheMinimumBitwiseArrayI {

    public int[] minBitwiseArray0(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nums.get(i), y = x + 1, z = y & -y;
            ans[i] = (x & y) | ((z >> 1) - 1);
        }
        return ans;
    }

    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nums.get(i), y = ((~x) & -(~x)) >> 1;
            ans[i] = x == 2 ? -1 : x ^ y;
        }
        return ans;
    }
}
