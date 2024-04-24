package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3120CountTheNumberOfSpecialCharactersI（统计特殊字母的数量I）
 * @date 2024/4/24 10:53 AM
 */
public class Problem3120CountTheNumberOfSpecialCharactersI {

    public int numberOfSpecialChars(String word) {
        long mask = 0;
        for (char c : word.toCharArray()) {
            mask |= 1L << c - 'A';
        }
        return Long.bitCount(mask & ((1L << 32) - 1) & (mask >> 32));
    }
}
