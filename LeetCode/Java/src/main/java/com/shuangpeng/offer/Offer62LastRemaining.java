package com.shuangpeng.offer;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Offer62LastRemaining（圆圈中最后剩下的数字）
 * @date 2023/5/15 5:04 PM
 */
public class Offer62LastRemaining {

    public int lastRemaining(int n, int m) {
        if (n == 1) {
            return 0;
        }
        return (lastRemaining(n - 1, m) + m) % n;
    }
}
