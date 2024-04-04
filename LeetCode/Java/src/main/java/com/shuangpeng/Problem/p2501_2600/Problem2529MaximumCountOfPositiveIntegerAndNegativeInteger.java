package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2529MaximumCountOfPositiveIntegerAndNegativeInteger（正整数和负整数的最大计数）
 * @date 2024/4/4 11:10 PM
 */
public class Problem2529MaximumCountOfPositiveIntegerAndNegativeInteger {

    public int maximumCount(int[] nums) {
        int n = nums.length, i = 0;
        while (i < n && nums[i] < 0) {
            i++;
        }
        int j = i;
        while (j < n && nums[j] == 0) {
            j++;
        }
        return Math.max(i, n - j);
    }
}
