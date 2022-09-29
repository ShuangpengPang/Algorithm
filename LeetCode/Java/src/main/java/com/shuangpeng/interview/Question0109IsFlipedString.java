package com.shuangpeng.interview;

/**
 * @Description: Question0109IsFlipedString（字符串轮转）
 * @Date 2022/9/29 9:55 AM
 * @Version 1.0
 */
public class Question0109IsFlipedString {

    public boolean isFlipedString0(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        int n = s1.length();
        int[] next = new int[n];
        for (int i = 1; i < n; i++) {
            char c = s1.charAt(i);
            int j = i - 1;
            while (j >= 0 && s1.charAt(next[j]) != c) {
                j = next[j] - 1;
            }
            if (j >= 0 && s1.charAt(next[j]) == c) {
                next[i] = next[j] + 1;
            }
        }
        for (int i = 0, j = 0; i < n && j < (n << 1) && n - i <= (n << 1) - j; j++) {
            char c = s2.charAt(j % n);
            while (i > 0 && s1.charAt(i) != c) {
                i = next[i - 1];
            }
            if (s1.charAt(i) == c) {
                i++;
                if (i == n) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        int n = s1.length();
        int[] next = new int[n];
        for (int i = 1; i < n; i++) {
            char c = s1.charAt(i);
            int j = next[i - 1];
            while (j > 0 && s1.charAt(j) != c) {
                j = next[j - 1];
            }
            if (s1.charAt(j) == c) {
                next[i] = j + 1;
            }
        }
        for (int i = 0, j = 0; i < n && j < (n << 1) && n - i <= (n << 1) - j; j++) {
            char c = s2.charAt(j % n);
            while (i > 0 && s1.charAt(i) != c) {
                i = next[i - 1];
            }
            if (s1.charAt(i) == c) {
                i++;
                if (i == n) {
                    return true;
                }
            }
        }
        return false;
    }
}
