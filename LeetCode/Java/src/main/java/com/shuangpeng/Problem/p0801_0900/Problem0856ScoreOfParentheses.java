package com.shuangpeng.Problem.p0801_0900;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: Problem0856ScoreOfParentheses（括号的分数）
 * @Date 2022/10/9 10:56 AM
 * @Version 1.0
 */
public class Problem0856ScoreOfParentheses {

    public int scoreOfParentheses0(String s) {
        int n = s.length();
        Deque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push("(");
            } else if (stack.peek().equals("(")) {
                stack.pop();
                stack.push("1");
            } else {
                int sum = 0;
                while (!stack.peek().equals("(")) {
                    sum += Integer.parseInt(stack.pop());
                }
                stack.pop();
                stack.push(Integer.toString(sum << 1));
            }
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += Integer.parseInt(stack.pop());
        }
        return ans;
    }

    public int scoreOfParentheses1(String s) {
        int n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(0);
            } else {
                int v = stack.pop();
                stack.push(stack.pop() + Math.max(v << 1, 1));
            }
        }
        return stack.pop();
    }

    public int scoreOfParentheses2(String s) {
        int n = s.length();
        int ans = 0, cnt = 0;
        int i = 0;
        while (i < n) {
            while (i < n && s.charAt(i) == '(') {
                cnt++;
                i++;
            }
            ans += 1 << (cnt - 1);
            while (i < n && s.charAt(i) == ')') {
                cnt--;
                i++;
            }
        }
        return ans;
    }

    public int scoreOfParentheses(String s) {
        int n = s.length(), ans = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            cnt += c == '(' ? 1 : -1;
            if (c == ')' && s.charAt(i - 1) == '(') {
                ans += 1 << cnt;
            }
        }
        return ans;
    }
}

