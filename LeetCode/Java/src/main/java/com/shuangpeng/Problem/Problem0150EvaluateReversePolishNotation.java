package com.shuangpeng.Problem;

import java.util.Deque;
import java.util.LinkedList;

public class Problem0150EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        int length = tokens.length;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            String token = tokens[i];
            if (token.equals("+") || token.equals("-")
                    || token.equals("*") || token.equals("/")) {
                int second = stack.pop();
                int first = stack.pop();
                stack.push(getResult(first, second, token));
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    public int getResult(int first, int second, String token) {
        if (token.equals("+")) {
            return first + second;
        } else if (token.equals("-")) {
            return first - second;
        } else if (token.equals("*")) {
            return first * second;
        } else {
            return first / second;
        }
    }
}
