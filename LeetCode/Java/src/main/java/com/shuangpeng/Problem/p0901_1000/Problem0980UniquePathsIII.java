package com.shuangpeng.Problem.p0901_1000;

/**
 * @Description: Problem0980UniquePathsIII
 * @Date 2022/4/28 11:29 AM
 * @Version 1.0
 */
public class Problem0980UniquePathsIII {

    public int uniquePathsIII0(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int sx = 0, sy = 0, tx = 0, ty = 0, count = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int num = grid[i][j];
                if (num != -1) {
                    if (num == 1) {
                        sx = i;
                        sy = j;
                    } else if (num == 2) {
                        tx = i;
                        ty = j;
                    }
                    ++count;
                }
            }
        }
        int[] ans = new int[1];
        dfs(grid, sx, sy, tx, ty, count - 1, new boolean[m][n], ans);
        return ans[0];
    }

    private void dfs(int[][] grid, int x, int y, int tx, int ty, int remain, boolean[][] visited, int[] p) {
        if (remain == 0) {
            ++p[0];
            return;
        }
        if (x == tx && y == ty) {
            return;
        }
        visited[x][y] = true;
        int m = grid.length, n = grid[0].length;
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int[] dir : dirs) {
            int a = x + dir[0], b = y + dir[1];
            if (a >= 0 && a < m && b >= 0 && b < n && !visited[a][b] && grid[a][b] != -1) {
                dfs(grid, a, b, tx, ty, remain - 1, visited, p);
            }
        }
        visited[x][y] = false;
    }

    int ans;
    int[][] grid;
    int R, C;
    int tr, tc, target;
    int[] dr = new int[]{0, -1, 0, 1};
    int[] dc = new int[]{1, 0, -1, 0};
    Integer[][][] memo;

    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        target = 0;

        int sr = 0, sc = 0;
        for (int r = 0; r < R; ++r)
            for (int c = 0; c < C; ++c) {
                if (grid[r][c] % 2 == 0)
                    target |= code(r, c);

                if (grid[r][c] == 1) {
                    sr = r;
                    sc = c;
                } else if (grid[r][c] == 2) {
                    tr = r;
                    tc = c;
                }
            }

        memo = new Integer[R][C][1 << R*C];
        return dp(sr, sc, target);
    }

    public int code(int r, int c) {
        return 1 << (r * C + c);
    }

    public Integer dp(int r, int c, int todo) {
        if (memo[r][c][todo] != null)
            return memo[r][c][todo];

        if (r == tr && c == tc) {
            return todo == 0 ? 1 : 0;
        }

        int ans = 0;
        for (int k = 0; k < 4; ++k) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                if ((todo & code(nr, nc)) != 0)
                    ans += dp(nr, nc, todo ^ code(nr, nc));
            }
        }
        memo[r][c][todo] = ans;
        return ans;
    }
}
