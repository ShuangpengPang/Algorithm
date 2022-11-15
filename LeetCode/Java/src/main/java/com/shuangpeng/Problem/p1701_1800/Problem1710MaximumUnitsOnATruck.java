package com.shuangpeng.Problem.p1701_1800;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1710MaximumUnitsOnATruck（卡车上的最大单元数）
 * @date 2022/11/15 10:04 AM
 */
public class Problem1710MaximumUnitsOnATruck {

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int n = boxTypes.length, ans = 0;
        for (int i = 0; i < n && truckSize > 0; i++) {
            int cnt = Math.min(boxTypes[i][0], truckSize);
            ans += cnt * boxTypes[i][1];
            truckSize -= cnt;
        }
        return ans;
    }
}
