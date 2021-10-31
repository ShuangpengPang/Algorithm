package com.shuangpeng.Problem.p0201_0300;

import java.util.ArrayList;
import java.util.List;

public class Problem0282ExpressionAddOperators {

    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        backtrack(num, new StringBuilder(), 0, 0, 0, target, ans);
        return ans;
    }

    private void backtrack(String num, StringBuilder expression, int i, long result, long mul, int target, List<String> ans) {
        int n = num.length();
        if (i == n && result == target) {
            ans.add(expression.toString());
            return;
        }
        int signIndex = expression.length();
        if (i > 0) {
            expression.append('0');
        }
        long value = 0;
        for (int j = i; j < n && (j == i || num.charAt(i) != '0'); ++j) {
            char ch = num.charAt(j);
            value = value * 10 + ch - '0';
            expression.append(ch);
            if (i == 0) {
                backtrack(num, expression, j + 1, value, value, target, ans);
            } else {
                expression.setCharAt(signIndex, '+');
                backtrack(num, expression, j + 1, result + value, value, target, ans);
                expression.setCharAt(signIndex, '-');
                backtrack(num, expression, j + 1, result - value, -value, target, ans);
                expression.setCharAt(signIndex, '*');
                backtrack(num, expression, j + 1, result - mul + mul * value, mul * value, target, ans);
            }
        }
        expression.setLength(signIndex);
    }

//    public static void main(String[] args) {
//        Problem0282ExpressionAddOperators a = new Problem0282ExpressionAddOperators();
//        a.addOperators("2147483648", -2147483648);
//    }
}
