package com.shuangpeng.lcr.p001_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR074Merge（合并区间）
 * @date 2024/6/30 6:42 PM
 */
public class LCR074Merge {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(e -> e[0]));
        int i = 0, n = intervals.length;
        List<int[]> list = new ArrayList<>(n);
        while (i < n) {
            int j = i + 1, end = intervals[i][1];
            while (j < n && end >= intervals[j][0]) {
                end = Math.max(end, intervals[j][1]);
                j++;
            }
            list.add(new int[]{intervals[i][0], end});
            i = j;
        }
        int m = list.size();
        int[][] ans = new int[m][2];
        for (int j = 0; j < m; j++) {
            ans[j] = list.get(j);
        }
        return ans;
    }
}
