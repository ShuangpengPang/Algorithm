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

    public int countWays0(int[][] ranges) {
        Arrays.sort(ranges, Comparator.comparingInt(a -> a[0]));
        int n = ranges.length, N = (int) 1e9 + 7;
        int ans = 1;
        for (int i = 0, j = 0; i < n; i = j) {
            int end = ranges[i][1];
            while (j < n && ranges[j][0] <= end) {
                end = Math.max(end, ranges[j++][1]);
            }
            ans = (ans << 1) % N;
        }
        return ans;
    }

    public int countWays(int[][] ranges) {
        Arrays.sort(ranges, Comparator.comparingInt(a -> a[0]));
        int ans = 1, end = -1, N = (int) 1e9 + 7;
        for (int[] r : ranges) {
            if (end < r[0]) {
                ans = (ans << 1) % N;
            }
            end = Math.max(end, r[1]);
        }
        return ans;
    }
}
