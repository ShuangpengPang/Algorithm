package com.shuangpeng.Problem.p3301_3400;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3341FindMinimumTimeToReachLastRoomI（到达最后一个房间的最少时间I）
 * @date 2025/2/27 5:46 PM
 */
public class Problem3341FindMinimumTimeToReachLastRoomI {

    private int[] dirs = {-1, 0, 1, 0, -1};

    public int minTimeToReach(int[][] moveTime) {
        int m = moveTime.length, n = moveTime[0].length;
        int[][] dis = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.offer(new int[3]);
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int x = p[0], y = p[1], t = p[2];
            if (t > dis[x][y]) {
                continue;
            }
            if (x == m - 1 && y == n - 1) {
                return t;
            }
            dis[x][y] = -1;
            for (int d = 0; d < 4; d++) {
                int nx = x + dirs[d], ny = y + dirs[d + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    int nt = Math.max(moveTime[nx][ny] + 1, t + 1);
                    if (nt < dis[nx][ny]) {
                        dis[nx][ny] = nt;
                        pq.offer(new int[]{nx, ny, nt});
                    }
                }
            }
        }
        return -1;
    }
}
