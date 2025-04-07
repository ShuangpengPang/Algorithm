package com.shuangpeng.Problem.p3301_3400;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3380MaximumAreaRectangleWithPointConstraintsI（用点构造面积最大的矩形I）
 * @date 2025/4/7 12:11
 */
public class Problem3380MaximumAreaRectangleWithPointConstraintsI {

    public int maxRectangleArea(int[][] points) {
        int n = points.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(101 * points[i][0] + points[i][1], i);
        }
        int ans = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || points[i][1] != points[j][1] || points[i][0] > points[j][0]) {
                    continue;
                }
                for (int x = 0; x < n; x++) {
                    if (x == i || x == j) {
                        continue;
                    }
                    if (points[x][0] == points[i][0]) {
                        int y = map.getOrDefault(points[j][0] * 101 + points[x][1], -1);
                        if (y != -1 && check(points, i, j, x, y)) {
                            ans = Math.max(ans, (points[j][0] - points[i][0]) * Math.abs(points[x][1] - points[i][1]));
                        }
                    }
                }
            }
        }
        return ans;
    }

    private boolean check(int[][] points, int i, int j, int x, int y) {
        int left = Math.min(points[i][0], points[j][0]), right = points[i][0] + points[j][0] - left;
        int low = Math.min(points[i][1], points[x][1]), high = points[i][1] + points[x][1] - low;
        int n = points.length;
        for (int k = 0; k < n; k++) {
            if (k == i || k == j || k == x || k == y) {
                continue;
            }
            if (points[k][0] >= left && points[k][0] <= right && points[k][1] >= low && points[k][1] <= high) {
                return false;
            }
        }
        return true;
    }
}
