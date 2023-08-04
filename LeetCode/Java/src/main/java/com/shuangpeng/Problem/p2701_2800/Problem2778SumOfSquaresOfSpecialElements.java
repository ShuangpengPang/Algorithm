package com.shuangpeng.Problem.p2701_2800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2778SumOfSquaresOfSpecialElements（特殊元素平方和）
 * @date 2023/8/4 6:21 PM
 */
public class Problem2778SumOfSquaresOfSpecialElements {

    public int sumOfSquares(int[] nums) {
        int n = nums.length, ans = 0;
        for (int i = 0; i < n; i++) {
            if (n % (i + 1) == 0) {
                ans += nums[i] * nums[i];
            }
        }
        return ans;
    }
}
