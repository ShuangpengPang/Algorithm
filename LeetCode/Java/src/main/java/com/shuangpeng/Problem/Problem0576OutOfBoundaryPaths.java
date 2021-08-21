package com.shuangpeng.Problem;

public class Problem0576OutOfBoundaryPaths {

    public int findPaths0(int m, int n, int maxMove, int startRow, int startColumn) {
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

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        final int M = (int) 1e9 + 7;
        int[][][] dp = new int[maxMove + 1][m + 2][n + 2];
        for (int s = 0; s <= maxMove; ++s) {
            for (int i = 0; i <= n + 1; ++i) {
                dp[s][0][i] = 1;
                dp[s][m + 1][i] = 1;
            }
            for (int i = 0; i <= m + 1; ++i) {
                dp[s][i][0] = 1;
                dp[s][i][n + 1] = 1;
            }
        }
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int s = 1; s <= maxMove; ++s) {
            for (int i = 1; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    for (int[] dir : dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        dp[s][i][j] += dp[s - 1][x][y];
                        if (dp[s][i][j] >= M) {
                            dp[s][i][j] -= M;
                        }
                    }
                }
            }
        }
        return dp[maxMove][startRow + 1][startColumn + 1];
    }

//    public static void main(String[] args) {
//        Problem0576OutOfBoundaryPaths a = new Problem0576OutOfBoundaryPaths();
//        a.findPaths(2, 2, 2, 0, 0);
//    }
}
