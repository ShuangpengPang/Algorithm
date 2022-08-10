package com.shuangpeng.Problem.p0601_0700;

/**
 * @Description: Problem0640SolveTheEquation（求解方程）
 * @Date 2022/8/10 10:04 AM
 * @Version 1.0
 */
public class Problem0640SolveTheEquation {

    public String solveEquation(String equation) {
        String[] strs = equation.split("=");
        int[] left = parseEquation(strs[0]), right = parseEquation(strs[1]);
        int coefficient = left[1] - right[1], value = right[0] - left[0];
        if (coefficient == 0) {
            return value == 0 ? "Infinite solutions" : "No solution";
        }
        return "x=" + value / coefficient;
    }

    private int[] parseEquation(String s) {
        int value = 0, coefficient = 0, n = s.length();
        int p = 0;
        while (p < n) {
            int sign = 1;
            if (s.charAt(p) == '-') {
                sign = -1;
                p++;
            } else if (s.charAt(p) == '+') {
                p++;
            }
            if (s.charAt(p) == 'x') {
                coefficient += sign == 1 ? 1 : -1;
                p++;
                continue;
            }
            int num = 0;
            while (p < n && Character.isDigit(s.charAt(p))) {
                num = num * 10 + s.charAt(p) - '0';
                p++;
            }
            if (p < n && s.charAt(p) == 'x') {
                coefficient += num * sign;
                p++;
            } else {
                value += num * sign;
            }
        }
        return new int[]{value, coefficient};
    }
}
