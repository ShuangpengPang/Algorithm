package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2490CircularSentence（回环句）
 * @date 2023/7/1 10:34 AM
 */
public class Problem2490CircularSentence {

    public boolean isCircularSentence0(String sentence) {
        int n = sentence.length();
        if (sentence.charAt(0) != sentence.charAt(n - 1)) {
            return false;
        }
        char last = sentence.charAt(0), prev = last;
        for (int i = 1; i < n; i++) {
            char c = sentence.charAt(i);
            if (prev == ' ' && c != last) {
                return false;
            }
            if (c != ' ') {
                last = c;
            }
            prev = c;
        }
        return true;
    }

    public boolean isCircularSentence(String sentence) {
        int n = sentence.length();
        if (sentence.charAt(0) != sentence.charAt(n - 1)) {
            return false;
        }
        for (int i = 1; i < n - 1; i++) {
            if (sentence.charAt(i) == ' ' && sentence.charAt(i - 1) != sentence.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
