package com.shuangpeng.Problem.p0101_0200;

import java.math.BigInteger;

public class Problem0172FactorialTrailingZeroes {

    public int trailingZeroes0(int n) {
        int base = 5;
        int answer = 0;
        while (n >= base) {
            answer += n / base;
            base *= 5;
        }
        return answer;
    }

    public int trailingZeroes(int n) {
        if (n < 5) {
            return 0;
        }
        BigInteger result = BigInteger.valueOf(1);
        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        int count = 0;
        while (result.mod(BigInteger.TEN).equals(BigInteger.ZERO)) {
            count++;
            result = result.divide(BigInteger.TEN);
        }
        return count;
    }
}
