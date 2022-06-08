package com.shuangpeng.Problem.p0701_0800;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @Description: Problem0732MyCalendarIII（我的日程安排III）
 * @Date 2022/6/6 11:25 AM
 * @Version 1.0
 */
public class Problem0732MyCalendarIII {

    class MyCalendarThree {

        TreeMap<Integer, int[]> map;
        int ans = 0;

        public MyCalendarThree() {
            map = new TreeMap<>();
        }

        public int book(int start, int end) {
            Integer leftKey = map.floorKey(start);
            Integer rightKey = map.lowerKey(end);
            if (rightKey == null) {
                map.put(start, new int[]{end, 1});
                ans = Math.max(ans, 1);
                return ans;
            }
            if (leftKey == null) {
                leftKey = map.ceilingKey(start);
            }
            TreeSet<Integer> set = new TreeSet<>();
            for (Integer key = leftKey; key != null && key <= rightKey; key = map.higherKey(key)) {
                set.add(key);
            }
            for (int key : set) {
                int[] pair = map.get(key);
                int e = pair[0], c = pair[1];
                if (e > start) {
                    if (start < key) {
                        map.put(start, new int[]{key, 1});
                        start = key;
                        ans = Math.max(ans, 1);
                    } else if (key < start) {
                        pair[0] = start;
                        ans = Math.max(ans, c);
                    }
                    if (e <= end) {
                        map.put(start, new int[]{e, c + 1});
                    } else {
                        map.put(start, new int[]{end, c + 1});
                        map.put(end, new int[]{e, c});
                    }
                    ans = Math.max(ans, c + 1);
                    start = e;
                }
            }
            if (start < end) {
                map.put(start, new int[]{end, 1});
                ans = Math.max(ans, 1);
            }
            return ans;
        }
    }

//    public static void main(String[] args) {
//        MyCalendarThree a = new Problem0732MyCalendarIII().new MyCalendarThree();
//        int[][] arrays = {{47,50},{1,10},{27,36},{40,47},{20,27},{15,23},{10,18},{27,36},{17,25},{8,17},{24,33},{23,28},{21,27},{47,50},{14,21},{26,32},{16,21},{2,7},{24,33}};
//        for (int[] pair : arrays) {
//            a.book(pair[0], pair[1]);
//        }
//    }
}

class Problem0732MyCalendarIII0 {

    class MyCalendarThree {

        TreeMap<Integer, Integer> map;
        int ans = 0;

        public MyCalendarThree() {
            map = new TreeMap<>();
            ans = 0;
        }

        public int book(int start, int end) {
            map.put(start, map.floorKey(start) == null ? 0 : map.get(map.floorKey(start)));
            for (Integer key = start; key != null && key < end; key = map.higherKey(key)) {
                int sum = map.get(key) + 1;
                map.put(key, sum);
                ans = Math.max(ans, sum);
            }
            if (!map.containsKey(end)) {
                map.put(end, map.get(map.floorKey(end)) - 1);
            }
            return ans;
        }
    }
}

class Problem0732MyCalendarIII1 {

    class Node {
        int start, end;
        Node left, right;
        int max;
        boolean cover;

        Node(int s, int e, int c) {
            this.start = s;
            this.end = e;
            this.max = c;
            this.cover = true;
        }

        int add(int s, int e) {
            if (s <= this.start && e >= this.end && this.cover) {
                this.left = null;
                this.right = null;
                ++this.max;
                return this.max;
            }
            int mid = this.start + ((this.end - this.start) >> 1);
            if (this.left == null) {
                this.left = this.cover ? new Node(this.start, mid, this.max) : new Node(this.start, mid, 0);
            }
            if (this.right == null) {
                this.right = this.cover ? new Node(mid, this.end, this.max) : new Node(mid, this.end, 0);
            }
            if (e <= mid) {
                this.max = Math.max(this.max, this.left.add(s, e));
            } else if (s >= mid) {
                this.max = Math.max(this.max, this.right.add(s, e));
            } else {
                int m1 = this.left.add(s, mid);
                int m2 = this.right.add(mid, e);
                this.max = Math.max(this.max, Math.max(m1, m2));
            }
            this.cover = this.left.cover && this.right.cover
                    && this.max == this.left.max && this.max == this.right.max;
            return this.max;
        }
    }

    class MyCalendarThree {

        Node root;

        public MyCalendarThree() {
            root = new Node(0, (int) 1e9, 0);
        }

        public int book(int start, int end) {
            return root.add(start, end);
        }
    }
}

class Problem0732MyCalendarIII2 {

    class MyCalendarThree {

        Map<Integer, Integer> lazy, tree;

        public MyCalendarThree() {
            lazy = new HashMap<>();
            tree = new HashMap<>();
        }

        public int book(int start, int end) {
            update(start, end, 0, (int) 1e9, 1);
            return tree.getOrDefault(1, 0);
        }

        private void update(int start, int end, int left, int right, int idx) {
            if (end <= left || start >= right) {
                return;
            }
            if (start <= left && end >= right) {
                lazy.put(idx, lazy.getOrDefault(idx, 0) + 1);
                tree.put(idx, tree.getOrDefault(idx, 0) + 1);
            } else {
                int mid = left + ((right - left) >> 1);
                update(start, end, left, mid, idx << 1);
                update(start, end, mid, right, (idx << 1) + 1);
                tree.put(idx, lazy.getOrDefault(idx, 0) + Math.max(tree.getOrDefault(idx << 1, 0), tree.getOrDefault((idx << 1) + 1, 0)));
            }
        }
    }
}


class Problem0732MyCalendarIII3 {

    class Node {
        int start, end;
        Node left, right, inner;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    class MyCalendarThree {

        Node root;
        int ans;

        public MyCalendarThree() {
            root = new Node(0, 0);
            ans = 0;
        }

        public int book(int start, int end) {
            ans = Math.max(ans, update(root, start, end, 0, false));
            return ans;
        }

        private int update(Node node, int s, int e, int depth, boolean isInner) {
            if (isInner) {
                if (node.inner == null) {
                    node.inner = new Node(s, e);
                    return depth + 1;
                }
                node = node.inner;
            }
            while (true) {
                if (e <= node.start) {
                    if (node.left == null) {
                        node.left = new Node(s, e);
                        return depth + 1;
                    }
                    node = node.left;
                } else if (s >= node.end) {
                    if (node.right == null) {
                        node.right = new Node(s, e);
                        return depth + 1;
                    }
                    node = node.right;
                } else {
                    if (s < node.start) {
                        int count = update(node, s, node.start, depth, false);
                        if (e <= node.end) {
                            return Math.max(count, update(node, node.start, e, depth + 1, true));
                        } else {
                            return Math.max(count, Math.max(update(node, node.start, node.end, depth + 1, true), update(node, node.end, e, depth, false)));
                        }
                    } else {
                        if (e <= node.end) {
                            return update(node, s, e, depth + 1, true);
                        } else {
                            return Math.max(update(node, s, node.end, depth + 1, true), update(node, node.end, e, depth, false));
                        }
                    }
                }
            }
        }
    }
}

class Problem0732MyCalendarIII4 {

    class Node {
        int left, right, lazy, max;
    }

    class MyCalendarThree {

        static final int N = (int) 1e9, M = 4 * 400 * 30;

        Node[] tr = new Node[M];
        int cnt = 1;

        MyCalendarThree() {
            tr[1] = new Node();
            cnt = 1;
        }

        public int book(int start, int end) {
            update(1, 0, N, start, end);
            return tr[1].max;
        }

        private void update(int u, int start, int end, int s, int e) {
            if (s <= start && end <= e) {
                ++tr[u].lazy;
                ++tr[u].max;
                return;
            }
            lazyCreate(u);
            pushDown(u);
            int mid = start + end >> 1;
            if (s < mid) {
                update(tr[u].left, start, mid, s, e);
            }
            if (e > mid) {
                update(tr[u].right, mid, end, s, e);
            }
            pushUp(u);
        }

        private void lazyCreate(int u) {
            if (tr[u].left == 0) {
                tr[u].left = ++cnt;
                tr[tr[u].left] = new Node();
            }
            if (tr[u].right == 0) {
                tr[u].right = ++cnt;
                tr[tr[u].right] = new Node();
            }
        }

        private void pushDown(int u) {
            Node left = tr[tr[u].left], right = tr[tr[u].right];
            left.lazy += tr[u].lazy;
            right.lazy += tr[u].lazy;
            left.max += tr[u].lazy;
            right.max += tr[u].lazy;
            tr[u].lazy = 0;
        }

        private void pushUp(int u) {
            tr[u].max = Math.max(tr[tr[u].left].max, tr[tr[u].right].max);
        }
    }
}


