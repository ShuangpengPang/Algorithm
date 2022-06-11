package com.shuangpeng.Problem.p0901_1000;

public class Problem0926FlipStringToMonotoneIncreasing {
    public int minFlipsMonoIncr0(String s) {
        int n = s.length();
        int[] right = new int[n];
        right[n - 1] = s.charAt(n - 1) == '0' ? 1 : 0;
        for (int i = n - 2; i >= 0; --i) {
            right[i] = right[i + 1] + (s.charAt(i) == '0' ? 1 : 0);
        }
        int ans = n;
        int ones = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.min(ans, ones + right[i]);
            if (s.charAt(i) == '1') {
                ones++;
            }
        }
        ans = Math.min(ans, ones);
        return ans;
    }

    public int minFlipsMonoIncr1(String s) {
        int n = s.length();
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            preSum[i + 1] = preSum[i] + (s.charAt(i) == '1' ? 1 : 0);
        }
        int ans = n;
        for (int i = 0; i <= n; ++i) {
            ans = Math.min(ans, (preSum[i] << 1) + n - i - preSum[n]);
        }
        return ans;
    }

    public int minFlipsMonoIncr2(String s) {
        int n = s.length();
        int[] ones = new int[n + 1];
        int[] zeros = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            ones[i] = ones[i - 1] + (s.charAt(i - 1) == '1' ? 1 : 0);
            int j = n - i;
            zeros[j] = zeros[j + 1] + (s.charAt(j) == '0' ? 1 : 0);
        }
        int ans = n;
        for (int i = 0; i <= n; ++i) {
            ans = Math.min(ans, ones[i] + zeros[i]);
        }
        return ans;
    }

    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        int one = s.charAt(0) == '1' ? 0 : 1;
        int zero = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 1; i < n; ++i) {
            one = Math.min(one, zero) + (s.charAt(i) == '1' ? 0 : 1);
            zero = zero + (s.charAt(i) == '0' ? 0 : 1);
        }
        return Math.min(one, zero);
    }
}
