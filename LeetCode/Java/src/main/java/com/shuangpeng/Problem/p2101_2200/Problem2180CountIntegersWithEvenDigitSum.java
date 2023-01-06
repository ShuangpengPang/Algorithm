package com.shuangpeng.Problem.p2101_2200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2180CountIntegersWithEvenDigitSum（统计各位数字之和为偶数的整数个数）
 * @date 2023/1/6 3:39 PM
 */
public class Problem2180CountIntegersWithEvenDigitSum {

    static final int N = 1000;
    static final int[] memo = new int[N + 1];
    static {
        for (int i = 1; i <= N; i++) {
            memo[i] = memo[i - 1];
            int s = 0, j = i;
            while (j > 0) {
                s += j % 10;
                j /= 10;
            }
            if ((s & 1) == 0) {
                memo[i]++;
            }
        }
    }

    public int countEven(int num) {
        return memo[num];
    }
}
