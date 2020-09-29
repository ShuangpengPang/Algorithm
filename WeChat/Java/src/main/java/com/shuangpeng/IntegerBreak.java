package com.shuangpeng;

// 题目链接：https://mp.weixin.qq.com/s/Cb_jO2gp3aq31Msw6AtGbQ
// LeetCode链接：https://leetcode.com/problems/integer-break/submissions/
public class IntegerBreak {
    static int[] result = new int[60];
    static int maxN = 2;

    public int integerBreak(int n) {
        if (n < 2 || n > 58) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        if (result[n] > 0) {
            return result[n];
        }
        return build(n);
    }

    public int build(int n) {
        result[2] = 1;
        int start = maxN + 1;
        for (int i = start; i <= n; i++) {
            int mid = i / 2;
            int max = i - 1;
            for (int j = 2; j <= mid; j++) {
                int k = i - j;
                int maxJ = Math.max(j, result[j]);
                int maxK = Math.max(k, result[k]);
                max = Math.max(max, maxJ * maxK);
            }
            result[i] = max;
        }
        maxN = n;
        return result[n];
    }

    public int integerBreak0(int n) {
        if (n < 2 || n > 58) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        int power = n / 3;
        int remainder = n % 3;
        if (remainder == 0) {
            return (int) Math.pow(3, power);
        }
        if (remainder == 1) {
            return (int) (Math.pow(3, power - 1) * 4);
        }
        return (int) (Math.pow(3, power) * 2);
    }
}
