package com.shuangpeng.Problem.p0901_1000;

/**
 * @Description: Problem0905SortArrayByParity
 * @Date 2022/4/28 10:51 AM
 * @Version 1.0
 */
public class Problem0905SortArrayByParity {

    public int[] sortArrayByParity(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            if (nums[i] % 2 == 0) {
                ++i;
            } else if (nums[j] % 2 == 1) {
                --j;
            } else {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                ++i;
                --j;
            }
        }
        return nums;
    }
}
