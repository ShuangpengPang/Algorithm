package com.shuangpeng.Problem.p0701_0800;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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

class Problem0729MyCalendarI0 {

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
}

class Problem0729MyCalendarI1 {

    class MyCalendar {

        TreeMap<Integer, Integer> map;

        public MyCalendar() {
            map = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            Map.Entry<Integer, Integer> lower = map.lowerEntry(end);
            if (lower != null && lower.getValue() > start) {
                return false;
            }
            if (lower != null && lower.getValue() == start) {
                start = lower.getKey();
                map.remove(start);
            }
            if (map.containsKey(end)) {
                int e = map.get(end);
                map.remove(end);
                end = e;
            }
            map.put(start, end);
            return true;
        }
    }
}

class Problem0729MyCalendarI2 {

    class MyCalendar {
        Set<Integer> tree;
        Set<Integer> lazy;

        public MyCalendar() {
            tree = new HashSet<Integer>();
            lazy = new HashSet<Integer>();
        }

        public boolean book(int start, int end) {
            if (query(start, end - 1, 0, 1000000000, 1)) {
                return false;
            }
            update(start, end - 1, 0, 1000000000, 1);
            return true;
        }

        public boolean query(int start, int end, int l, int r, int idx) {
            if (start > r || end < l) {
                return false;
            }
            /* 如果该区间已被预订，则直接返回 */
            if (lazy.contains(idx)) {
                return true;
            }
            if (start <= l && r <= end) {
                return tree.contains(idx);
            } else {
                int mid = (l + r) >> 1;
                if (end <= mid) {
                    return query(start, end, l, mid, 2 * idx);
                } else if (start > mid) {
                    return query(start, end, mid + 1, r, 2 * idx + 1);
                } else {
                    return query(start, end, l, mid, 2 * idx) | query(start, end, mid + 1, r, 2 * idx + 1);
                }
            }
        }

        public void update(int start, int end, int l, int r, int idx) {
            if (r < start || end < l) {
                return;
            }
            if (start <= l && r <= end) {
                tree.add(idx);
                lazy.add(idx);
            } else {
                int mid = (l + r) >> 1;
                update(start, end, l, mid, 2 * idx);
                update(start, end, mid + 1, r, 2 * idx + 1);
                tree.add(idx);
            }
        }
    }
}

class Problem0729MyCalendarI3 {

    class MyCalendar {

        class TreeNode{
            private int start, end;
            private TreeNode left, right;
            public TreeNode(int s, int e){
                start = s;
                end = e;
            }
        }
        TreeNode root;

        public MyCalendar() {}

        public boolean book(int start, int end) {
            if(root == null){
                root = new TreeNode(start, end);
                return true;
            }
            TreeNode cur = root;
            while(true){
                //插入左子树
                if(end <= cur.start){
                    if(cur.left != null){
                        cur = cur.left;
                        continue;
                    }else{
                        cur.left = new TreeNode(start, end);
                        return true;
                    }
                }
                //插入右子树
                if(start >= cur.end){
                    if(cur.right != null){
                        cur = cur.right;
                        continue;
                    }else{
                        cur.right = new TreeNode(start, end);
                        return true;
                    }
                }
                return false;
            }
        }
    }
}

