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

class Problem0592FractionAdditionAndSubtraction0 {

    public String fractionAddition(String expression) {
        long numerator = 0, denominator = 1, n = expression.length();
        int p = 0;
        while (p < n) {
            int sign = 1;
            if (expression.charAt(p) == '+') {
                p++;
            } else if (expression.charAt(p) == '-') {
                sign = -1;
                p++;
            }
            int numerator1 = 0;
            while (expression.charAt(p) != '/') {
                numerator1 = numerator1 * 10 + expression.charAt(p) - '0';
                p++;
            }
            numerator1 *= sign;
            p++;
            int denominator1 = 0;
            while (p < n && expression.charAt(p) >= '0' && expression.charAt(p) <= '9') {
                denominator1 = denominator1 * 10 + expression.charAt(p) - '0';
                p++;
            }
            numerator = numerator * denominator1 + denominator * numerator1;
            denominator = numerator == 0 ? 1 : denominator * denominator1;
        }
        if (numerator == 0) {
            return "0/1";
        }
        long g = gcd(Math.abs(numerator), denominator);
        return numerator / g + "/" + denominator / g;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long c = a % b;
            a = b;
            b = c;
        }
        return a;
    }
}

