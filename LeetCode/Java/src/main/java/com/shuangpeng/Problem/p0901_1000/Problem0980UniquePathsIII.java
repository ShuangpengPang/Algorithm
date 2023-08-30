package com.shuangpeng.Problem.p0901_1000;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Problem0980UniquePathsIII（不同路径III）
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

class Problem0980UniquePathsIII0 {

    int t, mask, m, n;
    Map<Integer, Integer> memo;
    int[] dirs = {-1, 0, 1, 0, -1};

    public int uniquePathsIII(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        mask = 0;
        int s = 0;
        for (int i = 0, k = 0; i < m; i++) {
            for (int j = 0; j < n; j++, k++) {
                if (grid[i][j] == 1) {
                    s = k;
                } else if (grid[i][j] == 2) {
                    t = k;
                }
                if (grid[i][j] != -1) {
                    mask |= 1 << k;
                }
            }
        }
        memo = new HashMap<>();
        return dfs(grid, (1 << s) << 5 | s);
    }

    private int dfs(int[][] grid, int hash) {
        int v1 = hash & ((1 << 5) - 1), v2 = hash >> 5;
        if (v1 == t) {
            return v2 == mask ? 1 : 0;
        }
        if (memo.containsKey(hash)) {
            return memo.get(hash);
        }
        int x = v1 / n, y = v1 % n, ans = 0;
        for (int d = 0; d < 4; d++) {
            int nx = x + dirs[d], ny = y + dirs[d + 1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] != -1) {
                int v = nx * n + ny;
                if ((v2 & 1 << v) == 0) {
                    ans += dfs(grid, (v2 | 1 << v) << 5 | v);
                }
            }
        }
        memo.put(hash, ans);
        return ans;
    }
}
