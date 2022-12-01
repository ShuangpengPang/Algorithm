package com.shuangpeng.Problem.p1701_1800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1779FindNearestPointThatHasTheSameXOrYCoordinate（找到最近的有相同X或Y坐标的点）
 * @date 2022/12/1 10:00 AM
 */
public class Problem1779FindNearestPointThatHasTheSameXOrYCoordinate {

    public int nearestValidPoint(int x, int y, int[][] points) {
        int n = points.length, idx = -1, d = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int nx = points[i][0], ny = points[i][1];
            if (nx == x || ny == y) {
                int nd = Math.abs(x - nx) + Math.abs(y - ny);
                if (nd < d) {
                    d = nd;
                    idx = i;
                }
            }
        }
        return idx;
    }
}
