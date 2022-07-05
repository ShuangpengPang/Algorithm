package com.shuangpeng.Problem.p0701_0800;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Description: Problem0729MyCalendarI（我的日程安排表I）
 * @Date 2022/7/5 9:53 AM
 * @Version 1.0
 */
public class Problem0729MyCalendarI {

    class MyCalendar {

        class Node {
            int start, end;
            Node left, right;
            boolean cover;

            Node(int s, int e) {
                this.start = s;
                this.end = e;
            }

            boolean query(int s, int e) {
                if (this.cover) {
                    return false;
                }
                int mid = getMid();
                if (s < mid) {
                    if (this.left != null && !this.left.query(s, e)) {
                        return false;
                    }
                }
                if (e > mid) {
                    if (this.right != null && !this.right.query(s, e)) {
                        return false;
                    }
                }
                return true;
            }

            void update(int s, int e) {
                if (s <= this.start && e >= this.end) {
                    this.cover = true;
                    return;
                }
                int mid = getMid();
                if (s < mid && this.left == null) {
                    this.left = new Node(this.start, mid);
                }
                if (e > mid && this.right == null) {
                    this.right = new Node(mid, this.end);
                }
                if (s < mid) {
                    this.left.update(s, e);
                }
                if (e > mid) {
                    this.right.update(s, e);
                }
                this.cover = this.left != null && this.right != null && this.left.cover && this.right.cover;
            }

            int getMid() {
                return start + ((end - start) >> 1);
            }
        }

        Node root;

        public MyCalendar() {
            root = new Node(0, (int) 1e9);
        }

        public boolean book(int start, int end) {
            if (!root.query(start, end)) {
                return false;
            }
            root.update(start, end);
            return true;
        }
    }

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
}

class MyCalendar {

    TreeMap<Integer, Integer> map;

    public MyCalendar() {
        map = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> endEntry = map.floorEntry(end);
        if (endEntry == null) {
            map.put(start, end);
            return true;
        }
        Map.Entry<Integer, Integer> startEntry = map.floorEntry(start);
        if (startEntry == null) {
            startEntry = map.higherEntry(start);
        }
        for (Map.Entry<Integer, Integer> entry = startEntry; entry != null && entry.getKey() <= endEntry.getKey(); entry = map.higherEntry(entry.getKey())) {
            int s = entry.getKey(), e = entry.getValue();
            if (s < end && e > start) {
                return false;
            }
        }
        for (Map.Entry<Integer, Integer> entry = startEntry; entry != null && entry.getKey() <= endEntry.getKey(); entry = map.higherEntry(entry.getKey())) {
            int s = entry.getKey(), e = entry.getValue();
            if (e == start || s == end) {
                map.remove(s);
                start = Math.min(start, s);
                end = Math.max(end, e);
            }
        }
        map.put(start, end);
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */