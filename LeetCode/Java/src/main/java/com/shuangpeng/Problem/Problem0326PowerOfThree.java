package com.shuangpeng.Problem;

public class Problem0326PowerOfThree {

    public boolean isPowerOfThree0(int n) {
        if (n <= 0) {
            return false;
        }
        while (n > 1) {
            if ((n % 3) != 0) {
                return false;
            }
            n /= 3;
        }
        return true;
    }

    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n > 1 && n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }
}
