package com.shuangpeng.Problem.p0801_0900;

public class Problem0829ConsecutiveNumbersSum {

    public int consecutiveNumbersSum0(int n) {
        n = n << 1;
        int ans = 1;
        for (int k = 2; k * k <= n; ++k) {
            if (n % k == 0) {
                if (check(n, k)) {
                    ++ans;
                }
                int t = n / k;
                if (t != k && check(n, t)) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private boolean check(int n, int k) {
        int t = n / k + 1 - k;
        return t > 0 && (t & 1) == 0;
    }

    // (s + s + k - 1) * k = 2 * n
    // 2 * s * k = 2 * n + k - k * k
    // s = (2 * n + k * (1 - k)) / (2 * k)
    // s = (n + (1 - k) * k / 2) / k
    public int consecutiveNumbersSum1(int n) {
        int ans = 0;
        for (int k = 1; n + ((1 - k) * k >> 1) > 0; ++k) {
            if ((n + ((1 - k) * k >> 1)) % k == 0) {
                ++ans;
            }
        }
        return ans;
    }

    // (2 * m + k) * (k + 1) = 2 * n
    public int consecutiveNumbersSum2(int n) {
        n <<= 1;
        int m = (int) Math.sqrt(n);
        int ans = 1;
        for (int i = 2; i <= m; ++i) {
            if (n % i == 0 && (n / i - i + 1) % 2 == 0) {
                ++ans;
            }
        }
        return ans;
    }

    public int consecutiveNumbersSum(int N) {
        while ((N & 1) == 0) N >>= 1;
        int ans = 1, d = 3;
        while (d * d <= N) {
            int e = 0;
            while (N % d == 0) {
                N /= d;
                e++;
            }
            ans *= e + 1;
            d += 2;
        }
        if (N > 1) ans <<= 1;
        return ans;
    }
}



