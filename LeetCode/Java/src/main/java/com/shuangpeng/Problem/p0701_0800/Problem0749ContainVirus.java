package com.shuangpeng.Problem.p0701_0800;

import java.util.*;

/**
 * @Description: Problem0749ContainVirus（隔离病毒）
 * @Date 2022/7/18 10:04 AM
 * @Version 1.0
 */
public class Problem0749ContainVirus {

    static final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static final int M = (1 << 16) - 1;

    public int containVirus(int[][] isInfected) {
        int m = isInfected.length, n = isInfected[0].length;
        int ans = 0;
        while (true) {
            List<Set<Integer>> neighbors = new ArrayList<>();
            List<Integer> firewalls = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (isInfected[i][j] == 1) {
                        int idx = neighbors.size() + 1;
                        Set<Integer> neighbor = new HashSet<>();
                        int firewall = 0;
                        Queue<int[]> q = new LinkedList<>();
                        q.offer(new int[]{i, j});
                        isInfected[i][j] = -idx;
                        while (!q.isEmpty()) {
                            int[] p = q.poll();
                            int x = p[0], y = p[1];
                            for (int[] dir : dirs) {
                                int nx = x + dir[0], ny = y + dir[1];
                                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                                    if (isInfected[nx][ny] == 1) {
                                        isInfected[nx][ny] = -idx;
                                        q.offer(new int[]{nx, ny});
                                    } else if (isInfected[nx][ny] == 0) {
                                        firewall++;
                                        neighbor.add(toHash(nx, ny));
                                    }
                                }
                            }
                        }
                        neighbors.add(neighbor);
                        firewalls.add(firewall);
                    }
                }
            }
            if (neighbors.isEmpty()) {
                break;
            }
            int idx = 0, maxNeighbor = neighbors.get(0).size(), size = neighbors.size();
            for (int i = 1; i < size; i++) {
                if (neighbors.get(i).size() > maxNeighbor) {
                    idx = i;
                    maxNeighbor = neighbors.get(i).size();
                }
            }
            ans += firewalls.get(idx);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (isInfected[i][j] < 0) {
                        if (isInfected[i][j] == -idx - 1) {
                            isInfected[i][j] = 2;
                        } else {
                            isInfected[i][j] = 1;
                        }
                    }
                }
            }
            for (int i = 0; i < size; i++) {
                if (i != idx) {
                    for (int h : neighbors.get(i)) {
                        int x = h >> 16, y = h & M;
                        isInfected[x][y] = 1;
                    }
                }
            }
            if (neighbors.size() == 1) {
                break;
            }
        }
        return ans;
    }

    private int toHash(int x, int y) {
        return (x << 16) | y;
    }
}

//class Problem0749ContainVirus0 {
//
//    static final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
//    static final int M = (1 << 16) - 1;
//
//    public int containVirus(int[][] isInfected) {
//    }
//}
