package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2595NumberOfEvenAndOddBits（奇偶位数）
 * @date 2024/4/5 2:58 PM
 */
public class Problem2595NumberOfEvenAndOddBits {

    public int[] evenOddBit(int n) {
        return new int[]{bitCount(n & 0x55555555), bitCount(n & 0xaaaaaaaa)};
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
