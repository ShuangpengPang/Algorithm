package com.shuangpeng.Problem.p0401_0500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0459RepeatedSubstringPattern（重复的子字符串）
 * @date 2022/12/9 7:57 PM
 */
public class Problem0459RepeatedSubstringPattern {

    public boolean repeatedSubstringPattern0(String s) {
        int n = s.length(), h = n >> 1;
        for (int i = 1; i <= h; i++) {
            if (n % i != 0) {
                continue;
            }
            int c = n / i;
            boolean valid = true;
            for (int j = 1; j < c && valid; j++) {
                for (int k = i * (j - 1); k < i * j; k++) {
                    if (s.charAt(k) != s.charAt(k + i)) {
                        valid = false;
                        break;
                    }
                }
            }
            if (valid) {
                return true;
            }
        }
        return false;
    }

    public boolean repeatedSubstringPattern1(String s) {
        int n = s.length(), h = n >> 1;
        for (int i = 1; i <= h; i++) {
            if (n % i == 0) {
                boolean match = true;
                for (int j = i; j < n; j++) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean repeatedSubstringPattern2(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }

    // KMP模版
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        int[] fail = new int[n];
        for (int i = 1; i < n; i++) {
            int j = fail[i - 1];
            while (j > 0 && s.charAt(j) != s.charAt(i)) {
                j = fail[j - 1];
            }
            fail[i] = s.charAt(j) == s.charAt(i) ? j + 1 : j;
        }
        for (int i = 1, j = 0; i < (n << 1) - 1; i++) {
            char c = s.charAt(i % n);
            while (j > 0 && s.charAt(j) != c) {
                j = fail[j - 1];
            }
            if (s.charAt(j) == c) {
                j++;
                if (j == n) {
                    return true;
                }
            }
        }
        return false;
    }
}
