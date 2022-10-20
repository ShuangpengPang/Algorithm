package com.shuangpeng.Problem.p0701_0800;

/**
 * @Description: Problem0779KthSymbolInGrammar（第K个语法符号）
 * @Date 2022/10/20 10:04 AM
 * @Version 1.0
 */
public class Problem0779KthSymbolInGrammar {

    public int kthGrammar(int n, int k) {
        return dfs(n, k, 0);
    }

    private int dfs(int n, int k, int num) {
        if (n == 1 && k == 1) {
            return num;
        }
        int h = 1 << (n - 2);
        if (k <= h) {
            return dfs(n - 1, k, num);
        } else {
            return dfs(n - 1, k - h, 1 - num);
        }
    }
}
