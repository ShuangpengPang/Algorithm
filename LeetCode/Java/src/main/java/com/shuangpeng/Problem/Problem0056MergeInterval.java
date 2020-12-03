package com.shuangpeng.Problem;

import java.util.Arrays;
import java.util.Comparator;

public class Problem0056MergeInterval {

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length < 2 || intervals[0].length < 2) {
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int[][] temp = new int[intervals.length][2];
        temp[0][0] = intervals[0][0];
        temp[0][1] = intervals[0][1];
        int current = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (temp[current][1] >= intervals[i][0]) {
                temp[current][1] = Math.max(temp[current][1], intervals[i][1]);
            } else {
                current++;
                temp[current][0] = intervals[i][0];
                temp[current][1] = intervals[i][1];
            }
        }
        int[][] result = new int[current + 1][2];
        for (int i = 0; i < result.length; i++) {
            result[i][0] = temp[i][0];
            result[i][1] = temp[i][1];
        }
        return result;
    }
}
