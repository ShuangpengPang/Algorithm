package com.shuangpeng.Problem.p0301_0400;

public class Problem0367ValidPerfectSquare {

    public boolean isPerfectSquare0(int num) {
        return binarySearch(num);
    }

    private boolean binarySearch(long num) {
        long left = 1, right = num;
        while (left <= right) {
            long mid = left + ((right - left) >> 1);
            long data = mid * mid;
            if (data > num) {
                right = mid - 1;
            } else if (data < num) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    // 牛顿迭代法
    public boolean isPerfectSquare(int num) {
        double target = 1e-6;
        double x = num;
        while ((x - 0.5 * (x + (num / x))) > target) {
            x = 0.5 * (x + (num / x));
        }
        return (int) x * (int) x == num;
    }
}
