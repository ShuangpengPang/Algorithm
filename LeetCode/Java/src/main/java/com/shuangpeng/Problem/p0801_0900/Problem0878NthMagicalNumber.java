package com.shuangpeng.Problem.p0801_0900;

/**
 * @description: 第N个神奇数字
 * @date 2022/11/22 10:24 AM
 **/
public class Problem0878NthMagicalNumber {

    public int nthMagicalNumber(int n, int a, int b) {
        final int M = (int) 1e9 + 7;
        int g = gcd(a, b);
        int c = a * b / g;
        int count = c / a + c / b - 1;
        int k = n / count;
        int m = n % count;
        return (int) (((long) c * k + getNumber(m, a, b)) % M);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private int getNumber(int m, int a, int b) {
        int ans = 0;
        int a1 = 1, b1 = 1;
        for (int i = 0; i < m; ++i) {
            int x = a * a1, y = b * b1;
            if (x < y) {
                ans = x;
                ++a1;
            } else {
                ans = y;
                ++b1;
            }
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem0878NthMagicalNumber a = new Problem0878NthMagicalNumber();
//
//    }
}

class Problem0878NthMagicalNumber0 {

    public int nthMagicalNumber(int n, int a, int b) {
        int g = gcd(a, b), c = a * b / g, M = (int) 1e9 + 7;
        long left = 0, right = (long) n * Math.max(a, b);
        while (left <= right) {
            long mid = left + (right - left >> 1);
            long cnt = mid / a + mid / b - mid / c;
            if (cnt < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int) (left % M);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}