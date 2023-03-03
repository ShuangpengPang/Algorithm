package com.shuangpeng.Problem.p0801_0900;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0874WalkingRobotSimulation（模拟行走机器人）
 * @date 2023/3/3 11:25 AM
 */
public class Problem0874WalkingRobotSimulation {

    public int robotSim(int[] commands, int[][] obstacles) {
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
}
