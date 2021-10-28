package com.shuangpeng.Problem.p0001_0100;

public class Problem0069SqrtX {

//    public static void main(String[] args) {
//        int x = 2147395599;
//        Problem0069SqrtX a = new Problem0069SqrtX();
//        a.mySqrt(x);
//    }

    public int mySqrt0(int x) {
        if (x <= 1) {
            return x;
        }
        long left = 1;
        long right = x;
        while (left <= right) {
            long mid = (left + right) >> 1;
            if (mid * mid > x) {
                right = mid - 1;
            } else if (mid * mid < x) {
                left = mid + 1;
            } else {
                return (int) mid;
            }
        }
        return (int) (left - 1);
    }

    public int mySqrt1(int x) {
        if (x <= 1) {
            return x;
        }
        long answer = (long) Math.exp(0.5 * Math.log(x));
        return (int) ((answer + 1) * (answer + 1) <= x ? answer + 1 : answer);
    }

    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        double e = 0.5;
        double a = x;
        while (Math.abs(a * a - x) > e) {
            a = 0.5 * (a + x / a);
        }
        return (int) a;
    }
}
