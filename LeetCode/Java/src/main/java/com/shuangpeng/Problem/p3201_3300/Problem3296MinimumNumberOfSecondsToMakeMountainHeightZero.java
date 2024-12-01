package com.shuangpeng.Problem.p3201_3300;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3296MinimumNumberOfSecondsToMakeMountainHeightZero（移山所需的最少秒数）
 * @date 2024/11/29 7:36 PM
 */
public class Problem3296MinimumNumberOfSecondsToMakeMountainHeightZero {

    public long minNumberOfSeconds0(int mountainHeight, int[] workerTimes) {
        int n = workerTimes.length, min = Integer.MAX_VALUE;
        for (int t : workerTimes) {
            min = Math.min(min, t);
        }
        long left = 0, right = (long) mountainHeight * (mountainHeight + 1) / 2 * min;
        while (left <= right) {
            long mid = left + (right - left >> 1);
            if (!check(mountainHeight, workerTimes, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int mountainHeight, int[] workerTimes, long t) {
        int n = workerTimes.length;
        for (int i = 0; i < n && mountainHeight > 0; i++) {
            mountainHeight -= (int) ((Math.sqrt(1 + 8 * t / workerTimes[i]) - 1) / 2);
        }
        return mountainHeight <= 0;
    }

    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        int n = workerTimes.length;
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[2]));
        for (int i = 0; i < n; i++) {
            pq.offer(new long[]{i, 1, workerTimes[i]});
        }
        long ans = 0;
        for (int i = 0; i < mountainHeight; i++) {
            long[] arr = pq.poll();
            ans = Math.max(ans, arr[2]);
            arr[1]++;
            arr[2] += workerTimes[(int) arr[0]] * arr[1];
            pq.offer(arr);
        }
        return ans;
    }
}
