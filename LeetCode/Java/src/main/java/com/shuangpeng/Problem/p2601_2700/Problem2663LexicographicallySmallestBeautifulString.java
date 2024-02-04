package com.shuangpeng.Problem.p2601_2700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2663LexicographicallySmallestBeautifulString（字典序最小的美丽字符串）
 * @date 2024/2/2 4:33 PM
 */
public class Problem2663LexicographicallySmallestBeautifulString {

    public String smallestBeautifulString(String s, int k) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        for (int i = n - 1; i >= 0; i--) {
            char c = getChar(cs, i, k);
            if (c != '\u0000') {
                cs[i] = c;
                return getString(cs, i, k);
            }
        }
        return "";
    }

    private char getChar(char[] cs, int i, int k) {
        for (char c = (char) (cs[i] + 1); c < 'a' + k; c++) {
            if ((i < 1 || c != cs[i - 1]) && (i < 2 || c != cs[i - 2])) {
                return c;
            }
        }
        return '\u0000';
    }

    private String getString(char[] cs, int i, int k) {
        for (int j = i + 1; j < cs.length; j++) {
            for (char c = 'a'; c < 'a' + k; c++) {
                if (c != cs[j - 1] && (j <= 1 || c != cs[j - 2])) {
                    cs[j] = c;
                    break;
                }
            }
        }
        return new String(cs);
    }
}
