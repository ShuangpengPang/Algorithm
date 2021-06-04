package com.shuangpeng.Problem;

import java.util.Deque;
import java.util.LinkedList;

public class Problem0032LongestValidParentheses {

    public int longestValidParentheses0(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        Deque<Integer> stack = new LinkedList<>();
        int max = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (stack.isEmpty() || s.charAt(stack.peekLast()) == ')' || c == '(') {
                stack.addLast(i);
            } else {
                stack.pollLast();
                int start = stack.isEmpty() ? -1 : stack.peekLast();
                max = Math.max(max, i - start);
            }
        }
        return max;
    }

    public int longestValidParentheses1(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i + 1] = dp[i - 1] + 2;
                } else if (i - 1 - dp[i] >= 0 && s.charAt(i - 1 - dp[i]) == '(') {
                    dp[i + 1] = dp[i] + 2 + dp[i - 1 - dp[i]];
                }
                max = Math.max(max, dp[i + 1]);
            }
        }
        return max;
    }

    public int longestValidParentheses2(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    public int longestValidParentheses3(String s) {
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    public int longestValidParentheses(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int left = 0, right = 0;
        int max = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (right > left) {
                left = 0;
                right = 0;
            } else if (left == right) {
                max = Math.max(max, 2 * left);
            }
        }
        left = 0;
        right = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left > right) {
                left = 0;
                right = 0;
            } else if (left == right) {
                max = Math.max(max, 2 * left);
            }
        }
        return max;
    }
}
