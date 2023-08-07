package com.shuangpeng.Problem.p0301_0400;

/**
 * @description:（反转字符串）
 * @date 2023/8/7 10:39 AM
 **/
public class Problem0344ReverseString {

    public void reverseString0(char[] s) {
        int mid = s.length / 2;
        for (int i = 0; i < mid; i++) {
            char ch = s[i];
            s[i] = s[s.length - i - 1];
            s[s.length - i - 1] = ch;
        }
    }

    public void reverseString(char[] s) {
        for (int i = 0, j = s.length - 1; i < j; i++, j--) {
            char c = s[i];
            s[i] = s[j];
            s[j] = c;
        }
    }
}
