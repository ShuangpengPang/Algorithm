package com.shuangpeng.Problem.p1301_1400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1353MaximumNumberOfEventsThatCanBeAttended（最多可以参加的会议数目）
 * @date 2023/8/7 12:04 PM
 */
public class Problem1353MaximumNumberOfEventsThatCanBeAttended {

    class Segment {
        int start, end, count;
        Segment left, right;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean add(int s, int e) {
            if (count == end - start + 1) {
                return false;
            }
            if (start == end) {
                count++;
                return true;
            }
            int mid = start + (end - start >> 1);
            if (left == null) {
                left = new Segment(start, mid);
            }
            if (right == null) {
                right = new Segment(mid + 1, end);
            }
            if (s <= mid && left.add(s, Math.min(e, mid))) {
                count++;
                return true;
            }
            if (e > mid && right.add(Math.max(s, mid + 1), e)) {
                count++;
                return true;
            }
            return false;
        }
    }

    public int maxEvents0(int[][] events) {
        Arrays.sort(events, (a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);
        Segment root = new Segment(0, events[events.length - 1][1]);
        int ans = 0;
        for (int[] e : events) {
            if (root.add(e[0], e[1])) {
                ans++;
            }
        }
        return ans;
    }

    public int maxEvents1(int[][] events) {
        int max = 0, n = events.length;
        for (int[] e : events) {
            max = Math.max(max, e[1]);
        }
        max += 2;
        List<Integer>[] in = new List[max], out = new List[max];
        for (int i = 0; i < max; i++) {
            in[i] = new ArrayList<>();
            out[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            in[events[i][0]].add(i);
            out[events[i][1] + 1].add(i);
        }
        boolean[] delete = new boolean[n];
        PriorityQueue<Integer> q = new PriorityQueue<>(n, Comparator.comparingInt(a -> events[a][1]));
        int ans = 0;
        for (int i = 0; i < max; i++) {
            for (int e : in[i]) {
                q.offer(e);
            }
            for (int e : out[i]) {
                delete[e] = true;
            }
            while (!q.isEmpty() && delete[q.peek()]) {
                q.poll();
            }
            if (!q.isEmpty()) {
                delete[q.poll()] = true;
                ans++;
            }
        }
        return ans;
    }

    public int maxEvents2(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        int n = events.length, time = 0, ans = 0;
        for (int i = 0; i < n || !q.isEmpty(); time++) {
            while (i < n && events[i][0] == time) {
                q.offer(events[i++][1]);
            }
            while (!q.isEmpty() && q.peek() < time) {
                q.poll();
            }
            if (!q.isEmpty()) {
                q.poll();
                ans++;
            }
        }
        return ans;
    }

    public int maxEvents3(int[][] events) {
        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));
        int n = events.length, M = 0;
        for (int[] e : events) {
            M = Math.max(M, e[1]);
        }
        PriorityQueue<Integer> q = new PriorityQueue<>(n, Comparator.comparingInt(a -> events[a][1]));
        int ans = 0;
        for (int i = 1, j = 0; i <= M; i++) {
            while (j < n && events[j][0] == i) {
                q.offer(j++);
            }
            while (!q.isEmpty() && events[q.peek()][1] < i) {
                q.poll();
            }
            if (!q.isEmpty()) {
                q.poll();
                ans++;
            }
        }
        return ans;
    }

    public int maxEvents(int[][] events) {
        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.comparingInt(a -> events[a][1]));
        int n = events.length, i = 0, ans = 0, maxEnd = 0;
        while (i < n) {
            int j = i;
            while (j < n && events[j][0] == events[i][0]) {
                maxEnd = Math.max(maxEnd, events[j][1]);
                q.offer(j++);
            }
            int end = j < n ? events[j][0] : maxEnd + 1;
            for (int d = events[i][0]; d < end && !q.isEmpty(); d++) {
                while (!q.isEmpty() && events[q.peek()][1] < d) {
                    q.poll();
                }
                if (!q.isEmpty()) {
                    q.poll();
                    ans++;
                }
            }
            i = j;
        }
        return ans;
    }
}
