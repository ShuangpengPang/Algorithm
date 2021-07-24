package com.shuangpeng.Problem;

public class Problem0576OutOfBoundaryPaths {

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        if (maxMove == 0) {
            return 0;
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            dp[0][i]++;
            dp[m - 1][i]++;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0]++;
            dp[i][n - 1]++;
        }
        int answer = dp[startRow][startColumn];
        final int MOD = (int) 1e9 + 7;
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int move = 2; move <= maxMove; move++) {
            int[][] temp = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int count = 0;
                    for (int[] d : dirs) {
                        int x = i + d[0];
                        int y = j + d[1];
                        if (x >= 0 && x < m && y >= 0 && y < n) {
                            count += dp[x][y];
                            count -= (count >= MOD ? MOD : 0);
                        }
                    }
                    temp[i][j] = count;
                }
            }
            dp = temp;
            answer += dp[startRow][startColumn];
            answer -= (answer >= MOD ? MOD : 0);
        }
        return answer;
    }

//    public static void main(String[] args) {
//        Problem0576OutOfBoundaryPaths a = new Problem0576OutOfBoundaryPaths();
//        a.findPaths(2, 2, 2, 0, 0);
//    }
}
