package com.shuangpeng.Problem.p1201_1300;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.stream.IntStream;

/**
 * @Description: Problem1235MaximumProfitInJobScheduling（规划兼职工作）
 * @Date 2022/7/26 12:08 PM
 * @Version 1.0
 */
public class Problem1235MaximumProfitInJobScheduling {

    public int jobScheduling0(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Integer[] ids = IntStream.range(0, n).boxed().toArray(Integer[]::new);
        Arrays.sort(ids, Comparator.comparingInt(a -> endTime[a]));
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0);
        for (int i : ids) {
            int s = startTime[i], e = endTime[i];
            dp.put(e, Math.max(dp.get(dp.floorKey(s)) + profit[i], dp.lastEntry().getValue()));
        }
        return dp.lastEntry().getValue();
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Integer[] ids = IntStream.range(0, n).boxed().toArray(Integer[]::new);
        Arrays.sort(ids, Comparator.comparingInt(a -> endTime[a]));
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int idx = ids[i], s = startTime[idx];
            int left = 0, right = i - 1;
            while (left <= right) {
                int mid = left + (right - left >> 1);
                if (s >= endTime[ids[mid]]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            int p = profit[idx] + (left == 0 ? 0 : dp[left - 1]);
            dp[i] = Math.max(i > 0 ? dp[i - 1] : 0, p);
        }
        return dp[n - 1];
    }
}
