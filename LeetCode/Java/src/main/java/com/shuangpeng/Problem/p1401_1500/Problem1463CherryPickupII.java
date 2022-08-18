package com.shuangpeng.Problem.p1401_1500;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: Problem1463CherryPickupII（摘樱桃II）
 * @Date 2022/8/18 5:13 PM
 * @Version 1.0
 */
public class Problem1463CherryPickupII {

    public int cherryPickup(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[n][n];
        dp[0][n - 1] = grid[0][0] + grid[0][n - 1];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, n - 1, dp[0][n - 1]});
        int ans = dp[0][n - 1];
        for (int r = 1; r < m; r++) {
            int[][] tmp = new int[n][n];
            for (int i = q.size() - 1; i >= 0; i--) {
                int[] arr = q.poll();
                int x = arr[0], y = arr[1], c = arr[2];
                if (c != dp[x][y]) {
                    continue;
                }
                for (int d1 = -1; d1 <= 1; d1++) {
                    int nx = x + d1;
                    if (nx < 0 || nx >= n) {
                        continue;
                    }
                    for (int d2 = -1; d2 <= 1; d2++) {
                        int ny = y + d2;
                        if (ny < 0 || ny >= n || ny <= nx || tmp[nx][ny] >= c + grid[r][nx] + grid[r][ny]) {
                            continue;
                        }
                        tmp[nx][ny] = c + grid[r][nx] + grid[r][ny];
                        q.offer(new int[]{nx, ny, tmp[nx][ny]});
                        ans = Math.max(ans, tmp[nx][ny]);
                    }
                }
            }
            dp = tmp;
        }
        return ans;
    }
}
