package com.shuangpeng.Problem.p1201_1300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1201UglyNumberIII（丑数III）
 * @date 2023/6/12 5:55 PM
 */
public class Problem1201UglyNumberIII {

    public int nthUglyNumber(int n, int a, int b, int c) {
        long ab = lcm(a, b), ac = lcm(a, c), bc = lcm(b, c), abc = lcm(lcm(a, b), c);
        long left = 1, right = (long) 2e9;
        while (left <= right) {
            long mid = left + (right - left >> 1);
            long cnt = mid / a - mid / ab + mid / b - mid / ac + mid / c - mid / bc + mid / abc;
            if (cnt < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int) left;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }
}
