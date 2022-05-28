package com.shuangpeng.Problem.p1001_1100;

/**
 * @Description: Problem1021RemoveOutermostParentheses（删除最外层的括号）
 * @Date 2022/5/28 9:56 AM
 * @Version 1.0
 */
public class Problem1021RemoveOutermostParentheses {

    public String removeOuterParentheses0(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            boolean valid = true;
            if (c == '(') {
                valid = count != 0;
                ++count;
            } else {
                --count;
                valid = count != 0;
            }
            if (valid) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public String removeOuterParentheses1(String s) {
        int n = s.length();
        int level = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == ')') {
                --level;
            }
            if (level > 0) {
                sb.append(c);
            }
            if (c == '(') {
                ++level;
            }
        }
        return sb.toString();
    }

    public String removeOuterParentheses(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int level = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            char c = chars[i];
            if (c == ')') {
                --level;
            }
            if (level > 0) {
                sb.append(c);
            }
            if (c == '(') {
                ++level;
            }
        }
        return sb.toString();
    }
}
