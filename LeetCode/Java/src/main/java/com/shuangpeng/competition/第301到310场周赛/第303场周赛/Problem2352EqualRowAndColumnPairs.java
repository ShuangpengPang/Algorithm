package com.shuangpeng.competition.第301到310场周赛.第303场周赛;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Problem2352EqualRowAndColumnPairs（相等行列对）
 * @Date 2022/7/31 5:45 PM
 * @Version 1.0
 */
public class Problem2352EqualRowAndColumnPairs {

    public int equalPairs0(int[][] grid) {
        int n = grid.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean valid = true;
                for (int k = 0; k < n; k++) {
                    if (grid[i][k] != grid[k][j]) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<String, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb1 = new StringBuilder(), sb2 = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb1.append(grid[i][j]).append(",");
                sb2.append(grid[j][i]).append(",");
            }
            String s1 = sb1.toString(), s2 = sb2.toString();
            map1.put(s1, map1.getOrDefault(s1, 0) + 1);
            map2.put(s2, map2.getOrDefault(s2, 0) + 1);
        }
        int ans = 0;
        for (String s : map1.keySet()) {
            ans += map1.get(s) * map2.getOrDefault(s, 0);
        }
        return ans;
    }
}
