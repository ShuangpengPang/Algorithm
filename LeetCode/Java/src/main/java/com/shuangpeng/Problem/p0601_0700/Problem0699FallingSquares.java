package com.shuangpeng.Problem.p0601_0700;

import java.util.*;

/**
 * @Description: Problem0699FallingSquares（掉落的方块）
 * @Date 2022/5/26 10:20 AM
 * @Version 1.0
 */
public class Problem0699FallingSquares {

    class Interval {
        int start, end;
        Interval left, right;
        int tall;
        boolean cover;

        Interval(int s, int e) {
            this.start = s;
            this.end = e;
            this.tall = 0;
            this.cover = false;
        }

        Interval(int s, int e, int t, boolean c) {
            this.start = s;
            this.end = e;
            this.tall = t;
            this.cover = c;
        }

        int getMid() {
            return start + ((end - start) >> 1);
        }

        void add(int s, int e) {
            int tall = getMaxTall(s, e);
            update(s, e, tall + e - s);
        }

        void update(int s, int e, int t) {
            if (s <= this.start && e >= this.end) {
                this.tall = t;
                this.cover = true;
                this.left = null;
                this.right = null;
                return;
            }
            int mid = getMid();
            if (this.cover) {
                if (this.left == null) {
                    this.left = new Interval(this.start, mid, this.tall, true);
                }
                if (this.right == null) {
                    this.right = new Interval(mid, this.end, this.tall, true);
                }
            }
            if (s < mid && this.left == null) {
                this.left = new Interval(this.start, mid);
            }
            if (e > mid && this.right == null) {
                this.right = new Interval(mid, this.end);
            }
            if (e <= mid) {
                this.left.update(s, e, t);
            } else if (s >= mid) {
                this.right.update(s, e, t);
            } else {
                this.left.update(s, mid, t);
                this.right.update(mid, e, t);
            }
            this.tall = Math.max(this.left == null ? 0 : this.left.tall, this.right == null ? 0 : this.right.tall);
            this.cover = this.left != null && this.right != null && this.left.cover && this.right.cover && this.left.tall == this.right.tall;
        }

        int getMaxTall(int s, int e) {
            if (s <= start && e >= end || this.cover) {
                return this.tall;
            }
            int mid = getMid();
            if (s < mid && this.left == null) {
                this.left = new Interval(this.start, mid);
            }
            if (e > mid && this.right == null) {
                this.right = new Interval(mid, this.end);
            }
            if (e <= mid) {
                return this.left.getMaxTall(s, e);
            } else if (s >= mid) {
                return this.right.getMaxTall(s, e);
            } else {
                return Math.max(this.left.getMaxTall(s, mid), this.right.getMaxTall(mid, e));
            }
        }
    }

    public List<Integer> fallingSquares0(int[][] positions) {
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
        for (int[] pos : positions) {
            left = Math.min(left, pos[0]);
            right = Math.max(right, pos[0] + pos[1]);
        }
        Interval root = new Interval(left, right);
        List<Integer> ans = new ArrayList<>(positions.length);
        for (int[] pos : positions) {
            root.add(pos[0], pos[0] + pos[1]);
            ans.add(root.tall);
        }
        return ans;
    }

    public List<Integer> fallingSquares1(int[][] positions) {
        TreeMap<Integer, int[]> map = new TreeMap<>();
        List<Integer> ans = new ArrayList<>(positions.length);
        for (int[] pos : positions) {
            int left = pos[0], right = pos[0] + pos[1];
            int height = 0;
            while (map.floorEntry(right - 1) != null && map.floorEntry(right - 1).getValue()[0] > left) {
                int left1 = map.floorKey(right - 1);
                int[] pair = map.remove(left1);
                int right1 = pair[0], height1 = pair[1];
                height = Math.max(height, height1);
                if (left1 < left) {
                    map.put(left1, new int[]{left, height1});
                }
                if (right1 > right) {
                    map.put(right, new int[]{right1, height1});
                }
            }
            height += pos[1];
            map.put(left, new int[]{right, height});
            if (ans.isEmpty()) {
                ans.add(height);
            } else {
                ans.add(Math.max(height, ans.get(ans.size() - 1)));
            }
        }
        return ans;
    }

    public List<Integer> fallingSquares2(int[][] positions) {
        List<Integer> ans = new ArrayList<>(positions.length);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 0);
        for (int[] pos : positions) {
            int left = pos[0], right = pos[0] + pos[1] - 1;
            int prevLeft = map.floorKey(left), prevRight = map.floorKey(right);
            Map<Integer, Integer> subMap = map.subMap(prevLeft, prevRight + 1);
            int height = pos[1];
            for (int key : subMap.keySet()) {
                height = Math.max(height, subMap.get(key) + pos[1]);
            }
            int prevHeight = map.get(prevRight);
            int size = subMap.keySet().size();
            int[] keys = new int[size];
            int idx = 0;
            for (int key : subMap.keySet()) {
                keys[idx++] = key;
            }
            for (int key : keys) {
                if (key > left) {
                    map.remove(key);
                }
            }
            map.put(left, height);
            Integer nextRight = map.higherKey(right);
            if (nextRight == null || nextRight != right + 1) {
                map.put(right + 1, prevHeight);
            }
            if (ans.isEmpty()) {
                ans.add(height);
            } else {
                ans.add(Math.max(height, ans.get(ans.size() - 1)));
            }
        }
        return ans;
    }

    public class Node {
        int left;
        int right;
        int height;
        int maxRight;
        Node l;
        Node r;

        public Node (int left, int right, int height, int maxRight) {
            this.left = left;
            this.right = right;
            this.height = height;
            this.maxRight = maxRight;
        }
    }
    public List<Integer> fallingSquares(int[][] positions) {
        Node root = null;
        List<Integer> ans = new ArrayList<>();
        int maxH = 0;
        for (int[] position : positions) {
            int left = position[0];
            int right = position[1] + left;
            int height = position[1];
            int curHeight = query(root, left, right);
            root = update(root, left, right, curHeight + height);
            maxH = Math.max(maxH, curHeight + height);
            ans.add(maxH);
        }
        return ans;
    }
    public Node update(Node root, int left, int right, int height) {
        if (root == null) {
            return new Node(left, right, height, right);
        }
        if (left < root.left) {
            root.l = update(root.l, left, right, height);
        } else {
            root.r = update(root.r, left, right, height);
        }
        root.maxRight = Math.max(right, root.maxRight);
        return root;
    }
    public int query(Node root, int left, int right) {
        if (root == null || left >= root.maxRight) {
            return 0;
        }
        int ans = 0;
        if (right > root.left && left < root.right) {
            ans = root.height;
        }
        ans = Math.max(ans, query(root.l, left, right));
        if (right > root.left) {
            ans = Math.max(ans, query(root.r, left, right));
        }
        return ans;
    }
}

class Problem0699FallingSquares0 {

    class Node {
        // ls 和 rs 分别代表当前区间的左右子节点所在 tr 数组中的下标
        // val 代表当前区间的最大高度，add 为懒标记
        int ls, rs, val, add;
    }
    int N = (int)1e9, cnt = 0;
    Node[] tr = new Node[1000010];
    void update(int u, int lc, int rc, int l, int r, int v) {
        if (l <= lc && rc <= r) {
            tr[u].val = v;
            tr[u].add = v;
            return ;
        }
        pushdown(u);
        int mid = lc + rc >> 1;
        if (l <= mid) update(tr[u].ls, lc, mid, l, r, v);
        if (r > mid) update(tr[u].rs, mid + 1, rc, l, r, v);
        pushup(u);
    }
    int query(int u, int lc, int rc, int l, int r) {
        if (l <= lc && rc <= r) return tr[u].val;
        pushdown(u);
        int mid = lc + rc >> 1, ans = 0;
        if (l <= mid) ans = query(tr[u].ls, lc, mid, l, r);
        if (r > mid) ans = Math.max(ans, query(tr[u].rs, mid + 1, rc, l, r));
        return ans;
    }
    void pushdown(int u) {
        if (tr[u] == null) tr[u] = new Node();
        if (tr[u].ls == 0) {
            tr[u].ls = ++cnt;
            tr[tr[u].ls] = new Node();
        }
        if (tr[u].rs == 0) {
            tr[u].rs = ++cnt;
            tr[tr[u].rs] = new Node();
        }
        if (tr[u].add == 0) return ;
        int add = tr[u].add;
        tr[tr[u].ls].add = add; tr[tr[u].rs].add = add;
        tr[tr[u].ls].val = add; tr[tr[u].rs].val = add;
        tr[u].add = 0;
    }
    void pushup(int u) {
        tr[u].val = Math.max(tr[tr[u].ls].val, tr[tr[u].rs].val);
    }
    public List<Integer> fallingSquares(int[][] ps) {
        List<Integer> ans = new ArrayList<>();
        tr[1] = new Node();
        for (int[] info : ps) {
            int x = info[0], h = info[1], cur = query(1, 1, N, x, x + h - 1);
            update(1, 1, N, x, x + h - 1, cur + h);
            ans.add(tr[1].val);
        }
        return ans;
    }
}
