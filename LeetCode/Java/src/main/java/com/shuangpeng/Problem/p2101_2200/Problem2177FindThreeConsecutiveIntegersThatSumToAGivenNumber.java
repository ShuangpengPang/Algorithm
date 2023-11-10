package com.shuangpeng.Problem.p2101_2200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2177FindThreeConsecutiveIntegersThatSumToAGivenNumber（找到和为给定整数的三个连续整数）
 * @date 2023/11/10 11:32 PM
 */
public class Problem2177FindThreeConsecutiveIntegersThatSumToAGivenNumber {

    public long[] sumOfThree(long num) {
        if (num % 3 != 0) {
            return new long[0];
        }
        long mid = num / 3;
        return new long[]{mid - 1, mid, mid + 1};
    }
}
