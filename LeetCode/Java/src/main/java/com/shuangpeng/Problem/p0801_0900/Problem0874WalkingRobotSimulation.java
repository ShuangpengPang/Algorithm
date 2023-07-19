package com.shuangpeng.Problem.p0801_0900;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0874WalkingRobotSimulation（模拟行走机器人）
 * @date 2023/3/3 11:25 AM
 */
public class Problem0874WalkingRobotSimulation {

    public int robotSim0(int[] commands, int[][] obstacles) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] o : obstacles) {
            map.computeIfAbsent(o[0], k -> new HashSet<>()).add(o[1]);
        }
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 0, y = 0, d = 0, ans = 0;
        for (int c : commands) {
            if (c == -2) {
                d = (d + 3) % 4;
            } else if (c == -1) {
                d = (d + 1) % 4;
            } else {
                for (int i = 0; i < c && (!map.containsKey(x + dirs[d][0]) || !map.get(x + dirs[d][0]).contains(y + dirs[d][1])); i++) {
                    x += dirs[d][0];
                    y += dirs[d][1];
                    ans = Math.max(ans, x * x + y * y);
                }
            }
        }
        return ans;
    }

    public int robotSim1(int[] commands, int[][] obstacles) {
        Set<Long> set = new HashSet<>(obstacles.length);
        for (int[] o : obstacles) {
            set.add(getHash(o[0], o[1]));
        }
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 0, y = 0, d = 0, ans = 0;
        for (int c : commands) {
            if (c == -2) {
                d = (d + 3) % 4;
            } else if (c == -1) {
                d = (d + 1) % 4;
            } else {
                int nx = x + dirs[d][0], ny = y + dirs[d][1];
                for (int i = 0; i < c && !set.contains(getHash(nx, ny)); i++) {
                    x = nx;
                    y = ny;
                    nx += dirs[d][0];
                    ny += dirs[d][1];
                }
                ans = Math.max(ans, x * x + y * y);
            }
        }
        return ans;
    }

    private long getHash(long x, long y) {
        long N = (long) 3e4;
        return (x + N << 17) | (y + N);
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        Map<Integer, TreeSet<Integer>> xMap = new HashMap<>(), yMap = new HashMap<>();
        for (int[] o : obstacles) {
            xMap.computeIfAbsent(o[0], k -> new TreeSet<>()).add(o[1]);
            yMap.computeIfAbsent(o[1], k -> new TreeSet<>()).add(o[0]);
        }
        int[] dirs = {0, 1, 0, -1, 0};
        int dir = 0, dx = 0, x = 0, y = 0;
        int ans = 0;
        for (int c : commands) {
            if (c < 0) {
                dir = (dir + (c << 1) + 7) % 4;
                dx = dirs[dir];
            } else {
                Map<Integer, TreeSet<Integer>> m = dx == 0 ? xMap : yMap;
                int key = dx == 0 ? x : y, num = dx == 0 ? y : x;
                int d = dx == 0 ? dirs[dir + 1] : dx;
                TreeSet<Integer> set = m.get(key);
                int s = c;
                if (set != null) {
                    Integer t = d == -1 ? set.lower(num) : set.higher(num);
                    s = Math.min(c, t == null ? c : Math.abs(t - num) - 1);
                }
                x += s * dirs[dir];
                y += s * dirs[dir + 1];
                ans = Math.max(ans, x * x + y * y);
            }
        }
        return ans;
    }
}
