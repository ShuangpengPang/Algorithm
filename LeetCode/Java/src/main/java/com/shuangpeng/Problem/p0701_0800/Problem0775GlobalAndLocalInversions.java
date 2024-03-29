package com.shuangpeng.Problem.p0701_0800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0775GlobalAndLocalInversions（全局倒置与局部倒置）
 * @date 2022/11/16 10:03 AM
 */
public class Problem0775GlobalAndLocalInversions {

    public boolean isIdealPermutation0(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > i && (nums[i] != i + 1 || nums[i + 1] != i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isIdealPermutation1(int[] nums) {
        int n = nums.length, min = nums[n - 1];
        for (int i = n - 3; i >= 0; i--) {
            if (nums[i] > min) {
                return false;
            }
            min = Math.min(min, nums[i + 1]);
        }
        return true;
    }

    public boolean isIdealPermutation(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (Math.abs(nums[i] - i) > 1) {
                return false;
            }
        }
        return true;
    }
}
