package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2544AlternatingDigitSum（交替数字和）
 * @date 2023/7/12 10:29 AM
 */
public class Problem2544AlternatingDigitSum {

    public int alternateDigitSum(int n) {
        int m = n, s = 1;
        while (m != 0) {
            m /= 10;
            s = -s;
        }
        s = -s;
        int sum = 0;
        while (n != 0) {
            sum += n % 10 * s;
            n /= 10;
            s = -s;
        }
        return sum;
    }
}
