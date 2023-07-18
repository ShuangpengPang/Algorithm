package com.shuangpeng.Problem.p1801_1900;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @Description: Problem1851MinimumIntervalToIncludeEachQuery（包含每个查询的最小区间）
 * @Date 2022/10/20 2:43 PM
 * @Version 1.0
 */
public class Problem1851MinimumIntervalToIncludeEachQuery {

    class Node {
        int start, end, val = -1;
        Node left, right;

        Node(int s, int e) {
            start = s;
            end = e;
        }

        int getMid() {
            return start + (end - start >> 1);
        }

        void add(int s, int e, int idx) {
            if (s <= start && e >= end) {
                val = idx;
                return;
            }
            int mid = getMid();
            if (left == null) {
                left = new Node(start, mid);
            }
            if (right == null) {
                right = new Node(mid, end);
            }
            if (val != -1) {
                left.val = val;
                right.val = val;
                val = -1;
            }
            if (s < mid) {
                left.add(s, Math.min(mid, e), idx);
            }
            if (e > mid) {
                right.add(Math.max(s, mid), e, idx);
            }
        }

        int query(int num) {
            if (val != -1 || num == start && start + 1 == end) {
                return val;
            }
            int mid = getMid();
            if (num < mid) {
                return left == null ? -1 : left.query(num);
            }
            return right == null ? -1 : right.query(num);
        }
    }

    public int[] minInterval0(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));
        int m = intervals.length, n = queries.length;
        Node root = new Node(1, (int) 1e7 + 1);
        for (int i = 0; i < m; i++) {
            root.add(intervals[i][0], intervals[i][1] + 1, i);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int j = root.query(queries[i]);
            if (j != -1) {
                ans[i] = intervals[j][1] - intervals[j][0] + 1;
            } else {
                ans[i] = -1;
            }
        }
        return ans;
    }

    public int[] minInterval1(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));
        TreeMap<Integer, int[]> map = new TreeMap<>();
        int m = intervals.length, n = queries.length;
        for (int i = 0; i < m; i++) {
            int left = intervals[i][0], right = intervals[i][1], len = right - left + 1;
            Map.Entry<Integer, int[]> startEntry = map.floorEntry(left);
            startEntry = startEntry == null ? map.ceilingEntry(left) : startEntry;
            for (Map.Entry<Integer, int[]> entry = startEntry; entry != null && entry.getKey() <= right; entry = map.higherEntry(entry.getKey())) {
                int start = entry.getKey(), end = entry.getValue()[0], prevLength = entry.getValue()[1];
                if (end < left) {
                    continue;
                }
                if (start < left) {
                    entry.getValue()[0] = left - 1;
                    if (end > right) {
                        map.put(right + 1, new int[]{end, prevLength});
                        break;
                    }
                } else if (end <= right) {
                    map.remove(start);
                } else {
                    map.remove(start);
                    map.put(right + 1, new int[]{end, prevLength});
                    break;
                }
            }
            map.put(left, new int[]{right, len});
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            Map.Entry<Integer, int[]> entry = map.floorEntry(queries[i]);
            ans[i] = entry == null || entry.getValue()[0] < queries[i] ? -1 : entry.getValue()[1];
        }
        return ans;
    }

    public int[] minInterval2(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int m = intervals.length, n = queries.length;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, Comparator.comparingInt(i -> queries[i]));
        int[] ans = new int[n];
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[1] - a[0]));
        for (int i = 0, j = 0; i < n; i++) {
            int id = ids[i], num = queries[id];
            while (j < m && intervals[j][0] <= num) {
                if (intervals[j][1] >= num) {
                    q.offer(intervals[j]);
                }
                j++;
            }
            while (!q.isEmpty() && q.peek()[1] < num) {
                q.poll();
            }
            ans[id] = q.isEmpty() ? -1 : q.peek()[1] - q.peek()[0] + 1;
        }
        return ans;
    }

    public int[] minInterval(int[][] intervals, int[] queries) {
        int m = intervals.length, n = queries.length;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, Comparator.comparingInt(a -> queries[a]));
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[1] - a[0]));
        int[] ans = new int[n];
        for (int i = 0, j = 0; i < n; i++) {
            int id = ids[i], num = queries[id];
            while (j < m && intervals[j][0] <= num) {
                q.offer(intervals[j++]);
            }
            while (!q.isEmpty() && q.peek()[1] < num) {
                q.poll();
            }
            ans[id] = q.isEmpty() ? -1 : q.peek()[1] - q.peek()[0] + 1;
        }
        return ans;
    }
}
