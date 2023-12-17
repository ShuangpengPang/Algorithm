package com.shuangpeng.Problem.p0701_0800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0724FindPivotIndex（寻找数组的中心下标）
 * @date 2023/12/17 10:44 AM
 */
public class Problem0724FindPivotIndex {

    public int pivotIndex(int[] nums) {
        int n = nums.length, left = 0;
        for (int num : nums) {
            left += num;
        }
        int right = 0, idx = -1;
        for (int i = n - 1; i >= 0; i--) {
            left -= nums[i];
            if (left == right) {
                idx = i;
            }
            right += nums[i];
        }
        return idx;
    }
}
