package com.shuangpeng.Problem.p0201_0300;

import java.util.*;

/**
 * @Description: 为运算表达式设计优先级
 * @Date 2022/7/1 10:47 AM
 **/
public class Problem0241DifferentWaysToAddParentheses {

    public List<Integer> diffWaysToCompute0(String expression) {
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

    public List<Integer> diffWaysToCompute(String expression) {
        int m = expression.length();
        int n = 1;
        for (int i = 0; i < m; i++) {
            char c = expression.charAt(i);
            if (c < '0' || c > '9') {
                n++;
            }
        }
        int[] nums = new int[n];
        char[] chars = new char[n - 1];
        int idx = 0;
        int num = 0;
        for (int i = 0; i < m; i++) {
            char c = expression.charAt(i);
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            } else {
                nums[idx] = num;
                chars[idx] = c;
                num = 0;
                idx++;
            }
        }
        nums[n - 1] = num;
        List<Integer>[][] dp = new List[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[i][j] = new ArrayList<>();
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i].add(nums[i]);
            for (int j = i + 1; j < n; j++) {
                for (int k = j; k > i; k--) {
                    for (int num1 : dp[i][k - 1]) {
                        for (int num2 : dp[k][j]) {
                            int res = 0;
                            if (chars[k - 1] == '+') {
                                res = num1 + num2;
                            } else if (chars[k - 1] == '-') {
                                res = num1 - num2;
                            } else {
                                res = num1 * num2;
                            }
                            dp[i][j].add(res);
                        }
                    }
                }
            }
        }
        return dp[0][n - 1];
    }
}
