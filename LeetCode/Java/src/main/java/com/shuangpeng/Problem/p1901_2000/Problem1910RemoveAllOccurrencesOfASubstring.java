package com.shuangpeng.Problem.p1901_2000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1910RemoveAllOccurrencesOfASubstring（删除一个字符串中所有出现的给定子字符串）
 * @date 2023/11/9 2:49 PM
 */
public class Problem1910RemoveAllOccurrencesOfASubstring {

    public String removeOccurrences0(String s, String part) {
        StringBuilder sb  = new StringBuilder();
        char[] cs1 = s.toCharArray(), cs2 = part.toCharArray();
        int n = cs2.length;
        for (char c : cs1) {
            sb.append(c);
            if (sb.length() >= cs2.length && check(sb, cs2)) {
                sb.setLength(sb.length() - n);
            }
        }
        return sb.toString();
    }

    private boolean check(StringBuilder sb, char[] cs) {
        int n = cs.length;
        for (int i = sb.length() - n, j = 0; j < n; i++, j++) {
            if (sb.charAt(i) != cs[j]) {
                return false;
            }
        }
        return true;
    }

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
        int[][] stack = new int[n + 1][2];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int j = stack[top][1];
            while (j > 0 && part.charAt(j) != c) {
                j = next[j - 1];
            }
            j = part.charAt(j) == c ? j + 1 : j;
            if (j == m) {
                top -= m - 1;
            } else {
                top++;
                stack[top][0] = c;
                stack[top][1] = j;
            }
        }
        char[] cs = new char[top];
        for (int i = 0; i < top; i++) {
            cs[i] = (char) stack[i + 1][0];
        }
        return new String(cs);
    }
}
