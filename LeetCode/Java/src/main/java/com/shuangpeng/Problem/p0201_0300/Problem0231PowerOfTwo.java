package com.shuangpeng.Problem.p0201_0300;

public class Problem0231PowerOfTwo {

    public boolean isPowerOfTwo(int n) {
        if (n < 0) {
            return false;
        }
        if (n == 0) {
            return false;
        }
        return (n & n - 1) == 0;
    }
}
