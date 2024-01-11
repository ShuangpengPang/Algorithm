package com.shuangpeng.Problem.p0901_1000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0922SortArrayByParityII（按奇偶排序数组II）
 * @date 2024/1/11 10:39 AM
 */
public class Problem0922SortArrayByParityII {

    public int[] sortArrayByParityII(int[] nums) {
        int n = nums.length, i = 0, j = 1;
        while (i < n && j < n) {
            if ((nums[i] & 1) == 0) {
                i += 2;
            } else if ((nums[j] & 1) == 1) {
                j += 2;
            } else {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i += 2;
                j += 2;
            }
        }
        return nums;
    }
}
