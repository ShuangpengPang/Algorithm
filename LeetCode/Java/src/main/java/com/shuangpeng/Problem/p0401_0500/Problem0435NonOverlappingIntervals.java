package com.shuangpeng.Problem.p0401_0500;

import java.util.Arrays;
import java.util.Comparator;

public class Problem0435NonOverlappingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length <= 1) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int answer = 0;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                answer++;
            } else {
                end = intervals[i][1];
            }
        }
        return answer;
    }
}
