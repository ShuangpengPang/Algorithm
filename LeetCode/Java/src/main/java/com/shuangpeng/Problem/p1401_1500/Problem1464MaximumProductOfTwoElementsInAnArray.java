package com.shuangpeng.Problem.p1401_1500;

/**
 * @Description: Problem1464MaximumProductOfTwoElementsInAnArray（数组中两数的最大乘积）
 * @Date 2022/8/26 10:20 AM
 * @Version 1.0
 */
public class Problem1464MaximumProductOfTwoElementsInAnArray {

    public int maxProduct(int[] nums) {
        int m1 = Integer.MIN_VALUE, m2 = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num >= m1) {
                m2 = m1;
                m1 = num;
            } else if (num > m2) {
                m2 = num;
            }
        }
        return (m1 - 1) * (m2 - 1);
    }
}
