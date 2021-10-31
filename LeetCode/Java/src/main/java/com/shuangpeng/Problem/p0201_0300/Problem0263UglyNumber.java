package com.shuangpeng.Problem.p0201_0300;

public class Problem0263UglyNumber {

    public boolean isUgly(int n) {
        if (n < 1) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        while (n != 2 && n != 3 && n != 5) {
            if (n % 2 == 0) {
                n /= 2;
            } else if (n % 3 == 0) {
                n /= 3;
            } else if (n % 5 == 0) {
                n /= 5;
            } else {
                return false;
            }
        }
        return true;
    }
}
