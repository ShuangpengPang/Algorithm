package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2535DifferenceBetweenElementSumAndDigitSumOfAnArray（数组元素和与数字和的绝对差）
 * @date 2024/4/4 9:41 PM
 */
public class Problem2535DifferenceBetweenElementSumAndDigitSumOfAnArray {

    public int differenceOfSum(int[] nums) {
        int s1 = 0, s2 = 0;
        for (int num : nums) {
            s1 += num;
            while (num != 0) {
                s2 += num % 10;
                num /= 10;
            }
        }
        return Math.abs(s1 - s2);
    }
}
