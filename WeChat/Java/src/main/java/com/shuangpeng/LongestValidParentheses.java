package com.shuangpeng;

import java.util.Stack;

public class LongestValidParentheses {

    // "(()(((()"
    public int longestValidParentheses0(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int left = 0;
        int right = 0;
        int max = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, left * 2);
            }
            if (right > left) {
                left = 0;
                right = 0;
            }
        }

        left = 0;
        right = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, left * 2);
            }
            if (left > right) {
                left = 0;
                right = 0;
            }
        }
        return max;
    }

    public int longestValidParentheses1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int left = 0;
        int max = 0;
        int previousLength = 0;
        int[] stack = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '(') {
                stack[left++] = i;
            } else if (left > 0) {
                left--;
                int leftIndex = stack[left];
                if (left > 0) {
                    leftIndex = stack[left - 1];
                } else {
                    leftIndex = leftIndex - previousLength - 1;
                    previousLength = i - leftIndex;
                }
                max = Math.max(max, i - leftIndex);
            } else {
                previousLength = 0;
            }
        }
        return max;
    }

    // ")()())"
    public int longestValidParentheses2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int max = 0;
        int[] dp = new int[chars.length + 1];
        for (int i = 2; i < chars.length + 1; i++) {
            char c = chars[i - 1];
            if (c == ')') {
                int index = i - dp[i - 1] - 1;
                if (index >= 1 && (chars[index - 1] == '(')) {
                    dp[i] = i - index + 1 + dp[index - 1];
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int longestValidParentheses3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();

        char[] sArr = s.toCharArray();
        Stack<Integer> stack = new Stack<>();

        int result = 0;

        // -1 入栈用于处理边界条件
        stack.push(-1);

        for (int i = 0; i < n; ++i) {
            // stack.size() > 1 表示栈不为空，而且我们必须保证栈顶元素是 '('
            if (sArr[i] == ')' && stack.size() > 1 && sArr[stack.peek()] == '(') {
                // 配对的 '(' 出栈
                stack.pop();
                // 记录长度
                result = Math.max(result, i - stack.peek());
            } else { // 其他情况，直接将当前位置入栈
                stack.push(i);
            }
        }

        return result;
    }

    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int max = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

        }
        return max;
    }
}
