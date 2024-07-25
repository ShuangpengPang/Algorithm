package com.shuangpeng.lcr.p101_200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR134MyPow（LCR 134. Pow(x, n)）
 * @date 2024/7/25 6:05 PM
 */
public class LCR134MyPow {

    public double myPow0(double x, int n) {
        double ans = 1;
        long p = n;
        boolean isNegative = false;
        if (p < 0) {
            isNegative = true;
            p = -p;
        }
        while (p != 0) {
            if ((p & 1) == 1) {
                ans *= x;
            }
            x *= x;
            p >>= 1;
        }
        return isNegative ? 1 / ans : ans;
    }

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == Integer.MIN_VALUE) {
            return myPow(x, n+1) / x;
        }
        if (n < 0) {
            return 1 / myPow(x, -n);
        }
        if ((n & 1) == 1) {
            return x * myPow(x*x, n/2);
        }
        return myPow(x*x, n/2);
    }
}
