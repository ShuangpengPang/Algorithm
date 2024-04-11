package com.shuangpeng.Problem.p2801_2900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2894DivisibleAndNonDivisibleSumsDifference（分类求和并作差）
 * @date 2024/4/11 4:38 PM
 */
public class Problem2894DivisibleAndNonDivisibleSumsDifference {

    public int differenceOfSums(int n, int m) {
        return ((n + 1) * n >> 1) - m * (1 + n / m) * (n / m);
    }
}
