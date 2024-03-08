package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1523CountOddNumbersInAnIntervalRange（在区间范围内统计奇数数目）
 * @date 2024/3/8 11:15 AM
 */
public class Problem1523CountOddNumbersInAnIntervalRange {

    public int countOdds(int low, int high) {
        return (high + 1 >> 1) - (low >> 1);
    }
}
