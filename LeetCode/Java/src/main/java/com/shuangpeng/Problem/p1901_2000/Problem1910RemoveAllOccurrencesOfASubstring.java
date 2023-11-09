package com.shuangpeng.Problem.p1901_2000;

import javafx.util.Pair;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1910RemoveAllOccurrencesOfASubstring（删除一个字符串中所有出现的给定子字符串）
 * @date 2023/11/9 2:49 PM
 */
public class Problem1910RemoveAllOccurrencesOfASubstring {

    public String removeOccurrences(String s, String part) {
        int n = s.length(), m = part.length();
        int[] next = new int[m];
        for (int i = 1; i < m; i++) {
            char c = part.charAt(i);
            int j = next[i - 1];
            while (j > 0 && part.charAt(j) != c) {
                j = next[j - 1];
            }
            next[i] = part.charAt(j) == c ? j + 1 : j;
        }
        int top = 0;
        Pair<Character, Integer>[] stack = new Pair[n];
        for (int i = 0, j = 0; i < n; i++) {
            char c = s.charAt(i);
            while (j > 0 && part.charAt(j) != c) {
                j = next[j - 1];
            }
            j = part.charAt(j) == c ? j + 1 : j;
            if (j == m) {
                top -= m - 1;
                j = top == 0 ? 0 : stack[top - 1].getValue();
            } else {
                stack[top++] = new Pair<>(c, j);
            }
        }
        char[] cs = new char[top];
        for (int i = 0; i < top; i++) {
            cs[i] = stack[i].getKey();
        }
        return new String(cs);
    }
}
