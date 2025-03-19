package com.shuangpeng.Problem.p3401_3500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3461CheckIfDigitsAreEqualInStringAfterOperationsI（判断操作后字符串中的数字是否相等I）
 * @date 2025/3/14 17:36
 */
public class Problem3461CheckIfDigitsAreEqualInStringAfterOperationsI {

    private static final int M = 100;
    private static final int[] f = new int[M];
    private static final int[] invf = new int[M];
    private static final int[] cnt2 = new int[M];
    private static final int[] cnt5 = new int[M];
    private static final int[] pow2 = {2, 4, 8, 6};

    static {
        f[0] = 1;
        for (int i = 1; i < M; i++) {
            int x = i;
            int c2 = Integer.numberOfTrailingZeros(x);
            x >>= c2;
            int c5 = 0;
            while ((x % 5) == 0) {
                c5++;
                x /= 5;
            }
            f[i] = f[i - 1] * x % 10;
            cnt2[i] = cnt2[i - 1] + c2;
            cnt5[i] = cnt5[i - 1] + c5;
        }
        invf[M - 1] = pow(f[M - 1], 3);
        for (int i = M - 1; i > 0; i--) {
            int x = i;
            x >>= Integer.numberOfTrailingZeros(x);
            while ((x % 5) == 0) {
                x /= 5;
            }
            invf[i - 1] = invf[i] * x % 10;
        }
    }

    private static int pow(int x, int e) {
        int ans = 1;
        while (e != 0) {
            if ((e & 1) == 1) {
                ans = ans * x % 10;
            }
            e >>= 1;
            x = x * x % 10;
        }
        return ans;
    }


    public boolean hasSameDigits(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            int c2 = cnt2[n - 2] - cnt2[i] - cnt2[n - i - 2];
            int c5 = cnt5[n - 2] - cnt5[i] - cnt5[n - i - 2];
            int m = f[n - 2] * invf[i] * invf[n - i - 2] * (c2 > 0 ? pow2[(c2 - 1) % 4] : 1) * (c5 > 0 ? 5 : 1) * (cs[i] - cs[i + 1]) % 10;
            sum = (sum + m + 10) % 10;
        }
        return sum == 0;
    }
}
