package com.shuangpeng.lcp;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCP17Calculate（速算机器人）
 * @date 2024/4/25 12:13 PM
 */
public class LCP17Calculate {

    public int calculate(String s) {
        int x = 1, y = 0;
        for (char c : s.toCharArray()) {
            if (c == 'A') {
                x = (x << 1) + y;
            } else {
                y = (y << 1) + x;
            }
        }
        return x + y;
    }
}
