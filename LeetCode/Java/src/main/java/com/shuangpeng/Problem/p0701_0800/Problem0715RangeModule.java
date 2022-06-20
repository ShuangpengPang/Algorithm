package com.shuangpeng.Problem.p0701_0800;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @Description: Problem0715RangeModule（range模块）
 * @Date 2022/6/20 10:00 AM
 * @Version 1.0
 */
public class Problem0715RangeModule {

    class RangeModule {

        class Node {
            int start, end;
            Node left, right;
            boolean cover;

            Node(int s, int e) {
                this.start = s;
                this.end = e;
            }

            int getMid() {
                return start + ((end - start) >> 1);
            }

            void add(int s, int e) {
                if (this.cover) {
                    return;
                }
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
                if (e <= mid) {
                    this.left.add(s, e);
                } else if (s >= mid) {
                    this.right.add(s, e);
                } else {
                    this.left.add(s, mid);
                    this.right.add(mid, e);
                }
                this.cover = this.left != null && this.right != null && this.left.cover && this.right.cover;
            }

            boolean query(int s, int e) {
                if (this.cover) {
                    return true;
                }
                if (s <= this.start && e >= this.end) {
                    return false;
                }
                int mid = getMid();
                if (s < mid && this.left == null) {
                    return false;
                } else if (e > mid && this.right == null) {
                    return false;
                }
                if (e <= mid) {
                    return this.left.query(s, e);
                } else if (s >= mid) {
                    return this.right.query(s, e);
                } else {
                    return this.left.query(s, mid) && this.right.query(mid, e);
                }
            }

            void remove(int s, int e) {
                int mid = getMid();
                if (this.cover) {
                    if (this.left == null) {
                        this.left = new Node(this.start, mid);
                    }
                    if (this.right == null) {
                        this.right = new Node(mid, this.end);
                    }
                    this.left.cover = true;
                    this.right.cover = true;
                }
                if (s <= this.start && e >= mid) {
                    this.left = null;
                }
                if (s <= mid && e >= this.end) {
                    this.right = null;
                }
                if (e <= mid && this.left != null) {
                    this.left.remove(s, e);
                } else if (s >= mid && this.right != null) {
                    this.right.remove(s, e);
                } else if (s < mid && e > mid) {
                    if (this.left != null) {
                        this.left.cover |= this.cover;
                        this.left.remove(s, mid);
                    }
                    if (this.right != null) {
                        this.right.remove(mid, e);
                    }
                }
                this.cover = false;
            }
        }

        Node root;

        public RangeModule() {
            root = new Node(1, (int) 1e9);
        }

        public void addRange(int left, int right) {
            root.add(left, right);
        }

        public boolean queryRange(int left, int right) {
            return root.query(left, right);
        }

        public void removeRange(int left, int right) {
            root.remove(left, right);
        }
    }
}

class RangeModule {

    TreeMap<Integer, Integer> map;

    public RangeModule() {
        map = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        Integer start = map.floorKey(left);
        Integer end = map.floorKey(right);
        if (start == null) {
            start = map.higherKey(left);
        }
        if (end == null) {
            map.put(left, right);
            return;
        }
        List<Integer> list = new ArrayList<>();
        for (Integer i = start; i != null && i <= end; i = map.higherKey(i)) {
            list.add(i);
        }
        for (int i : list) {
            int value = map.get(i);
            if (value < left) {
                continue;
            }
            left = Math.min(left, i);
            right = Math.max(right, value);
        }
        for (int i : list) {
            if (i >= left) {
                map.remove(i);
            }
        }
        map.put(left, right);
    }

    public boolean queryRange(int left, int right) {
        Integer key = map.floorKey(left);
        return key != null && map.get(key) >= right;
    }

    public void removeRange(int left, int right) {
        Integer leftKey = map.floorKey(left);
        Integer rightKey = map.lowerKey(right);
        if (rightKey == null) {
            return;
        }
        if (leftKey == null) {
            leftKey = map.higherKey(left);
        }
        List<Integer> list = new ArrayList<>();
        for (Integer i = leftKey; i != null && i <= rightKey; i = map.higherKey(i)) {
            list.add(i);
        }
        for (int i : list) {
            int value = map.get(i);
            if (value <= left) {
                continue;
            }
            if (i < left) {
                map.put(i, left);
            } else {
                map.remove(i);
            }
            if (value > right) {
                map.put(right, value);
            }
        }
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
