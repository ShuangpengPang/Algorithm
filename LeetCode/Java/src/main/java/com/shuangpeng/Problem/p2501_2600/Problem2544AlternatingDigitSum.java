package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2544AlternatingDigitSum（交替数字和）
 * @date 2023/7/12 10:29 AM
 */
public class Problem2544AlternatingDigitSum {

    public int alternateDigitSum0(int n) {
        int sign = 1, sum = 0;
        while (n != 0) {
            sum += n % 10 * sign;
            n /= 10;
            sign = -sign;
        }
        return -sum * sign;
    }

    public int alternateDigitSum(int n) {
        int sum = 0;
        while (n != 0) {
            sum = n % 10 - sum;
            n /= 10;
        }
        return sum;
    }
}
