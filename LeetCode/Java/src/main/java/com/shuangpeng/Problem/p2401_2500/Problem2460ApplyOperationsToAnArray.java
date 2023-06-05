package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2460ApplyOperationsToAnArray（对数组执行操作）
 * @date 2023/6/5 10:22 AM
 */
public class Problem2460ApplyOperationsToAnArray {

    public int[] applyOperations(int[] nums) {
        int n = nums.length, p = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                if (i < n - 1 && nums[i] == nums[i + 1]) {
                    nums[i] <<= 1;
                    nums[i + 1] = 0;
                }
                if (p != i) {
                    nums[p] = nums[i];
                    nums[i] = 0;
                }
                p++;
            }
        }
        return nums;
    }
}
