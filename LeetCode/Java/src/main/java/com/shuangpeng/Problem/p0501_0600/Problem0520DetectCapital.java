package com.shuangpeng.Problem.p0501_0600;

public class Problem0520DetectCapital {

    public boolean detectCapitalUse(String word) {
        int n = word.length();
        if (n == 1) {
            return true;
        }
        boolean isCapital = check(word.charAt(1));
        for (int i = 2; i < n; ++i) {
            if (check(word.charAt(i)) != isCapital) {
                return false;
            }
        }
        return check(word.charAt(0)) || !isCapital;
    }

    private boolean check(char c) {
        return c >= 'A' && c <= 'Z';
    }
}
