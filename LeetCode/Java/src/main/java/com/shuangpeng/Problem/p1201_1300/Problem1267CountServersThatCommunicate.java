package com.shuangpeng.Problem.p1201_1300;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1267CountServersThatCommunicate（统计参与通信的服务器）
 * @date 2023/6/17 10:05 PM
 */
public class Problem1267CountServersThatCommunicate {

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
