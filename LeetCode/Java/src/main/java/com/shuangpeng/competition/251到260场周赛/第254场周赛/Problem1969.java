package com.shuangpeng.competition.第254场周赛;

public class Problem1969 {

    public int minNonZeroProduct(int p) {
        final int M = (int) 1e9 + 7;
        int max = getExp(2, p) - 1;
        return (int) ((long) get(max - 1, p - 1) * max % M);
    }

    private int getExp(int base, int exp) {
        final int M = (int) 1e9 + 7;
        int result = 1;
        int k = base;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (int) ((long) result * k % M);
            }
            exp >>= 1;
            k = (int) ((long) k * k % M);
        }
        return result;
    }

    private int get(int base, int loop) {
        final int M = (int) 1e9 + 7;
        int result = 1;
        while (loop > 0) {
            result = (int) ((long) result * base % M);
            base = (int) ((long) base * base % M);
            loop--;
        }
        return result;
    }
}
