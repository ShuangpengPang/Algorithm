package com.shuangpeng.Problem.p0901_1000;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0994RottingOrange（腐烂的橘子）
 * @date 2022/11/29 10:59 AM
 */
public class Problem0994RottingOrange {

    private static final int[] dirs = {-1, 0, 1, 0, -1};

    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    cnt++;
                }
            }
        }
        int ans = 0;
        while (cnt > 0 && !q.isEmpty()) {
            ans++;
            for (int i = q.size() - 1; i >= 0; i--) {
                int[] p = q.poll();
                int x = p[0], y = p[1];
                for (int d = 0; d < 4; d++) {
                    int nx = x + dirs[d], ny = y + dirs[d + 1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1) {
                        cnt--;
                        grid[nx][ny] = 2;
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        return cnt == 0 ? ans : -1;
    }
}
