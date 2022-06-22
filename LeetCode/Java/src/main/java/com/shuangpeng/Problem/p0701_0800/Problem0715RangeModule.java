package com.shuangpeng.Problem.p0701_0800;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

class Problem0715RangeModule0 {

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
}

class Problem0715RangeModule1 {

    class RangeModule {
        TreeMap<Integer, Integer> intervals;

        public RangeModule() {
            intervals = new TreeMap<>();
        }

        public void addRange(int left, int right) {
            Map.Entry<Integer, Integer> leftEntry = intervals.floorEntry(left);
            leftEntry = leftEntry == null ? intervals.higherEntry(left) : leftEntry;
            if (leftEntry == null || leftEntry.getKey() > right) {
                intervals.put(left, right);
                return;
            }
            for (Map.Entry<Integer, Integer> entry = leftEntry; entry != null && entry.getKey() <= right; entry = intervals.higherEntry(entry.getKey())) {
                int key = entry.getKey(), value = entry.getValue();
                if (value < left) {
                    continue;
                }
                left = Math.min(left, key);
                right = Math.max(right, entry.getValue());
                intervals.remove(entry.getKey());
            }
            intervals.put(left, right);
        }

        public boolean queryRange(int left, int right) {
            Map.Entry<Integer, Integer> entry = intervals.floorEntry(left);
            return entry != null && entry.getValue() >= right;
        }

        public void removeRange(int left, int right) {
            Map.Entry<Integer, Integer> leftEntry = intervals.floorEntry(left);
            if (leftEntry == null) {
                leftEntry = intervals.higherEntry(left);
            }
            if (leftEntry == null || leftEntry.getKey() >= right) {
                return;
            }
            for (Map.Entry<Integer, Integer> entry = leftEntry; entry != null && entry.getKey() < right; entry = intervals.higherEntry(entry.getKey())) {
                int key = entry.getKey(), value = entry.getValue();
                if (value <= left) {
                    continue;
                }
                if (key < left) {
                    intervals.put(key, left);
                } else {
                    intervals.remove(key);
                }
                if (value > right) {
                    intervals.put(right, value);
                }
            }
        }
    }
}

class Problem0715RangeModule2 {

    class RangeModule {

        class Node {
            Node left, right;
            boolean cover;
            int add;

            private void update(int start, int end, int s, int e, int val) {
                if (s <= start && end <= e) {
                    this.add = val;
                    this.cover = val == 1;
                    return;
                }
                pushDown();
                int mid = start + ((end - start) >> 1);
                if (s < mid) {
                    this.left.update(start, mid, s, e, val);
                }
                if (e > mid) {
                    this.right.update(mid, end, s, e, val);
                }
                this.cover = val == 1 && this.left.cover && this.right.cover;
            }

            private void pushDown() {
                if (this.left == null) {
                    this.left = new Node();
                }
                if (this.right == null) {
                    this.right = new Node();
                }
                if (this.add == 0) {
                    return;
                };
                this.left.add = this.right.add = this.add;
                this.left.cover = this.right.cover = this.add == 1;
                this.add = 0;
            }

            private boolean query(int start, int end, int s , int e) {
                if ((s <= start && end <= e) || this.cover || (this.add == -1)) {
                    return this.cover;
                }
                pushDown();
                int mid = start + ((end - start) >> 1);
                boolean ans = true;
                if (s < mid) {
                    ans = this.left.query(start, mid, s, e);
                }
                if (ans && e > mid) {
                    ans = this.right.query(mid, end, s, e);
                }
                return ans;
            }
        }

        Node root;
        static final int N = (int) 1e9;

        public RangeModule() {
            root = new Node();
        }

        public void addRange(int left, int right) {
            root.update(1, N, left, right, 1);
        }

        public boolean queryRange(int left, int right) {
            return root.query(1, N, left, right);
        }

        public void removeRange(int left, int right) {
            root.update(1, N, left, right, -1);
        }
    }
}

class RangeModule {
    private Node root;

    public RangeModule() {
    }

    private Node insert(Node root, int left, int right) {
        if (root == null) {
            return new Node(left, right);
        }
        if (right < root.left - 1) {
            root.l = insert(root.l, left, right);
        } else if (left > root.right + 1) {
            root.r = insert(root.r, left, right);
        } else {
            int[] info = new int[] {left, right};
            root.l = insertProcess(root.l, info, true);
            root.r = insertProcess(root.r, info, false);
            root.left = Math.min(root.left, info[0]);
            root.right = Math.max(root.right, info[1]);
        }
        return root;
    }

    private Node insertProcess(Node root, int[] info, boolean left) {
        if (root == null) {
            return null;
        }
        if (left) {
            if (root.right >= info[0] - 1) {
                info[0] = Math.min(info[0], root.left);
                return insertProcess(root.l, info, left);
            } else {
                root.r = insertProcess(root.r, info, left);
                return root;
            }
        } else if (!left) {
            if (root.left <= info[1] + 1) {
                info[1] = Math.max(info[1], root.right);
                return insertProcess(root.r, info, left);
            } else {
                root.l = insertProcess(root.l, info, left);
                return root;
            }
        }
        return root;
    }

    public void addRange(int left, int right) {
        root = insert(root, left, right - 1);
    }

    private boolean query(Node root, int left, int right) {
        if (root == null) {
            return false;
        }
        if (right < root.left) {
            return query(root.l, left, right);
        } else if (left > root.right) {
            return query(root.r, left, right);
        } else if (left >= root.left && right <= root.right) {
            return true;
        }
        return false;
    }

    public boolean queryRange(int left, int right) {
        return query(root, left, right - 1);
    }

    private Node remove(Node root, int left, int right) {
        if (root == null) {
            return null;
        }
        if (right < root.left) {
            root.l = remove(root.l, left, right);
        } else if (left > root.right) {
            root.r = remove(root.r, left, right);
        } else if (left <= root.left && right >= root.right) {
            Node l = remove(root.l, left ,right);
            Node r = remove(root.r, left, right);
            if (l == null) {
                return r;
            } else if (r == null) {
                return l;
            } else {
                if (l.r == null) {
                    l.r = r;
                    return l;
                } else {
                    Node tmp = r;
                    while (tmp.l != null) {
                        tmp = tmp.l;
                    }
                    tmp.l = l;
                    return r;
                }
            }
        } else if (left <= root.left) {
            root.left = right + 1;
            root.l = remove(root.l, left, right);
        } else if (right >= root.right) {
            root.right = left - 1;
            root.r = remove(root.r, left, right);
        } else {
            Node newR = new Node(right + 1, root.right);
            Node r = root.r;
            root.right = left - 1;
            newR.r = r;
            root.r = newR;
        }
        return root;
    }

    public void removeRange(int left, int right) {
        root = remove(root, left, right - 1);
    }
}
class Node {
    int left;
    int right;
    Node l;
    Node r;

    public Node(int left, int right) {
        this.left = left;
        this.right = right;
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
