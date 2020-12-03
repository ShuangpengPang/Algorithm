package com.shuangpeng.Problem;

public class Problem0221MaximalSquare {

//    [
//            ["0","0","0","1"],
//            ["1","1","0","1"],
//            ["1","1","1","1"],
//            ["0","1","1","1"],
//            ["0","1","1","1"]
//            ]

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int maxSide = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i][j + 1], dp[i + 1][j]), dp[i][j]) + 1;
                    maxSide = Math.max(maxSide, dp[i + 1][j + 1]);
                }
            }
        }
        return maxSide * maxSide;
    }
}
