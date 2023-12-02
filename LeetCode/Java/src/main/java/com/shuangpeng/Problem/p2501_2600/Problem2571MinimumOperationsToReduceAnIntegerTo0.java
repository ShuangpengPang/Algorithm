package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2571MinimumOperationsToReduceAnIntegerTo0（将整数减少到零需要的最少操作数）
 * @date 2023/12/2 4:57 PM
 */
public class Problem2571MinimumOperationsToReduceAnIntegerTo0 {

    public int minOperations(int n) {
        return dfs(n);
    }

    private int dfs(int n) {
        if (n < 2) {
            return n;
        }
        while ((n & 1) == 0) {
            n >>= 1;
        }
        if (n == 1) {
            return 1;
        }
        return 1 + Math.min(dfs(n + 1), dfs(n - 1));
    }
}
