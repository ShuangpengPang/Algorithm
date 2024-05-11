package com.shuangpeng.lcr;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR072MySqrt（x的平方根）
 * @date 2024/5/11 3:59 PM
 */
public class LCR072MySqrt {

    public int mySqrt0(int x) {
        return (int) Math.sqrt(x);
    }

    public int mySqrt(int x) {
        int left = 0, right = x;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if ((long) mid * mid <= x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }
}
