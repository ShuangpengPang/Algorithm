package com.shuangpeng.Problem.p0401_0500;

import java.util.*;

/**
 * @Description: Problem0436FindRightInterval（寻找右区间）
 * @Date 2022/5/20 10:11 AM
 * @Version 1.0
 */
public class Problem0436FindRightInterval {

    public int[] findRightInterval0(int[][] intervals) {
        int n = intervals.length;
        Map<Integer, Integer> map = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            map.put(intervals[i][0], i);
        }
        int[] starts = new int[n];
        for (int i = 0; i < n; ++i) {
            starts[i] = intervals[i][0];
        }
        Arrays.sort(starts);
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int end = intervals[i][1];
            int idx = binarySearch(starts, end);
            ans[i] = idx != -1 ? map.get(starts[idx]) : -1;
        }
        return ans;
    }

    private int binarySearch(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int num = nums[mid];
            if (num < target) {
                left = mid + 1;
            } else if (num > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return left < n ? left : -1;
    }

    public int[] findRightInterval1(int[][] intervals) {
        int n = intervals.length;
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            index.put(intervals[i][0], i);
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; ++i) {
            set.add(intervals[i][0]);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            Integer num = set.ceiling(intervals[i][1]);
            ans[i] = num == null ? -1 : index.get(num);
        }
        return ans;
    }

    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[][] startMap = new int[n][2];
        for (int i = 0; i < n; ++i) {
            startMap[i][0] = intervals[i][0];
            startMap[i][1] = i;
        }
        Arrays.sort(startMap, Comparator.comparingInt(a -> a[0]));
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int end = intervals[i][1];
            int left = 0, right = n - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                int num = startMap[mid][0];
                if (num < end) {
                    left = mid + 1;
                } else if (num > end) {
                    right = mid - 1;
                } else {
                    left = mid;
                    break;
                }
            }
            ans[i] = left >= n ? -1 : startMap[left][1];
        }
        return ans;
    }
}
