package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2220MinimumBitFlipsToConvertNumber（转换数字的最少位翻转次数）
 * @date 2024/3/26 11:49 AM
 */
public class Problem2220MinimumBitFlipsToConvertNumber {

    public int minBitFlips(int start, int goal) {
        return bitCount(start ^ goal);
    }

    private int bitCount(int x) {
        x = x - (x >>> 1 & 0x55555555);
        x = (x & 0x33333333) + (x >>> 2 & 0x33333333);
        x = (x + (x >>> 4)) & 0xf0f0f0f;
        x = x + (x >>> 8);
        x = x + (x >>> 16);
        return x & 0x3f;
    }
}
