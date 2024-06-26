package com.shuangpeng.lcr.p001_100;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR058MyCalendar（我的日程安排表I）
 * @date 2024/6/25 4:39 PM
 */
public class LCR058MyCalendar {

    class MyCalendar {

        private TreeMap<Integer, Integer> treeMap;

        public MyCalendar() {
            treeMap = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            Map.Entry<Integer, Integer> lowerEntry = treeMap.lowerEntry(end);
            if (lowerEntry != null && lowerEntry.getValue() > start) {
                return false;
            }
            treeMap.put(start, end);
            return true;
        }
    }

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
}

class LCR058MyCalendar0 {

    class MyCalendar {

        class SegmentTree {
            public int start, end;
            public SegmentTree left, right;
            public boolean cover, contains;

            public SegmentTree(int start, int end) {
                this.start = start;
                this.end = end;
                cover = contains = false;
            }

            public boolean valid(int s, int e) {
                if (!contains) {
                    return true;
                }
                if (cover || s <= start && end <= e) {
                    return false;
                }
                int mid = start + end >> 1;
                split(mid);
                if (e <= mid) {
                    return left.valid(s, e);
                } else if (s >= mid) {
                    return right.valid(s, e);
                } else {
                    return left.valid(s, mid) && right.valid(mid, e);
                }
            }

            public void add(int s, int e) {
                contains = true;
                if (s <= start && end <= e) {
                    cover = true;
                    return;
                }
                int mid = start + end >> 1;
                split(mid);
                if (e <= mid) {
                    left.add(s, e);
                } else if (s >= mid) {
                    right.add(s, e);
                } else {
                    left.add(s, mid);
                    right.add(mid, e);
                }
                cover = left.cover && right.cover;
            }

            public void split(int mid) {
                if (left == null) {
                    left = new SegmentTree(start, mid);
                }
                if (right == null) {
                    right = new SegmentTree(mid, end);
                }
            }
        }

        private SegmentTree root;

        public MyCalendar() {
            root = new SegmentTree(0, (int) 1e9);
        }

        public boolean book(int start, int end) {
            if (!root.valid(start, end)) {
                return false;
            }
            root.add(start, end);
            return true;
        }
    }
}
