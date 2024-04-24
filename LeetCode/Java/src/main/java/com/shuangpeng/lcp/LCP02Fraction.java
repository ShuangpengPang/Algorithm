package com.shuangpeng.lcp;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCP02Fraction（分式化简）
 * @date 2024/4/24 2:40 PM
 */
public class LCP02Fraction {

    public int[] fraction(int[] cont) {
        int n = cont.length;
        long[] arr = {cont[n - 1], 1};
        for (int i = n - 2; i >= 0; i--) {
            long a = cont[i] * arr[0] + arr[1], b = arr[0], g = gcd(a, b);
            arr[0] = a / g;
            arr[1] = b / g;
        }
        return new int[]{(int) arr[0], (int) arr[1]};
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}
