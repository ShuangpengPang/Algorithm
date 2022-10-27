package com.shuangpeng.Problem.p1801_1900;

/**
 * @Description: Problem1822SignOfTheProductOfAnArray（数组元素积的符号）
 * @Date 2022/10/27 10:15 AM
 * @Version 1.0
 */
public class Problem1822SignOfTheProductOfAnArray {

    public int arraySign(int[] nums) {
        int sign = 1;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            }
            sign = num < 0 ? -sign : sign;
        }
        return sign;
    }
}
