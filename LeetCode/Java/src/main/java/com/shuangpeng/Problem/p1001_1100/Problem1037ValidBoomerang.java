package com.shuangpeng.Problem.p1001_1100;

/**
 * @Description: Problem1037ValidBoomerang（有效的回旋镖）
 * @Date 2022/6/8 10:12 AM
 * @Version 1.0
 */
public class Problem1037ValidBoomerang {

    public boolean isBoomerang(int[][] points) {
        int x1 = points[1][0] - points[0][0], y1 = points[1][1] - points[0][1];
        int x2 = points[2][0] - points[0][0], y2 = points[2][1] - points[0][1];
        return x1 * y2 != x2 * y1;
    }
}
