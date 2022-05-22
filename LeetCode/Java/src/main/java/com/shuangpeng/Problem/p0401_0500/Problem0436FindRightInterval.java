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

    public int[] findRightInterval2(int[][] intervals) {
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

    public int[] findRightInterval3(int[][] intervals) {
        int n = intervals.length;
        int[][] startMap = new int[n][2];
        int[][] endMap = new int[n][2];
        for (int i = 0; i < n; ++i) {
            startMap[i][0] = intervals[i][0];
            startMap[i][1] = i;
            endMap[i][0] = intervals[i][1];
            endMap[i][1] = i;
        }
        Arrays.sort(startMap, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(endMap, Comparator.comparingInt(a -> a[0]));
        int[] ans = new int[n];
        for (int i = 0, j = 0; i < n; ++i) {
            while (j < n && startMap[j][0] < endMap[i][0]) {
                ++j;
            }
            ans[endMap[i][1]] = j < n ? startMap[j][1] : -1;
        }
        return ans;
    }

    public int[] findRightInterval4(int[][] intervals) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int[] p : intervals) {
            min = Math.min(min, p[0]);
            max = Math.max(max, p[1]);
        }
        int n = intervals.length, m = max - min + 1, delta = -min;
        int[] next = new int[m];
        for (int i = 0; i < n; ++i) {
            next[intervals[i][0] + delta] = i + 1;
        }
        for (int i = m - 1, t = -1; i >= 0; --i) {
            if (next[i] > 0) {
                t = next[i] - 1;
            }
            next[i] = t;
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = next[intervals[i][1] + delta];
        }
        return ans;
    }

    public int[] findRightInterval(int[][] intervals) {
        int min=1_000_000,max=-1_000_000;
        for (int[]p:intervals){
            min = Math.min(min,p[0]);
            max = Math.max(max,p[1]);
        }

        int delta = -min;
        int m = max-min+2;
        int temp;
        int n = intervals.length;
        int[] ret = new int[n];
        int[] next = new int [m];
        for (temp=0;temp<n;++temp){
            next[intervals[temp][0]+delta] = temp+1;
        }
        for (temp=-1;--m>=0;){
            if (next[m] >0) temp=next[m]-1;
            next[m]=temp;
        }
        while(--n>=0){
            ret[n] = next[intervals[n][1]+delta];
        }
        return ret;
    }
}
