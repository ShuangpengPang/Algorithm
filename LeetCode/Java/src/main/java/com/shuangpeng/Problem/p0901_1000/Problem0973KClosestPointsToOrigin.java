package com.shuangpeng.Problem.p0901_1000;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0973KClosestPointsToOrigin（最接近原点的K个点）
 * @date 2023/3/29 6:27 PM
 */
public class Problem0973KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int k) {
        Arrays.sort(points, Comparator.comparingInt(a -> a[0] * a[0] + a[1] * a[1]));
        int[][] ans = new int[k][];
        for (int i = 0; i < k; i++) {
            ans[i] = points[i];
        }
        return ans;
    }
}
