package com.shuangpeng.Problem.p0301_0400;

public class Problem0344ReverseString {

    public void reverseString(char[] s) {
        int mid = s.length / 2;
        for (int i = 0; i < mid; i++) {
            char ch = s[i];
            s[i] = s[s.length - i - 1];
            s[s.length - i - 1] = ch;
        }
    }
}
