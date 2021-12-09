package com.shuangpeng.Problem.p0301_0400;

public class Problem0400NthDigit {

    public int findNthDigit(int n) {
        long count = 9;
        int bits = 1;
        while (n > count * bits) {
            n -= count * bits;
            count *= 10;
            ++bits;
        }
        int num = (int) Math.pow(10, bits - 1) + n / bits - 1;
        int m = n % bits;
        if (m == 0) {
            return num % 10;
        }
        ++num;
        return num / (int) Math.pow(10, bits - m) % 10;
    }

//    public static void main(String[] args) {
//        Problem0400NthDigit a = new Problem0400NthDigit();
//        a.findNthDigit(100000);
//    }
}
