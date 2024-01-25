package com.shuangpeng.lcp;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCP03Robot（机器人大冒险）
 * @date 2024/1/25 4:36 PM
 */
public class LCP03Robot {

    public boolean robot(String command, int[][] obstacles, int x, int y) {
        int n = command.length();
        int[] path = new int[n];
        for (int i = 0, cnt = 0; i < n; i++) {
            if (command.charAt(i) == 'R') {
                cnt++;
            }
            path[i] = cnt;
        }
        if (!check(path, x, y)) {
            return false;
        }
        int step = x + y;
        for (int i = 0; i < obstacles.length; i++) {
            int a = obstacles[i][0], b = obstacles[i][1];
            if (a + b < step && check(path, a, b)) {
                return false;
            }
        }
        return true;
    }

    private boolean check(int[] path, int x, int y) {
        int n = path.length;
        int c = (x + y - 1) / n, m = (x + y - 1) % n;
        return path[m] == x - c * path[n - 1] && m + 1 - path[m] == y - c * (n - path[n - 1]);
    }
}
