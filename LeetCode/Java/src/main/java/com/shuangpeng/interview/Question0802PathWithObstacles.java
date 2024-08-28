package com.shuangpeng.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0802PathWithObstacles（面试题 08.02. 迷路的机器人）
 * @date 2024/8/28 4:19 PM
 */
public class Question0802PathWithObstacles {

    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        List<List<Integer>> ans = new ArrayList<>();
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0 || obstacleGrid[0][0] == 1) {
            return ans;
        }
        dfs(obstacleGrid, 0, 0, ans, new Boolean[obstacleGrid.length][obstacleGrid[0].length]);
        return ans;
    }

    private boolean dfs(int[][] obstacleGrid, int x, int y, List<List<Integer>> ans, Boolean[][] memo) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if (x == m - 1 && y == n - 1) {
            ans.add(Arrays.asList(x, y));
            return true;
        }
        if (memo[x][y] != null) {
            return memo[x][y];
        }
        ans.add(Arrays.asList(x, y));
        boolean res = y + 1 < n && obstacleGrid[x][y + 1] == 0 && dfs(obstacleGrid, x, y + 1, ans, memo);
        res = res || x + 1 < m && obstacleGrid[x + 1][y] == 0 && dfs(obstacleGrid, x + 1, y, ans, memo);
        if (!res) {
            ans.remove(ans.size() - 1);
        }
        return memo[x][y] = res;
    }
}
