package com.shuangpeng.Problem.p1001_1100;

import java.util.Arrays;

public class Problem1024VideoStitching {

    public int videoStitching0(int[][] clips, int time) {
        Arrays.sort(clips, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });
        if (clips[0][0] != 0) {
            return -1;
        }
        int n = clips.length;
        int lastEnd = clips[0][1];
        int maxEnd = clips[0][1];
        int count = 1;
        for (int i = 1; i < n; ++i) {
            int start = clips[i][0], end = clips[i][1];
            if (start > maxEnd) {
                break;
            }
            if (start >= lastEnd) {
                ++count;
                if (start == lastEnd) {
                    lastEnd = Math.max(maxEnd, end);
                } else {
                    lastEnd = maxEnd;
                }
            }
            maxEnd = Math.max(maxEnd, end);
            if (maxEnd >= time) {
                break;
            }
        }
        if (maxEnd < time) {
            return -1;
        }
        return lastEnd >= time ? count : count + 1;
    }

    public int videoStitching1(int[][] clips, int time) {
        final int INF = Integer.MAX_VALUE >> 1;
        int[] dp = new int[time + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 1; i <= time; ++i) {
            for (int[] clip : clips) {
                int start = clip[0], end = clip[1];
                if (start < i && end >= i) {
                    dp[i] = Math.min(dp[i], dp[start] + 1);
                }
            }
        }
        return dp[time] == INF ? -1 : dp[time];
    }

    public int videoStitching(int[][] clips, int time) {
        int[] maxN = new int[time];
        for (int[] clip : clips) {
            int start = clip[0], end = clip[1];
            if (start < time) {
                maxN[start] = Math.max(maxN[start], end);
            }
        }
        int pre = 0, last = 0, count = 0;
        for (int i = 0; i < time; ++i) {
            last = Math.max(last, maxN[i]);
            if (last == i) {
                return -1;
            }
            if (pre == i) {
                ++count;
                pre = last;
            }
        }
        return count;
    }
}
