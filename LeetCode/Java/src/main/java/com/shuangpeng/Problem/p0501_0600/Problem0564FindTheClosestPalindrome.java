package com.shuangpeng.Problem.p0501_0600;

public class Problem0564FindTheClosestPalindrome {

    public String nearestPalindromic(String n) {
        int m = n.length();
        long num = Long.parseLong(n);
        if (m == 1) {
            return Long.toString(num - 1);
        }
        if (num == (long) Math.pow(10, m - 1) || num == (long) Math.pow(10, m - 1) + 1) {
            return Long.toString((long) Math.pow(10, m - 1) - 1);
        }
        if (num == (long) Math.pow(10, m) - 1) {
            return Long.toString((long) Math.pow(10, m) + 1);
        }
        long half = num / (long) Math.pow(10, m - (m + 1) / 2);
        long value1 = recover(half, m);
        long value2 = recover(half + 1, m);
        long value3 = recover(half - 1, m);
        long diff1 = Math.abs(num - value1);
        long diff2 = Math.abs(num - value2);
        long diff3 = Math.abs(num - value3);
        if (diff1 == 0) {
            return diff2 < diff3 ? Long.toString(value2) : Long.toString(value3);
        }
        long ans = diff1 < diff3 ? (diff2 < diff1 ? value2 : value1) : (diff2 < diff3 ? value2 : value3);
        return Long.toString(ans);
    }

    private long recover(long num, int n) {
        char[] ans = new char[n];
        String s = Long.toString(num);
        int m = s.length();
        for (int i = 0; i < m; ++i) {
            ans[i] = s.charAt(i);
        }
        for (int i = 0, j = n - 1; i < j; ++i, --j) {
            ans[j] = ans[i];
        }
        long value = 0;
        for (int i = 0; i < n; ++i) {
            value = value * 10 + ans[i] - '0';
        }
        return value;
    }
}
