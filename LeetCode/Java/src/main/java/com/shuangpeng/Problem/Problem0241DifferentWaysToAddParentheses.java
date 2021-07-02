package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.List;

public class Problem0241DifferentWaysToAddParentheses {

    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> datas = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        int n = expression.length();
        int i = 0;
        while (i < n) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                int d = 0;
                while (i < n && Character.isDigit(expression.charAt(i))) {
                    d = d * 10 + expression.charAt(i) - '0';
                    i++;
                }
                datas.add(d);
            } else {
                operators.add(expression.charAt(i));
                i++;
            }
        }
        int size = datas.size();
        List<Integer>[][] dp = new List[size][size];
        for (int j = 0; j < size; j++) {
            dp[j][j] = new ArrayList<>(1);
            dp[j][j].add(datas.get(j));
        }
        for (int j = size - 2; j >= 0; j--) {
            for (int k = j + 1; k < size; k++) {
                List<Integer> list = new ArrayList<>();
                for (int m = j; m < k; m++) {
                    calculate(operators, j, m, k, dp, list);
                }
                dp[j][k] = list;
            }
        }
        return dp[0][size - 1];
    }

    private void calculate(List<Character> operators,
                           int s, int m, int e, List<Integer>[][] dp, List<Integer> list) {
        char c = operators.get(m);
        for (int i : dp[s][m]) {
            for (int j : dp[m + 1][e]) {
                if (c == '+') {
                    list.add(i + j);
                } else if (c == '-') {
                    list.add(i - j);
                } else {
                    list.add(i * j);
                }
            }
        }
    }

//    public static void main(String[] args) {
//        Problem0241DifferentWaysToAddParentheses a= new Problem0241DifferentWaysToAddParentheses();
//        a.diffWaysToCompute("2-1-1");
//    }
}
