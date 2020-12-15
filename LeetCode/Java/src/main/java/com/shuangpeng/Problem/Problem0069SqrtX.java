package com.shuangpeng.Problem;

public class Problem0069SqrtX {

//    public static void main(String[] args) {
//        int x = 2147395599;
//        Problem0069SqrtX a = new Problem0069SqrtX();
//        a.mySqrt(x);
//    }

    public int mySqrt(int x) {
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
}
