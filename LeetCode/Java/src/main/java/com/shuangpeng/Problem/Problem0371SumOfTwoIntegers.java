package com.shuangpeng.Problem;

public class Problem0371SumOfTwoIntegers {

    public int getSum0(int a, int b) {
        int c = 0;
        while (b != 0) {
            c = a ^ b;
            b = (a & b) << 1;
            a = c;
        }
        return a;
    }

    public int getSum(int a, int b) {
        int c = a;
        while (b != 0) {
            c = a;
            a ^= b;
            b = (b & c) << 1;
        }
        return a;
    }
}
