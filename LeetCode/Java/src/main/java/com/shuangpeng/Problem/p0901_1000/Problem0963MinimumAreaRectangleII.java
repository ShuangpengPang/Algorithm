package com.shuangpeng.Problem.p0901_1000;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0963MinimumAreaRectangleII（最小面积矩形II）
 * @date 2023/3/30 5:42 PM
 */
public class Problem0963MinimumAreaRectangleII {

    public double minAreaFreeRect(int[][] points) {
        int n = points.length;
        if (n < 4) {
            return 0d;
        }
        long ans = Long.MAX_VALUE;
        for (int i = 0; i <= n - 4; i++) {
            for (int j = i + 1; j <= n - 3; j++) {
                for (int x = j + 1; x <= n - 2; x++) {
                    for (int y = x + 1; y < n; y++) {
                        ans = Math.min(ans, getArea(points, i, j, x, y));
                        ans = Math.min(ans, getArea(points, i, x, y, j));
                        ans = Math.min(ans, getArea(points, i, j, y, x));
                    }
                }
            }
        }
        return ans == Long.MAX_VALUE ? 0d : ans;
    }

    private long getArea(int[][] points, int p1, int p2, int p3, int p4) {
        int[] edge1 = getEdge(points, p1, p2), edge2 = getEdge(points, p1, p3);
        if (dotProduct(edge1, edge2) != 0 || !checkDiagonal(points, p1, p2, p3, p4)) {
            return Long.MAX_VALUE;
        }
        int[] edge3 = getEdge(points, p4, p2), edge4 = getEdge(points, p4, p3);
        return dotProduct(edge3, edge4) == 0 ? crossProduct(edge1, edge2) : Long.MAX_VALUE;
    }

    private int[] getEdge(int[][] points, int p1, int p2) {
        return new int[]{points[p2][0] - points[p1][0], points[p2][1] - points[p1][1]};
    }

    private long dotProduct(int[] p1, int[] p2) {
        return (long) p1[0] * p2[0] + (long) p1[1] * p2[1];
    }

    private long crossProduct(int[] p1, int[] p2) {
        return Math.abs((long) p1[0] * p2[1] - (long) p2[0] * p1[1]);
    }

    private boolean checkDiagonal(int[][] points, int p1, int p2, int p3, int p4) {
        int[] e1 = getEdge(points, p1, p4), e2 = getEdge(points, p2, p3);
        return (long) e1[0] * e1[0] + (long) e1[1] * e1[1] == (long) e2[0] * e2[0] + (long) e2[1] * e2[1];
    }
}

class Problem0963MinimumAreaRectangleII0 {

    public double minAreaFreeRect(int[][] points) {
        int n = points.length;
        Set<Pair<Integer, Integer>> set = new HashSet<>(n);
        for (int[] p : points) {
            set.add(new Pair<>(p[0], p[1]));
        }
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                for (int k = j + 1; k < n; k++) {
                    if (i == k) {
                        continue;
                    }
                    ans = Math.min(ans, getArea(points, i, j, k, set));
                }
            }
        }
        return ans == Long.MAX_VALUE ? 0 : ans;
    }

    private long getArea(int[][] points, int p1, int p2, int p3, Set<Pair<Integer, Integer>> set) {
        int[] edge1 = getEdge(points, p1, p2), edge2 = getEdge(points, p1, p3);
        int x = points[p2][0] + points[p3][0] - points[p1][0];
        int y = points[p2][1] + points[p3][1] - points[p1][1];
        if (dotProduct(edge1, edge2) != 0 || !set.contains(new Pair<>(x, y))) {
            return Long.MAX_VALUE;
        }
        return crossProduct(edge1, edge2);
    }

    private int[] getEdge(int[][] points, int p1, int p2) {
        return new int[]{points[p2][0] - points[p1][0], points[p2][1] - points[p1][1]};
    }

    private long dotProduct(int[] p1, int[] p2) {
        return (long) p1[0] * p2[0] + (long) p1[1] * p2[1];
    }

    private long crossProduct(int[] p1, int[] p2) {
        return Math.abs((long) p1[0] * p2[1] - (long) p2[0] * p1[1]);
    }
}
