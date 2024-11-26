package com.shuangpeng.Problem.p3301_3400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3307FindTheKthCharacterInStringGameII（找出第K个字符II）
 * @date 2024/11/26 5:01 PM
 */
public class Problem3307FindTheKthCharacterInStringGameII {

    public char kthCharacter(long k, int[] operations) {
        int c = 0;
        for (int i = Long.SIZE - Long.numberOfLeadingZeros(k - 1); i > 0; i--) {
            long num = 1L << i - 1;
            if (k > num) {
                k -= num;
                c += operations[i - 1];
            }
        }
        return (char) ('a' + (c % 26));
    }
}
