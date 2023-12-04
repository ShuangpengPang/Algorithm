package com.shuangpeng.Problem.p2501_2600;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2580CountWaysToGroupOverlappingRanges（统计将重叠区间合并成组的方案数）
 * @date 2023/12/4 5:47 PM
 */
public class Problem2580CountWaysToGroupOverlappingRanges {

    public int countWays(int[][] ranges) {
        Arrays.sort(ranges, Comparator.comparingInt(a -> a[0]));
        int n = ranges.length, g = 0, N = (int) 1e9 + 7;
        for (int i = 0, j = 0; i < n; i = j) {
            int end = ranges[i][1];
            while (j < n && ranges[j][0] <= end) {
                end = Math.max(end, ranges[j++][1]);
            }
            g++;
        }
        long ans = 1, p = 2;
        while (g > 0) {
            if ((g & 1) == 1) {
                ans = ans * p % N;
            }
            p = (p * p) % N;
            g >>= 1;
        }
        return (int) ans;
    }
}
