package com.shuangpeng.Problem.p1401_1500;

/**
 * @Description: Problem1470ShuffleTheArray（重新排列数组）
 * @Date 2022/8/29 9:56 AM
 * @Version 1.0
 */
public class Problem1470ShuffleTheArray {

    public int[] shuffle(int[] nums, int n) {
        int[] copy = nums.clone();
        for (int i = 0; i < n; i++) {
            nums[i << 1] = copy[i];
            nums[(i << 1) + 1] = copy[i + n];
        }
        return nums;
    }
}
