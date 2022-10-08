package com.shuangpeng.Problem.p1701_1800;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description: Problem1751MaximumNumberOfEventsThatCanBeAttendedII（最多可以参加的会议数目II）
 * @Date 2022/10/8 4:53 PM
 * @Version 1.0
 */
public class Problem1751MaximumNumberOfEventsThatCanBeAttendedII {

    public int maxValue(int[][] events, int k) {
        int n = events.length;
        int[][] dp = new int[n][k + 1];
        Arrays.sort(events, Comparator.comparingInt(a -> a[1]));
        for (int i = 0; i < n; i++) {
            int idx = getIndex(events, i);
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i][j - 1];
                if (i > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
                dp[i][j] = Math.max(dp[i][j], (idx >= 0 ? dp[idx][j - 1] : 0) + events[i][2]);
            }
        }
        return dp[n - 1][k];
    }

    private int getIndex(int[][] events, int i) {
        int left = 0, right = i - 1, start = events[i][0];
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (start > events[mid][1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }
}
