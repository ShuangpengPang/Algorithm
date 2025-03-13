package com.shuangpeng.Problem.p3401_3500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3467TransformArrayByParity（将数组按照奇偶性转化）
 * @date 2025/3/13 11:03
 */
public class Problem3467TransformArrayByParity {

    public int[] transformArray(int[] nums) {
        int n = nums.length;
        int p = 0;
        for (int i = 0; i < n; i++) {
            if ((nums[i] & 1) == 0) {
                nums[i] = 1;
                nums[p++] = 0;
            }
        }
        while (p < n) {
            nums[p++] = 1;
        }
        return nums;
    }
}
