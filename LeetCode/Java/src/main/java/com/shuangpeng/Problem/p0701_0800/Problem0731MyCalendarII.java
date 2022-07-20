package com.shuangpeng.Problem.p0701_0800;

import java.util.*;

/**
 * @Description: Problem0731MyCalendarII（我的日程安排表II）
 * @Date 2022/7/19 10:37 AM
 * @Version 1.0
 */
public class Problem0731MyCalendarII {

    class MyCalendarTwo {

        TreeMap<Integer, int[]> map;

        public MyCalendarTwo() {
            map = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            Map.Entry<Integer, int[]> startEntry = map.floorEntry(start);
            if (startEntry == null) {
                startEntry = map.higherEntry(start);
            }
            if (startEntry == null || startEntry.getKey() >= end) {
                map.put(start, new int[]{end, 1});
                return true;
            }
            Map.Entry<Integer, int[]> endEntry = map.lowerEntry(end);
            if (endEntry.getValue()[0] <= start) {
                map.put(start, new int[]{end, 1});
                return true;
            }
            List<Integer> keys = new ArrayList<>();
            for (Map.Entry<Integer, int[]> entry = startEntry; entry != null && entry.getKey() <= endEntry.getKey(); entry = map.higherEntry(entry.getKey())) {
                if (entry.getValue()[0] > start) {
                    keys.add(entry.getKey());
                }
            }
            for (int key : keys) {
                int[] value = map.get(key);
                int e = value[0], c = value[1];
                if (c == 2) {
                    return false;
                }
            }
            for (int key : keys) {
                int[] value = map.get(key);
                int e = value[0], c = value[1];
                map.remove(key);
                if (key < start) {
                    map.put(key, new int[]{start, c});
                } else if (start < key) {
                    map.put(start, new int[]{key, 1});
                    start = key;
                }
                int e1 = Math.min(e, end);
                map.put(start, new int[]{e1, c + 1});
                start = e1;
                if (key == endEntry.getKey()) {
                    if (start < end) {
                        map.put(start, new int[]{end, 1});
                    } else if (start < e) {
                        map.put(start, new int[]{e, c});
                    }
                }
            }
            return true;
        }
    }

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */
}

class Problem0731MyCalendarII0 {

    class MyCalendarTwo {

        class Node {
            int start, end, lazy, count;
            Node left, right;

            Node(int s, int e) {
                start = s;
                end = e;
            }

            int getMid() {
                return start + (end - start >> 1);
            }

            boolean check(int s, int e) {
                if (s <= start && end <= e) {
                    return lazy + count < 2;
                }
                int mid = getMid();
                if (left == null) {
                    left = new Node(start, mid);
                }
                if (right == null) {
                    right = new Node(mid, end);
                }
                if (lazy > 0) {
                    left.lazy += lazy;
                    right.lazy += lazy;
                    count += lazy;
                    lazy = 0;
                }
                boolean ans = true;
                if (s < mid) {
                    ans = left.check(s, Math.min(mid, e));
                    if (!ans) {
                        return false;
                    }
                }
                if (e > mid) {
                    ans = right.check(Math.max(s, mid), e);
                }
                return ans;
            }

            void update(int s, int e) {
                if (s <= start && end <= e) {
                    lazy++;
                    return;
                }
                int mid = getMid();
                if (left == null) {
                    left = new Node(start, mid);
                }
                if (right == null) {
                    right = new Node(mid, end);
                }
                if (lazy > 0) {
                    left.lazy += lazy;
                    right.lazy += lazy;
                    lazy = 0;
                }
                if (s < mid) {
                    left.update(s, Math.min(mid, e));
                }
                if (e > mid) {
                    right.update(Math.max(mid, s), e);
                }
                count = Math.max(left.lazy + left.count, right.lazy + right.count);
            }
        }

        Node root;

        public MyCalendarTwo() {
            root = new Node(0, (int) 1e9);
        }

        public boolean book(int start, int end) {
            if (!root.check(start, end)) {
                return false;
            }
            root.update(start, end);
            return true;
        }
    }
}

class Problem0731MyCalendarII1 {

    class MyCalendarTwo {

        List<int[]> books;
        List<int[]> overlaps;

        public MyCalendarTwo() {
            books = new ArrayList<>();
            overlaps = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            for (int[] p : overlaps) {
                if (p[0] < end && p[1] > start) {
                    return false;
                }
            }
            for (int[] b : books) {
                int l = b[0], r = b[1];
                if (l < end && r > start) {
                    overlaps.add(new int[]{Math.max(l, start), Math.min(end, r)});
                }
            }
            books.add(new int[]{start, end});
            return true;
        }
    }
}

class Problem0731MyCalendarII2 {

    class MyCalendarTwo {

        List<int[]> diff;

        public MyCalendarTwo() {
            diff = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            int sum = 0;
            int n = diff.size();
            for (int i = 0; i < n; i++) {
                int[] arr = diff.get(i);
                if (arr[0] >= end) {
                    break;
                }
                sum += arr[1];
                if (arr[0] >= start || (i < n - 1 && diff.get(i + 1)[0] > start)) {
                    if (sum == 2) {
                        return false;
                    }
                }
            }
            int i = 0;
            while (i < n && diff.get(i)[0] < start) {
                i++;
            }
            if (i == n) {
                diff.add(new int[]{start, 1});
                diff.add(new int[]{end, -1});
                return true;
            }
            if (diff.get(i)[0] == start) {
                diff.get(i)[1]++;
            } else {
                diff.add(i, new int[]{start, 1});
            }
            n = diff.size();
            while (i < n && diff.get(i)[0] < end) {
                i++;
            }
            if (i == n) {
                diff.add(new int[]{end, -1});
                return true;
            }
            if (diff.get(i)[0] == end) {
                diff.get(i)[1]--;
            } else {
                diff.add(i, new int[]{end, -1});
            }
            return true;
        }
    }
}

class Problem0731MyCalendarII3 {

    class MyCalendarTwo {

        TreeMap<Integer, Integer> diff;

        public MyCalendarTwo() {
            diff = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            diff.put(start, diff.getOrDefault(start, 0) + 1);
            diff.put(end, diff.getOrDefault(end, 0) - 1);
            int sum = 0;
            for (int cnt : diff.values()) {
                sum += cnt;
                if (sum > 2) {
                    diff.put(start, diff.get(start) - 1);
                    diff.put(end, diff.get(end) + 1);
                    return false;
                }
            }
            return true;
        }
    }
}

class Problem0731MyCalendarII4 {

    class MyCalendarTwo {

        static final int M = (int) 1e9;

        Map<Integer, int[]> tree;

        public MyCalendarTwo() {
            tree = new HashMap<>();
        }

        public boolean book(int start, int end) {
            tree.putIfAbsent(1, new int[]{0, 0});
            update(start, end, 0, M, 1, 1);
            if (tree.get(1)[0] > 2) {
                update(start, end, 0, M, -1, 1);
                return false;
            }
            return true;
        }

        private void update(int start, int end, int l, int r, int val, int idx) {
            if (end <= l || start >= r) {
                return;
            }
            tree.putIfAbsent(idx, new int[]{0, 0});
            if (start <= l && r <= end) {
                tree.get(idx)[0] += val;
                tree.get(idx)[1] += val;
                return;
            }
            int m = l + (r - l >> 1);
            update(start, end, l, m, val, idx << 1);
            update(start, end, m, r, val, (idx << 1) + 1);
            tree.putIfAbsent(idx << 1, new int[]{0, 0});
            tree.putIfAbsent((idx << 1) + 1, new int[]{0, 0});
            tree.get(idx)[0] = tree.get(idx)[1] + Math.max(tree.get(idx << 1)[0], tree.get((idx << 1) + 1)[0]);
        }
    }
}

class Problem0731MyCalendarII5 {

    class MyCalendarTwo {

        static final int M = (int) 1e9;

        Map<Integer, int[]> tree;

        public MyCalendarTwo() {
            tree = new HashMap<>();
        }

        public boolean book(int start, int end) {
            tree.putIfAbsent(1, new int[]{0, 0});
            update(start, end, 0, M, 1, 1);
            if (tree.get(1)[0] > 2) {
                update(start, end, 0, M, -1, 1);
                return false;
            }
            return true;
        }

        private void update(int start, int end, int l, int r, int val, int idx) {
            if (end <= l || start >= r) {
                return;
            }
            tree.putIfAbsent(idx, new int[]{0, 0});
            if (start <= l && r <= end) {
                tree.get(idx)[0] += val;
                tree.get(idx)[1] += val;
                return;
            }
            int m = l + (r - l >> 1);
            update(start, end, l, m, val, idx << 1);
            update(start, end, m, r, val, (idx << 1) + 1);
            tree.putIfAbsent(idx << 1, new int[]{0, 0});
            tree.putIfAbsent((idx << 1) + 1, new int[]{0, 0});
            tree.get(idx)[0] = tree.get(idx)[1] + Math.max(tree.get(idx << 1)[0], tree.get((idx << 1) + 1)[0]);
        }
    }
}

class Problem0731MyCalendarII6 {

    class MyCalendarTwo {

        class Node {
            int l, r, count;
            Node left, right;
        }

        Node root;

        public MyCalendarTwo() {
            root = new Node();
        }

        public boolean book(int start, int end) {
            return update(root, start, end);
        }

        private boolean update(Node node, int l, int r) {
            if (l == r) {
                return true;
            }
            if (node.count == 0) {
                node.l = l;
                node.r = r;
                node.count = 1;
                node.left = new Node();
                node.right = new Node();
                return true;
            }
            if (r <= node.l) {
                return update(node.left, l, r);
            } else if (l >= node.r) {
                return update(node.right, l, r);
            } else if (node.count == 2) {
                return false;
            }
            if (!isValid(node, l, r)) {
                return false;
            }
            int lMin = Math.min(node.l, l), lMax = Math.max(node.l, l);
            int rMin = Math.min(node.r, r), rMax = Math.max(node.r, r);
            node.count++;
            node.l = lMax;
            node.r = rMin;
            update(node.left, lMin, lMax);
            update(node.right, rMin, rMax);
            return true;
        }

        private boolean isValid(Node node, int l, int r) {
            if (node == null || node.count == 0) {
                return true;
            }
            if (r <= node.l) {
                return isValid(node.left, l, r);
            } else if (l >= node.r) {
                return isValid(node.right, l, r);
            } else if (node.count == 2) {
                return false;
            }
            return isValid(node.left, Math.min(node.l, l), Math.max(node.l, l))
                    && isValid(node.right, Math.min(node.r, r), Math.max(node.r, r));
        }
    }
}

class MyCalendarTwo {

    class Node {
        int max, lazy;
        Node left, right;

        void update(int start, int end, int l, int r, int v) {
            if (l <= start && end <= r) {
                max += v;
                lazy += v;
                return;
            }
            if (left == null) {
                left = new Node();
                right = new Node();
            }
            if (lazy > 0) {
                left.lazy += lazy;
                left.max += lazy;
                right.lazy += lazy;
                right.max += lazy;
                lazy = 0;
            }
            int mid = start + (end - start >> 1);
            if (l < mid) {
                left.update(start, mid, l, Math.min(r, mid), v);
            }
            if (r > mid) {
                right.update(mid, end, Math.max(mid, l), r, v);
            }
            max = Math.max(left.max, right.max);
        }

        boolean query(int start, int end, int l, int r) {
            if (l <= start && end <= r) {
                return max < 2;
            }
            if (left == null) {
                left = new Node();
                right = new Node();
            }
            if (lazy > 0) {
                left.max += lazy;
                left.lazy += lazy;
                right.max += lazy;
                right.lazy += lazy;
                lazy = 0;
            }
            int mid = start + (end - start >> 1);
            if (l < mid && !left.query(start, mid, l, Math.min(mid, r))) {
                return false;
            }
            if (r > mid && !right.query(mid, end, Math.max(mid, l), r)) {
                return false;
            }
            return true;
        }
    }

    static final int M = (int) 1e9;

    Node root = new Node();

    public boolean book(int start, int end) {
        if (!root.query(0, M, start, end)) {
            return false;
        }
        root.update(0, M, start, end, 1);
        return true;
    }
}



