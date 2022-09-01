package com.shuangpeng.competition.第290到300场周赛.第293场周赛;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Description: Problem2276CountIntegersInIntervals（统计区间中的整数数目）
 * @Date 2022/6/25 3:11 PM
 * @Version 1.0
 */
public class Problem2276CountIntegersInIntervals {

    // 比赛时写法
    class CountIntervals {

        class Interval {
            int start, end;
            Interval left, right;
            int count = 0;
            boolean cover = false;

            public Interval(int start, int end, boolean cover) {
                this.start = start;
                this.end = end;
                this.cover = cover;
                if (cover) {
                    this.count = end - start + 1;
                } else {
                    this.count = 0;
                }
            }

            public int getMid() {
                return start + ((end - start) >> 1);
            }

            public void add(int s, int e) {
                if (this.cover) {
                    return;
                }
                if (s <= this.start && e >= this.end) {
                    this.cover = true;
                    this.count = this.end - this.start + 1;
                    return;
                }
                int mid = getMid();
                if (s <= mid && this.left == null) {
                    this.left = new Interval(this.start, mid, false);
                }
                if (e > mid && this.right == null) {
                    this.right = new Interval(mid + 1, this.end, false);
                }
                if (e <= mid) {
                    this.left.add(s, e);
                } else if (s > mid) {
                    this.right.add(s, e);
                } else {
                    this.left.add(s, mid);
                    this.right.add(mid + 1, e);
                }
                this.cover = this.left != null && this.right != null && this.left.cover && this.right.cover;
                this.count = (this.left == null ? 0 : this.left.count) + (this.right == null ? 0 : this.right.count);
            }
        }

        Interval root;

        public CountIntervals() {
            root = new Interval(1, (int) 1e9, false);
        }

        public void add(int left, int right) {
            root.add(left, right);
        }

        public int count() {
            return root.count;
        }
    }
}

class Problem2276CountIntegersInIntervals0 {

    class CountIntervals {

        class Node {
            int start, end;
            int count;
            boolean cover;
            Node left, right;

            Node(int s, int e) {
                this.start = s;
                this.end = e;
            }

            int getMid() {
                return this.start + ((this.end - this.start) >> 1);
            }

            void add(int s, int e) {
                if (e < this.start || s > this.end || this.cover) {
                    return;
                }
                if (s <= this.start && this.end <= e) {
                    this.cover = true;
                    this.count = this.end - this.start + 1;
                    return;
                }
                int mid = getMid();
                if (s <= mid && this.left == null) {
                    this.left = new Node(this.start, mid);
                }
                if (e > mid && this.right == null) {
                    this.right = new Node(mid + 1, this.end);
                }
                if (s <= mid) {
                    this.left.add(s, e);
                }
                if (e > mid) {
                    this.right.add(s, e);
                }
                this.count = (this.left == null ? 0 : this.left.count) + (this.right == null ? 0 : this.right.count);
                this.cover = this.left != null && this.right != null && this.left.cover && this.right.cover;
            }
        }

        Node root;

        public CountIntervals() {
            root = new Node(1, (int) 1e9);
        }

        public void add(int left, int right) {
            root.add(left, right);
        }

        public int count() {
            return root.count;
        }
    }
}

class CountIntervals {

    int count;
    TreeMap<Integer, Integer> map;

    public CountIntervals() {
        count = 0;
        map = new TreeMap<>();
    }

    public void add(int left, int right) {
        Map.Entry<Integer, Integer> end = map.floorEntry(right + 1);
        if (end == null) {
            map.put(left, right);
            count += right - left + 1;
            return;
        }
        Map.Entry<Integer, Integer> start = map.floorEntry(left);
        if (start == null) {
            start = map.higherEntry(left);
        }
        int oldCount = 0;
        for (Map.Entry<Integer, Integer> entry = start; entry != null && entry.getKey() <= end.getKey(); entry = map.higherEntry(entry.getKey())) {
            int s = entry.getKey(), e = entry.getValue();
            if (e < left - 1) {
                continue;
            }
            map.remove(s);
            oldCount += e - s + 1;
            left = Math.min(left, s);
            right = Math.max(right, e);
        }
        map.put(left, right);
        count += right - left - oldCount + 1;
    }

    public int count() {
        return count;
    }
}

/**
 * Your CountIntervals object will be instantiated and called as such:
 * CountIntervals obj = new CountIntervals();
 * obj.add(left,right);
 * int param_2 = obj.count();
 */
