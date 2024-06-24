package com.shuangpeng.lcr.p001_100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR072MySqrt（x的平方根）
 * @date 2024/5/11 3:59 PM
 */
public class LCR072MySqrt {

    public int mySqrt0(int x) {
        return (int) Math.sqrt(x);
    }

    public int mySqrt1(int x) {
        if (x <= 0) {
            return 0;
        }
        int num = (int) Math.exp(Math.log(x) / 2);
        return (long) (num + 1) * (num + 1) <= x ? num + 1 : num;
    }

    public int mySqrt2(int x) {
        int left = 0, right = x;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if ((long) mid * mid <= x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }

    public int mySqrt3(int x) {
        if (x == 0) {
            return 0;
        }

        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int) x0;
    }

    public int mySqrt4(int x) {
        double eps = 1e-6;
        double x0 = x;
        while (x0 * x0 - x > eps) {
            x0 = (x - x0 * x0) / (2 * x0) + x0;
        }
        return (int) x0;
    }

    public int mySqrt(int x) {
        long x0 = x;
        while (x0 * x0 > x) {
            x0 = (x0 * x0 + x) / (2 * x0);
        }
        return (int) x0;
    }
}
