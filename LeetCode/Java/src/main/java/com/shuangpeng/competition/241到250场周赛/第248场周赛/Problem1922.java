package com.shuangpeng.competition.第248场周赛;

import java.math.BigInteger;

public class Problem1922 {

    public int countGoodNumbers(long n) {
        return (int) (quickPower(5, (n + 1) / 2) * quickPower(4, n / 2) % ((int) (1e9 + 7)));
    }

    private long quickPower(long n, long e) {
        int mod = (int) (1e9 + 7);
        long answer = 1;
        while (e > 0) {
            if ((e & 1) == 1) {
                answer = answer * n % mod;
            }
            e >>= 1;
            n = n * n % mod;
        }
        return answer;
    }
}
