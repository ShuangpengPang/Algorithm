package com.shuangpeng.Problem.p2601_2700;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2617MinimumNumberOfVisitedCellsInAGrid（网格图中最少访问的格子数）
 * @date 2024/2/1 2:52 PM
 */
public class Problem2617MinimumNumberOfVisitedCellsInAGrid {

    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dis = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }
        TreeSet<Integer>[] rows = new TreeSet[m], cols = new TreeSet[n];
        for (int i = 0; i < m; i++) {
            rows[i] = new TreeSet<>();
            for (int j = 0; j < n; j++) {
                rows[i].add(j);
            }
        }
        for (int j = 0; j < n; j++) {
            cols[j] = new TreeSet<>();
            for (int i = 0; i < m; i++) {
                cols[j].add(i);
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.offer(new int[]{0, 0, 1});
        dis[0][0] = 1;
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int x = p[0], y = p[1], d = p[2];
            if (d > dis[x][y]) {
                continue;
            }
            if (x == m - 1 && y == n - 1) {
                return d;
            }
            for (Integer i = rows[x].higher(y); i != null && i < n && i <= grid[x][y] + y; i = rows[x].higher(i)) {
                rows[x].remove(i);
                if (dis[x][i] > d + 1) {
                    dis[x][i] = d + 1;
                    pq.offer(new int[]{x, i, d + 1});
                }
            }
            for (Integer i = cols[y].higher(x); i != null && i < m && i <= grid[x][y] + x; i = cols[y].higher(i)) {
                cols[y].remove(i);
                if (dis[i][y] > d + 1) {
                    dis[i][y] = d + 1;
                    pq.offer(new int[]{i, y, d + 1});
                }
            }
        }
        return -1;
    }
}
