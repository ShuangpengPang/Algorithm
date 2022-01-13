package com.shuangpeng.Problem.p0801_0900;

import java.util.*;

public class Problem0864ShortestPathToGetAllKeys {

    private static final int INF = Integer.MAX_VALUE;

    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        Map<Character, int[]> location = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                char c = grid[i].charAt(j);
                if (c != '.' && c != '#') {
                    location.put(c, new int[]{i, j});
                }
            }
        }
        int keyCount = (location.size() - 1) >> 1;
        List<int[]> possibles = new ArrayList<>();
        permutation(new int[keyCount], 0, keyCount, possibles);
        int ans = INF;
        next: for (int[] possible : possibles) {
            int step = 0;
            for (int i = 0; i < keyCount; ++i) {
                char s = i == 0 ? '@' : (char) ('a' + possible[i - 1]);
                char t = (char) ('a' + possible[i]);
                int keyMask = 0;
                for (int j = 0; j < i; ++j) {
                    keyMask |= (1 << possible[j]);
                }
                int result = bfs(grid, s, t, keyMask, location);
                if (result == INF) {
                    continue next;
                }
                step += result;
                if (step >= ans) {
                    continue next;
                }
            }
            ans = step;
        }
        return ans == INF ? -1 : ans;
    }

    private int bfs(String[] grid, char source, char target, int keyMask, Map<Character, int[]> location) {
        int[] num1 = location.get(source), num2 = location.get(target);
        int sx = num1[0];
        int sy = num1[1];
        int tx = num2[0];
        int ty = num2[1];
        int m = grid.length, n = grid[0].length();
        boolean[][] seen = new boolean[m][n];
        seen[sx][sy] = true;
        final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int step = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sx, sy});
        while (!queue.isEmpty()) {
            ++step;
            for (int i = queue.size() - 1; i >= 0; --i) {
                int[] pair = queue.poll();
                for (int[] dir : dirs) {
                    int x = pair[0] + dir[0], y = pair[1] + dir[1];
                    if (x == tx && y == ty) {
                        return step;
                    }
                    if (x >= 0 && x < m && y >= 0 && y < n && !seen[x][y] && grid[x].charAt(y) != '#') {
                        char c = grid[x].charAt(y);
                        if (c >= 'A' && c <= 'Z' && (keyMask >> (c - 'A') & 1) == 0) {
                            continue;
                        }
                        seen[x][y] = true;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
        }
        return INF;
    }

    private void permutation(int[] nums, int used, int size, List<int[]> ans) {
        if (size == 0) {
            ans.add(nums.clone());
            return;
        }
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (((used >> i) & 1) == 0) {
                nums[size - 1] = i;
                permutation(nums, used | (1 << i), size - 1, ans);
            }
        }
    }
}
