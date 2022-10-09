package com.shuangpeng.Problem.p0801_0900;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: Problem0856ScoreOfParentheses（括号的分数）
 * @Date 2022/10/9 10:56 AM
 * @Version 1.0
 */
public class Problem0856ScoreOfParentheses {

    public int scoreOfParentheses(String s) {
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
}
