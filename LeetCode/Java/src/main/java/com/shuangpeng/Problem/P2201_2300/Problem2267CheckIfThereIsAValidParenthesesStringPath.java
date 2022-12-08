package com.shuangpeng.Problem.P2201_2300;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2267CheckIfThereIsAValidParenthesesStringPath（检查是否有合法括号字符串路径）
 * @date 2022/12/8 3:28 PM
 */
public class Problem2267CheckIfThereIsAValidParenthesesStringPath {

    public boolean hasValidPath(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(' || ((m + n - 1) & 1) == 1) {
            return false;
        }
        return dfs(grid, 0, 0, 0, new HashMap<>());
    }

    private boolean dfs(char[][] grid, int x, int y, int cnt, Map<Integer, Boolean> memo) {
        int m = grid.length, n = grid[0].length;
        if (x == m || y == n) {
            return false;
        }
        int key = x | y << 10 | cnt << 20;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        cnt += grid[x][y] == '(' ? 1 : -1;
        if (cnt < 0) {
            return false;
        }
        if (x == m - 1 && y == n - 1) {
            return cnt == 0;
        }
        boolean ans = dfs(grid, x, y + 1, cnt, memo) || dfs(grid, x + 1, y, cnt, memo);
        memo.put(key, ans);
        return ans;
    }
}

class Problem2267CheckIfThereIsAValidParenthesesStringPath0 {

    public boolean hasValidPath(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(' || ((m + n) & 1) == 0) {
            return false;
        }
        return dfs(grid, 0, 0, 0, new boolean[m][n][(m + n + 1) >> 1]);
    }

    private boolean dfs(char[][] grid, int x, int y, int cnt, boolean[][][] visited) {
        int m = grid.length, n = grid[0].length;
        if (x == m || y == n || cnt > m + n - 1 - x - y || visited[x][y][cnt]) {
            return false;
        }
        if (x == m - 1 && y == n - 1) {
            return cnt == 1;
        }
        visited[x][y][cnt] = true;
        cnt += grid[x][y] == '(' ? 1 : -1;
        return cnt >= 0 && (dfs(grid, x, y + 1, cnt, visited) || dfs(grid, x + 1, y, cnt, visited));
    }
}

class Problem2267CheckIfThereIsAValidParenthesesStringPath1 {

    public boolean hasValidPath(char[][] grid) {
        int m = grid.length, n = grid[0].length, len = (m + n - 1) >> 1;
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(' || ((m + n) & 1) == 0) {
            return false;
        }
        boolean[][][] dp = new boolean[m + 1][n + 1][len + 1];
        dp[0][1][0] = dp[1][0][0] = true;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int d = grid[i - 1][j - 1] == '(' ? -1 : 1;
                for (int k = 0; k <= len; k++) {
                    int t = k + d;
                    dp[i][j][k] = t >= 0 && t <= len && (dp[i - 1][j][t] || dp[i][j - 1][t]);
                }
            }
        }
        return dp[m][n][0];
    }
}
