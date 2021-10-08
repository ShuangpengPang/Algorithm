package com.shuangpeng.Problem;

public class Problem1139Largest1BorderedSquare {

    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][][] leftUp = new int[m][n][2];
        leftUp[0][0][0] = grid[0][0] == 1 ? 1 : 0;
        leftUp[0][0][1] = leftUp[0][0][0];
        for (int i = 1; i < n; ++i) {
            if (grid[0][i] == 1) {
                leftUp[0][i][0] = leftUp[0][i - 1][0] + 1;
                leftUp[0][i][1] = 1;
            }
        }
        for (int i = 1; i < m; ++i) {
            if (grid[i][0] == 1) {
                leftUp[i][0][0] = 1;
                leftUp[i][0][1] = leftUp[i - 1][0][1] + 1;
            }
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (grid[i][j] == 1) {
                    leftUp[i][j][0] = leftUp[i][j - 1][0] + 1;
                    leftUp[i][j][1] = leftUp[i - 1][j][1] + 1;
                }
            }
        }
        int[][][] rightDown = new int[m][n][2];
        rightDown[m - 1][n - 1][0] = grid[m - 1][n - 1] == 1 ? 1 : 0;
        rightDown[m - 1][n - 1][1] = rightDown[m - 1][n - 1][0];
        for (int i = n - 2; i >= 0; --i) {
            if (grid[m - 1][i] == 1) {
                rightDown[m - 1][i][0] = rightDown[m - 1][i + 1][0] + 1;
                rightDown[m - 1][i][1] = 1;
            }
        }
        for (int i = m - 2; i >= 0; --i) {
            if (grid[i][n - 1] == 1) {
                rightDown[i][n - 1][0] = 1;
                rightDown[i][n - 1][1] = rightDown[i + 1][n - 1][1] + 1;
            }
        }
        for (int i = m - 2; i >= 0; --i) {
            for (int j = n - 2; j >= 0; --j) {
                if (grid[i][j] == 1) {
                    rightDown[i][j][0] = rightDown[i][j + 1][0] + 1;
                    rightDown[i][j][1] = rightDown[i + 1][j][1] + 1;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (rightDown[i][j][0] != 0) {
                    int edge = Math.min(rightDown[i][j][0], rightDown[i][j][1]);
                    for (int k = edge; k >= 1; --k) {
                        int x = i + k - 1;
                        int y = j + k - 1;
                        if (Math.min(leftUp[x][y][0], leftUp[x][y][1]) >= k) {
                            ans = Math.max(ans, k * k);
                            break;
                        }
                    }

                }
            }
        }
        return ans;
    }
}
