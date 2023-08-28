package com.shuangpeng.Problem.p0001_0100;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:（插入区间）
 * @date 2023/8/28 11:14 AM
 **/
public class Problem0057InsertInterval {

    public int[][] insert0(int[][] intervals, int[] newInterval) {
        int s = newInterval[0], e = newInterval[1];
        List<int[]> list = new ArrayList<>();
        int left = s, right = e;
        boolean add = false;
        for (int[] interval : intervals) {
            if (interval[1] < s) {
                list.add(interval);
            } else if (interval[0] <= e) {
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            } else {
                if (!add) {
                    add = true;
                    list.add(new int[]{left, right});
                }
                list.add(interval);
            }
        }
        if (!add) {
            list.add(new int[]{left, right});
        }
        int n = list.size();
        int[][] ans = new int[n][2];
        for (int i = 0; i < n; ++i) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        int start = newInterval[0], end = newInterval[1], n = intervals.length;
        boolean add = false;
        for (int i = 0; i < n; i++) {
            int s = intervals[i][0], e = intervals[i][1];
            if (e < start) {
                list.add(intervals[i]);
            } else if (end < s) {
                if (!add) {
                    list.add(new int[]{start, end});
                    add = true;
                }
                list.add(intervals[i]);
            } else {
                start = Math.min(start, s);
                end = Math.max(end, e);
            }
        }
        if (!add) {
            list.add(new int[]{start, end});
        }
        return list.toArray(new int[0][]);
    }
}
