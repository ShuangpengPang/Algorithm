package com.shuangpeng.Problem.p1601_1700;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1637WidestVerticalAreaBetweenTwoPointsContainingNoPoints（两点之间不包含任何点的最宽垂直区域）
 * @date 2023/3/30 10:01 AM
 */
public class Problem1637WidestVerticalAreaBetweenTwoPointsContainingNoPoints {

    public int maxWidthOfVerticalArea0(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        int ans = Integer.MIN_VALUE, n = points.length;
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, points[i][0] - points[i - 1][0]);
        }
        return ans;
    }

    public int maxWidthOfVerticalArea1(int[][] points) {
        int n = points.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = points[i][0];
        }
        final int inf = 1 << 30;
        int mi = inf, mx = -inf;
        for (int v : nums) {
            mi = Math.min(mi, v);
            mx = Math.max(mx, v);
        }
        int bucketSize = Math.max(1, (mx - mi) / (n - 1));
        int bucketCount = (mx - mi) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][2];
        for (int[] bucket : buckets) {
            bucket[0] = inf;
            bucket[1] = -inf;
        }
        for (int v : nums) {
            int i = (v - mi) / bucketSize;
            buckets[i][0] = Math.min(buckets[i][0], v);
            buckets[i][1] = Math.max(buckets[i][1], v);
        }
        int prev = inf;
        int ans = 0;
        for (int[] bucket : buckets) {
            if (bucket[0] > bucket[1]) {
                continue;
            }
            ans = Math.max(ans, bucket[0] - prev);
            prev = bucket[1];
        }
        return ans;
    }

    public int maxWidthOfVerticalArea(int[][] points) {
        int n = points.length, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int[] p : points) {
            min = Math.min(min, p[0]);
            max = Math.max(max, p[0]);
        }
        int d = (max - min + n - 2) / (n - 1);
        if (d == 0) {
            return 0;
        }
        int size = (max - min + d) / d;
        int[][] bucket = new int[size][2];
        for (int i = 0; i < size; i++) {
            bucket[i][0] = Integer.MAX_VALUE;
            bucket[i][1] = Integer.MIN_VALUE;
        }
        for (int[] p : points) {
            int x = p[0], idx = (x - min) / d;
            bucket[idx][0] = Math.min(bucket[idx][0], x);
            bucket[idx][1] = Math.max(bucket[idx][1], x);
        }
        int ans = 0, last = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            int x = bucket[i][0];
            if (x == Integer.MAX_VALUE) {
                continue;
            }
            ans = Math.max(ans, x - last);
            last = bucket[i][1];
        }
        return ans;
    }
}
