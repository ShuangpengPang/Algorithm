package com.shuangpeng.Problem.p0801_0900;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: Problem0812LargestTriangleArea（最大三角形面积）
 * @Date 2022/5/15 10:58 PM
 * @Version 1.0
 */
public class Problem0812LargestTriangleArea {

    public double largestTriangleArea0(int[][] points) {
        int n = points.length;
        double ans = 0d;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = j + 1; k < n; ++k) {
                    int x1 = points[j][0] - points[i][0], y1 = points[j][1] - points[i][1];
                    int x2 = points[k][0] - points[i][0], y2 = points[k][1] - points[i][1];
                    ans = Math.max(ans, Math.abs(x1 * y2 - y1 * x2) / 2d);
                }
            }
        }
        return ans;
    }

    public double largestTriangleArea(int[][] points) {
        int[][] hull = getConvexHull(points);
        int n = hull.length;
        double ans = 0.0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1, k = j + 1; j < n && k < n; ++j) {
                while (k + 1 < n) {
                    if (triangleArea(hull[i], hull[j], hull[k]) > triangleArea(hull[i], hull[j], hull[k + 1])) {
                        break;
                    }
                    ++k;
                }
                ans = Math.max(ans, triangleArea(hull[i], hull[j], hull[k]));
            }
        }
        return ans;
    }

    private int[][] getConvexHull(int[][] points) {
        int n = points.length;
        if (n < 4) {
            return points;
        }
        Arrays.sort(points, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        List<int[]> hull = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            while (hull.size() > 1 && cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) <= 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(points[i]);
        }
        int m = hull.size();
        for (int i = n - 1; i >= 0; --i) {
            while (hull.size() > m && cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) <= 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(points[i]);
        }
        hull.remove(hull.size() - 1);
        m = hull.size();
        int[][] hullArr = new int[m][];
        for (int i = 0; i < m; ++i) {
            hullArr[i] = hull.get(i);
        }
        return hullArr;
    }

    private int cross(int[] p, int[] q, int[] r) {
        int x1 = q[0] - p[0], y1 = q[1] - p[1];
        int x2 = r[0] - q[0], y2 = r[1] - q[1];
        return x1 * y2 - x2 * y1;
    }

    private double triangleArea(int[] p, int[] q, int[] r) {
        return 0.5 * Math.abs((q[0] - p[0]) * (r[1] - p[1]) - (q[1] - p[1]) * (r[0] - p[0]));
    }
}
