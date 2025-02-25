package com.shuangpeng.Problem.p3301_3400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3396MinimumNumberOfOperationsToMakeElementsInArrayDistinct（使数组元素互不相同所需的最少操作次数）
 * @date 2025/2/24 7:42 PM
 */
public class Problem3396MinimumNumberOfOperationsToMakeElementsInArrayDistinct {

    public int minimumOperations(int[] nums) {
        boolean[] set = new boolean[101];
        for (int i = nums.length - 1; i >= 0; i--) {
            if (set[nums[i]]) {
                return (i + 3) / 3;
            }
            set[nums[i]] = true;
        }
        return 0;
    }
}
