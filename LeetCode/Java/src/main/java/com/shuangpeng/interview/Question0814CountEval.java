package com.shuangpeng.interview;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0814CountEval（面试题 08.14. 布尔运算）
 * @date 2024/10/9 7:28 PM
 */
public class Question0814CountEval {

    public int countEval0(String s, int result) {
        int n = s.length();
        int[] ans = dfs(s.toCharArray(), 0, n - 1, new int[n][n][]);
        return result == 0 ? ans[0] : ans[1];
    }

    private int[] dfs(char[] cs, int start, int end, int[][][] memo) {
        if (start > end) {
            return new int[2];
        }
        if (memo[start][end] != null) {
            return memo[start][end];
        }
        int[] ans = new int[2];
        if (start == end) {
            int val = cs[start] - '0';
            ans[0] = val ^ 1;
            ans[1] = val ^ 0;
            return memo[start][end] = ans;
        }
        for (int i = start; i < end; i += 2) {
            int[] left = dfs(cs, start, i, memo);
            int[] right = dfs(cs, i + 2, end, memo);
            switch (cs[i + 1]) {
                case '&':
                    ans[0] += left[0] * (right[0] + right[1]) + left[1] * right[0];
                    ans[1] += left[1] * right[1];
                    break;
                case '|':
                    ans[0] += left[0] * right[0];
                    ans[1] += left[1] * (right[0] + right[1]) + left[0] * right[1];
                    break;
                default:
                    ans[0] += left[0] * right[0] + left[1] * right[1];
                    ans[1] += left[0] * right[1] + left[1] * right[0];
                    break;
            }
        }
        return memo[start][end] = ans;
    }

    public int countEval(String s, int result) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        char[] cs = s.toCharArray();
        int[][][] dp = new int[n][n][];
        for (int i = n - 1; i >= 0; i--) {
            int val = cs[i] - '0';
            dp[i][i] = new int[2];
            dp[i][i][0] = 1 ^ val;
            dp[i][i][1] = 0 ^ val;
            for (int j = i + 2; j < n; j += 2) {
                int[] ans = new int[2];
                for (int k = i; k < j; k += 2) {
                    int[] left = dp[i][k], right = dp[k + 2][j];
                    switch (cs[k + 1]) {
                        case '&':
                            ans[0] += left[0] * (right[0] + right[1]) + left[1] * right[0];
                            ans[1] += left[1] * right[1];
                            break;
                        case '|':
                            ans[0] += left[0] * right[0];
                            ans[1] += left[1] * (right[0] + right[1]) + left[0] * right[1];
                            break;
                        default:
                            ans[0] += left[0] * right[0] + left[1] * right[1];
                            ans[1] += left[0] * right[1] + left[1] * right[0];
                            break;
                    }
                }
                dp[i][j] = ans;
            }
        }
        return dp[0][n - 1][result];
    }

//    public static void main(String[] args) {
//        int[][][] a = new int[2][2][];
//        Integer[][][] b = new Integer[2][2][2];
//        int[][][] c = a;
//        Integer[][][] d = b;
//        int i = 1;
//        int j = i;
//    }
}
