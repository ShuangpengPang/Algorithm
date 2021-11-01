package com.shuangpeng.Problem.p0401_0500;

public class Problem0441ArrangingCoins {

    public int arrangeCoins0(int n) {
        long k = (long) Math.sqrt(n * 2L);
        return (int) (k * (k + 1) <= n * 2L ? k : k - 1);
    }

    public int arrangeCoins1(int n) {
        int left = 1, right = n;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            long k = (long) mid * (mid + 1) >> 1;
            if (k < n) {
                left = mid + 1;
            } else if (k > n) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return left - 1;
    }

    public int arrangeCoins(int n) {
        return (int) (Math.sqrt(8L * n + 1L) - 1) >> 1;
    }
}
