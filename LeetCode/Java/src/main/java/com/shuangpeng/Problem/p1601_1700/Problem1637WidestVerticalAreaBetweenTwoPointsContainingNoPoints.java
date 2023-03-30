package com.shuangpeng.Problem.p1601_1700;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1637WidestVerticalAreaBetweenTwoPointsContainingNoPoints（两点之间不包含任何点的最宽垂直区域）
 * @date 2023/3/30 10:01 AM
 */
public class Problem1637WidestVerticalAreaBetweenTwoPointsContainingNoPoints {

    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        int ans = Integer.MIN_VALUE, n = points.length;
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, points[i][0] - points[i - 1][0]);
        }
        return ans;
    }
}
