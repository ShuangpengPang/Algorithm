package com.shuangpeng.Problem.p1801_1900;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: Problem1896MinimumCostToChangeTheFinalValueOfExpression（反转表达式值的最少操作次数）
 * @Date 2022/10/27 11:59 AM
 * @Version 1.0
 */
public class Problem1896MinimumCostToChangeTheFinalValueOfExpression {

    public int minOperationsToFlip(String expression) {
        int n = expression.length();
        Deque<int[]> stack = new ArrayDeque<>();
        Deque<Character> sign = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);
            if (c == '0' || c == '1') {
                int num = c - '0';
                int[] arr = {num ^ 0, num ^ 1};
                if (!sign.isEmpty() && sign.peek() != '(') {
                    calculate(stack, sign, arr);
                } else {
                    stack.push(arr);
                }
            } else if (c == ')') {
                sign.pop();
                if (!sign.isEmpty() && sign.peek() != '(') {
                    calculate(stack, sign, stack.pop());
                }
            } else {
                sign.push(c);
            }
        }
        int[] arr = stack.pop();
        return Math.max(arr[0], arr[1]);
    }

    private void calculate(Deque<int[]> stack, Deque<Character> sign, int[] arr) {
        char s = sign.pop();
        int[] top = stack.pop();
        int zero = Integer.MAX_VALUE, one = Integer.MAX_VALUE;
        if (s == '&') {
            zero = Math.min(arr[0], top[0]);
            one = Math.min(arr[1] + top[1], Math.min(arr[1], top[1]) + 1);
        } else {
            zero = Math.min(arr[0] + top[0], Math.min(arr[0], top[0]) + 1);
            one = Math.min(arr[1], top[1]);
        }
        stack.push(new int[]{zero, one});
    }

//    public static void main(String[] args) {
//        Problem1896MinimumCostToChangeTheFinalValueOfExpression a = new Problem1896MinimumCostToChangeTheFinalValueOfExpression();
//        a.minOperationsToFlip("(((0)&1&((0&0))))");
//    }
}
