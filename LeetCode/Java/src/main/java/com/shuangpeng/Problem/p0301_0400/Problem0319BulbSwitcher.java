package com.shuangpeng.Problem.p0301_0400;

public class Problem0319BulbSwitcher {

    public int bulbSwitch0(int n) {
        int left = 0, right = n;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            long data = (long) mid * mid;
            if (data > n) {
                right = mid - 1;
            } else if (data < n) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left - 1;
    }

    public int bulbSwitch(int n) {
        double target = 1e-6;
        double x = n;
        double x1 = 0.5 * (x + n / x);
        while (x - x1 > target) {
            x = x1;
            x1 = 0.5 * (x + n / x);
        }
        return (int) x;
    }
}
