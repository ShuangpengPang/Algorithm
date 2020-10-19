package com.shuangpeng;

public class Problem0020ValidParentheses {

    public boolean isValid(String s) {
        int length = s.length();
        if (length % 2 == 1) {
            return false;
        }
        char[] stack = new char[length + 1];
        int count = 0;
        stack[0] = '*';
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            char last = stack[count];
            if (c == '(' || c == '{' || c == '[') {
                stack[++count] = c;
            } else if ((last == '(' && c == ')') || (last == '{' && c == '}') || (last == '[' && c == ']')) {
                count--;
            } else {
                return false;
            }
        }
        return count == 0;
    }
}
