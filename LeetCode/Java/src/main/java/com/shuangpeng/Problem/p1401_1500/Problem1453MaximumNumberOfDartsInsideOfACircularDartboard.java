package com.shuangpeng.Problem.p1401_1500;

/**
 * @Description: Problem1453MaximumNumberOfDartsInsideOfACircularDartboard（圆形靶内的最大飞镖数量）
 * @Date 2022/8/17 10:55 PM
 * @Version 1.0
 */
public class Problem1453MaximumNumberOfDartsInsideOfACircularDartboard {

    private static final double EPSILON = 1e-6;
    private static int S = 0;

    public int numPoints(int[][] darts, int r) {
        S = r * r;
        int n = darts.length, ans = Math.min(n, 1);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dx = darts[j][0] - darts[i][0], dy = darts[j][1] - darts[i][1];
                int s = dx * dx + dy * dy;
                double xMid = (darts[i][0] + darts[j][0]) / 2.0, yMid = (darts[i][1] + darts[j][1]) / 2.0;
                if (s > 4 * S) {
                    continue;
                } else if (s < 4 * S) {
                    double d = Math.sqrt(s);
                    double distance = Math.sqrt(S - s / 4.0);
                    double deltaX = dx / d, deltaY = dy / d;
                    double[] center1 = {xMid - distance * deltaY, yMid + distance * deltaX};
                    double[] center2 = {xMid + distance * deltaY, yMid - distance * deltaX};
                    ans = Math.max(ans, Math.max(getCount(darts, center1), getCount(darts, center2)));
                } else {
                    ans = Math.max(ans, getCount(darts, new double[]{xMid, yMid}));
                }
            }
        }
        return ans;
    }

    private int getCount(int[][] darts, double[] center) {
        int count = 0;
        for (int[] d : darts) {
            double x = d[0] - center[0], y = d[1] - center[1];
            if (x * x + y * y - S < EPSILON) {
                count++;
            }
        }
        return count;
    }
}
