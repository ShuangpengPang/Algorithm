package com.shuangpeng.Problem.p1801_1900;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description: Problem1840MaximumBuildingHeight（最高建筑高度）
 * @Date 2022/10/19 11:15 AM
 * @Version 1.0
 */
public class Problem1840MaximumBuildingHeight {

    public int maxBuilding0(int n, int[][] restrictions) {
        int m = restrictions.length;
        if (m == 0) {
            return n - 1;
        }
        Arrays.sort(restrictions, Comparator.comparingInt(a -> a[0]));
        int pId = 1, pH = 0;
        for (int i = 0; i < m; i++) {
            int j = restrictions[i][0], h = restrictions[i][1];
            pH = Math.min(h, pH + j - pId);
            restrictions[i][1] = pH;
            pId = j;
        }
        pId = n;
        pH = Math.min(n - 1, restrictions[m - 1][1] + n - restrictions[m - 1][0]);
        int ans = pH;
        for (int i = m - 1; i >= 0; i--) {
            int j = restrictions[i][0];
            int h = Math.min(restrictions[i][1], pH + pId - j);
            int minH = Math.min(h, pH), maxH = Math.max(h, pH);
            int id = j + maxH - minH;
            ans = Math.max(ans, maxH + ((pId - id) >> 1));
            pId = j;
            pH = h;
        }
        return Math.max(ans, pH + (pId - (1 + pH)) >> 1);
    }

    public static int maxBuilding(int n, int[][] restrictions) {
        Arrays.sort(restrictions, (o1, o2) -> o1[0] + o1[1] - o2[0] - o2[1]);
        int res = 0, b = -1;
        for (int[] restriction : restrictions)
            if (restriction[1] - restriction[0] < b) {
                res = Math.max(res, (restriction[0] + restriction[1] + b) >>> 1);
                b = restriction[1] - restriction[0];
            }
        return Math.max(res, n + b);
    }
}
