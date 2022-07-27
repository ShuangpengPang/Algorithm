package com.shuangpeng.Problem.p0501_0600;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: Problem0592FractionAdditionAndSubtraction（分数加减运算）
 * @Date 2022/7/27 10:11 AM
 * @Version 1.0
 */
public class Problem0592FractionAdditionAndSubtraction {

    public String fractionAddition(String expression) {
        Deque<int[]> q = parseExpression(expression);
        while (q.size() > 1) {
            q.addFirst(calculate(q.pollFirst(), q.pollFirst()));
        }
        int[] arr = q.pollFirst();
        return "" + (arr[0] < 0 ? "-" : "") + Math.abs(arr[0]) + "/" + arr[1];
    }

    private Deque<int[]> parseExpression(String s) {
        Deque<int[]> q = new ArrayDeque<>();
        int n = s.length();
        int p = 0;
        while (p < n) {
            int sign = 1;
            if (s.charAt(p) == '+') {
                sign = 1;
                p++;
            } else if (s.charAt(p) == '-') {
                sign = -1;
                p++;
            }
            int numerator = 0;
            while (s.charAt(p) != '/') {
                numerator = numerator * 10 + s.charAt(p) - '0';
                p++;
            }
            p++;
            int denominator = 0;
            while (p < n && s.charAt(p) >= '0' && s.charAt(p) <= '9') {
                denominator = denominator * 10 + s.charAt(p) - '0';
                p++;
            }
            q.addLast(new int[]{sign * numerator, denominator});
        }
        return q;
    }

    private int[] calculate(int[] a, int[] b) {
        int num1 = a[0] * b[1] + b[0] * a[1];
        int num2 = a[1] * b[1];
        if (num1 == 0) {
            return new int[]{0, 1};
        }
        int g = gcd(Math.abs(num1), num2);
        return new int[]{num1 / g, num2 / g};
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
