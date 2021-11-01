package com.shuangpeng.Problem.p0401_0500;

import java.util.HashMap;
import java.util.Map;

public class Problem0447NumberOfBoomerangs {

    public int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        Map<Integer, Integer>[] map = new Map[n];
        for (int i = 0; i < n; ++i) {
            map[i] = new HashMap<>();
        }
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];
                int dist = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
                map[i].put(dist, map[i].getOrDefault(dist, 0) + 1);
                map[j].put(dist, map[j].getOrDefault(dist, 0) + 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            Map<Integer, Integer> m = map[i];
            for (int dist : m.keySet()) {
                int count = m.get(dist);
                if (count >= 2) {
                    ans += count * (count - 1);
                }
            }
        }
        return ans;
    }
}
