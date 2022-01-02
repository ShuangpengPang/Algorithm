package com.shuangpeng.Problem.p0301_0400;

public class Problem0390EliminationGame {

    public int lastRemaining(int n) {
        if (n == 1) {
            return 1;
        }
        if (n < 4) {
            return 2;
        }
        int m = lastRemaining(n >> 2);
        int s = ((n >> 1) & 1) == 0 ? 2 : 4;
        return s + ((m - 1) << 2);
    }
}
