package com.shuangpeng.Problem.p1101_1200;

/**
 * @description: 最大的以1为边界的正方形
 * @date 2023/5/12 12:08 AM
 **/
public class Problem1139Largest1BorderedSquare {

    public int largest1BorderedSquare0(int[][] grid) {
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

    public int largest1BorderedSquare1(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] row = new int[m + 1][n + 1], col = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (grid[i - 1][j - 1] == 1) {
                    row[i][j] = row[i][j - 1] + 1;
                }
            }
        }
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= m; i++) {
                if (grid[i - 1][j - 1] == 1) {
                    col[i][j] = col[i - 1][j] + 1;
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int x = i + ans, y = j + ans; x <= m && y <= n; x++, y++) {
                    if (row[i][y] - row[i][j - 1] != y - j + 1) {
                        break;
                    }
                    if (col[x][j] - col[i - 1][j] != x - i + 1) {
                        break;
                    }
                    if (col[x][y] - col[i - 1][y] != x - i + 1) {
                        continue;
                    }
                    if (row[x][y] - row[x][j - 1] != y - j + 1) {
                        continue;
                    }
                    ans = x - i + 1;
                }
            }
        }
        return ans * ans;
    }

    public int largest1BorderedSquare2(int[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        int[][] row = new int[m + 1][n + 1], col = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (grid[i - 1][j - 1] == 0) {
                    continue;
                }
                row[i][j] = row[i][j - 1] + 1;
                col[i][j] = col[i - 1][j] + 1;
                for (int k = ans; row[i][j] > k && col[i][j] > k; k++) {
                    if (col[i][j - k] > k && row[i - k][j] > k) {
                        ans = k + 1;
                    }
                }
            }
        }
        return ans * ans;
    }

    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        int[][] row = new int[m + 1][n + 1];
        int[] col = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (grid[i - 1][j - 1] == 0) {
                    row[i][j] = 0;
                    col[j] = 0;
                } else {
                    row[i][j] = row[i][j - 1] + 1;
                    col[j]++;
                }
                int length = Math.min(row[i][j], col[j]);
                for (int k = ans + 1; k <= length; k++) {
                    if (col[j - k + 1] >= k && row[i - k + 1][j] >= k) {
                        ans = k;
                    }
                }
            }
        }
        return ans * ans;
    }
}
