package com.shuangpeng.Problem.p1301_1400;

import java.util.Arrays;

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

    public int maxEvents(int[][] events) {
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
}
