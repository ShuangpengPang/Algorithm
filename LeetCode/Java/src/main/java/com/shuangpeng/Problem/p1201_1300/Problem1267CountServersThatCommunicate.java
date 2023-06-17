package com.shuangpeng.Problem.p1201_1300;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1267CountServersThatCommunicate（统计参与通信的服务器）
 * @date 2023/6/17 10:05 PM
 */
public class Problem1267CountServersThatCommunicate {

    public int countServers0(int[][] grid) {
        int m = grid.length, n = grid[0].length, N = m * n;
        boolean[] overOne = new boolean[N];
        int[] parent = new int[N], rows = new int[m], cols = new int[n];
        Arrays.setAll(parent, i -> i);
        Arrays.fill(rows, -1);
        Arrays.fill(cols, -1);
        for (int i = 0, k = 0; i < m; i++) {
            for (int j = 0; j < n; j++, k++) {
                if (grid[i][j] == 1) {
                    rows[i] = rows[i] == -1 ? k : rows[i];
                    cols[j] = cols[j] == -1 ? k : cols[j];
                    union(parent, rows[i], cols[j], overOne);
                    union(parent, rows[i], k, overOne);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (overOne[find(parent, i)]) {
                ans++;
            }
        }
        return ans;
    }

    private int find(int[] parent, int x) {
        return parent[x] = parent[x] == x ? x : find(parent, parent[x]);
    }

    private void union(int[] parent, int x, int y, boolean[] overOne) {
        int px = find(parent, x), py = find(parent, y);
        if (px != py) {
            parent[py] = px;
            overOne[px] = true;
        }
    }

    public int countServers(int[][] grid) {
        int m = grid.length, n = grid[0].length, N = 250;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    map.merge(i, 1, Integer::sum);
                    map.merge(N + j, 1, Integer::sum);
                }
            }
        }
        int ans = 0;
        for (int i = 0 ; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && (map.get(i) > 1 || map.get(N + j) > 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
