package com.shuangpeng.Problem.p3001_3100;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3025FindTheNumberOfWaysToPlacePeopleI（人员站位的方案数I）
 * @date 2024/2/28 3:10 PM
 */
public class Problem3025FindTheNumberOfWaysToPlacePeopleI {

    public int numberOfPairs(int[][] points) {
        Arrays.sort(points, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int ans = 0, n = points.length;
        for (int i = n - 1; i >= 0; i--) {
            int p = -1;
            for (int j = i + 1; j < n && p < points[i][1]; j++) {
                if (points[j][1] > p && points[j][1] <= points[i][1]) {
                    p = points[j][1];
                    ans++;
                }
            }
        }
        return ans;
    }
}
