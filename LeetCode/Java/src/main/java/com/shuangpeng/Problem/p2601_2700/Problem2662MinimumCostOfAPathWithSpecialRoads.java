package com.shuangpeng.Problem.p2601_2700;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2662MinimumCostOfAPathWithSpecialRoads（前往目标的最小代价）
 * @date 2023/8/16 10:52 AM
 */
public class Problem2662MinimumCostOfAPathWithSpecialRoads {

    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        int n = specialRoads.length, N = Integer.MAX_VALUE >> 1;
        int[] dis = new int[n];
        Arrays.fill(dis, N);
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int sx = start[0], sy = start[1], tx = target[0], ty = target[1];
        for (int i = 0; i < n; i++) {
            int x = specialRoads[i][2], y = specialRoads[i][3], c = specialRoads[i][4];
            dis[i] = Math.min(x - sx + y - sy, specialRoads[i][0] - sx + specialRoads[i][1] - sy + c);
            q.offer(new int[]{i, dis[i]});
        }
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int idx = p[0];
            if (p[1] > dis[idx] || visited[idx]) {
                continue;
            }
            visited[idx] = true;
            int x = specialRoads[idx][2], y = specialRoads[idx][3];
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    int x1 = specialRoads[i][0], y1 = specialRoads[i][1];
                    int x2 = specialRoads[i][2], y2 = specialRoads[i][3], c = specialRoads[i][4];
                    int d = dis[idx] + Math.min(Math.abs(x - x2) + Math.abs(y - y2), Math.abs(x - x1) + Math.abs(y - y1) + c);
                    if (d < dis[i]) {
                        dis[i] = d;
                        q.offer(new int[]{i, d});
                    }
                }
            }
        }
        int ans = tx + ty - sx - sy;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dis[i] + tx + ty - specialRoads[i][2] - specialRoads[i][3]);
        }
        return ans;
    }
}
