package com.shuangpeng.Problem.p0801_0900;

/**
 * @Description: Problem0812LargestTriangleArea（最大三角形面积）
 * @Date 2022/5/15 10:58 PM
 * @Version 1.0
 */
public class Problem0812LargestTriangleArea {

    public double largestTriangleArea(int[][] points) {
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
}
