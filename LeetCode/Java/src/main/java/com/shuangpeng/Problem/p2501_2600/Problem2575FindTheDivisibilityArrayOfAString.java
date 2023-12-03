package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2575FindTheDivisibilityArrayOfAString（找出字符串的可整除数组）
 * @date 2023/12/3 9:44 PM
 */
public class Problem2575FindTheDivisibilityArrayOfAString {

    public int[] divisibilityArray(String word, int m) {
        int n = word.length();
        int[] div = new int[n];
        long num = 0;
        for (int i = 0; i < n; i++) {
            num = (num * 10 + word.charAt(i) - '0') % m;
            div[i] = num == 0 ? 1 : 0;
        }
        return div;
    }
}
