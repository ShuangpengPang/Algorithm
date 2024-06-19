package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3178FindTheChildWhoHasTheBallAfterKSeconds（找出K秒后拿着球的孩子）
 * @date 2024/6/17 7:43 PM
 */
public class Problem3178FindTheChildWhoHasTheBallAfterKSeconds {

    public int numberOfChild0(int n, int k) {
        int m = k % (n - 1 << 1);
        return m < n ? m : n - 1 - (m - n + 1);
    }

    public int numberOfChild1(int n, int k) {
        int t = k % (n - 1);
        return k / (n - 1) % 2 > 0 ? n - t - 1 : t;
    }

    public int numberOfChild(int n, int k) {
        int N = n - 1 << 1;
        k %= N;
        return Math.min(k, N - k);
    }
}
