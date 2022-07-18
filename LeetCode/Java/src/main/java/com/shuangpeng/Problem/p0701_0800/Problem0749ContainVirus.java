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

class Problem0749ContainVirus0 {

    static final int[] dirs = {-1, 0, 1, 0, -1};

    public int containVirus(int[][] isInfected) {
        int m = isInfected.length, n = isInfected[0].length;
        int ans = 0;
        while (true) {
            int[][] states = new int[m][n];
            int color = -1, maxCount = 0, r = 0, c = 0, w = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (isInfected[i][j] == 1 && states[i][j] == 0) {
                        int[] wall = new int[1];
                        int count = getNeighborCount(isInfected, states, color, i, j, wall);
                        if (count > maxCount) {
                            maxCount = count;
                            r = i;
                            c = j;
                            w = wall[0];
                        }
                        color--;
                    }
                }
            }
            if (maxCount == 0) {
                break;
            }
            ans += w;
            buildWall(isInfected, r, c);
            states = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (isInfected[i][j] == 1 && states[i][j] == 0) {
                        spread(isInfected, states, i, j);
                    }
                }
            }
        }
        return ans;
    }

    private void spread(int[][] grid, int[][] states, int i, int j) {
        int m = grid.length, n = grid[0].length;
        states[i][j] = 1;
        for (int d = 0; d < 4; d++) {
            int x = i + dirs[d], y = j + dirs[d + 1];
            if (x < 0 || x >= m || y < 0 || y >= n || states[x][y] != 0) {
                continue;
            }
            if (grid[x][y] == 0) {
                states[x][y] = 1;
                grid[x][y] = 1;
            } else if (grid[x][y] == 1) {
                spread(grid, states, x, y);
            }
        }
    }

    private void buildWall(int[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        grid[i][j] = -1;
        for (int d = 0; d < 4; d++) {
            int x = i + dirs[d], y = j + dirs[d + 1];
            if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != 1) {
                continue;
            }
            buildWall(grid, x, y);
        }
    }

    private int getNeighborCount(int[][] grid, int[][] states, int color, int i, int j, int[] wall) {
        int m = grid.length, n = grid[0].length;
        states[i][j] = 1;
        int ans = 0;
        for (int d = 0; d < 4; d++) {
            int x = i + dirs[d], y = j + dirs[d + 1];
            if (x < 0 || x >= m || y < 0 || y >= n || states[x][y] == 1) {
                continue;
            }
            if (grid[x][y] == 0) {
                wall[0]++;
                if (states[x][y] != color) {
                    states[x][y] = color;
                    ans++;
                }
            } else if (grid[x][y] == 1) {
                ans += getNeighborCount(grid, states, color, x, y, wall);
            }
        }
        return ans;
    }
}