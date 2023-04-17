package com.shuangpeng.Problem.p1001_1100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1041RobotBoundedInCircle（困于环中的机器人）
 * @date 2023/4/11 6:26 PM
 */
public class Problem1041RobotBoundedInCircle {

    public boolean isRobotBounded0(String instructions) {
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 0, y = 0, d = 0, n = instructions.length();
        for (int i = 0; i < n; i++) {
            char c = instructions.charAt(i);
            if (c == 'G') {
                x += dirs[d][0];
                y += dirs[d][1];
            } else {
                d = (d + (c == 'R' ? 1 : 3)) % 4;
            }
        }
        return x == 0 && y == 0 || d != 0;
    }

    public boolean isRobotBounded(String instructions) {
        int n = instructions.length(), d = 0;
        int[] dist = new int[4];
        for (int i = 0; i < n; i++) {
            char c = instructions.charAt(i);
            if (c == 'L') {
                d = (d + 1) % 4;
            } else if (c == 'R') {
                d = (d + 3) % 4;
            } else {
                dist[d]++;
            }
        }
        return dist[0] == dist[1] && dist[2] == dist[4] || d != 0;
    }
}
