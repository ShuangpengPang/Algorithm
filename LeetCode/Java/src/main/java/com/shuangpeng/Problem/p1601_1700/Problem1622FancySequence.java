package com.shuangpeng.Problem.p1601_1700;

/**
 * @Description: Problem1622FancySequence（奇妙序列）
 * @Date 2022/9/6 12:02 PM
 * @Version 1.0
 */
public class Problem1622FancySequence {

    static int M = (int) 1e9 + 7, N = (int) 1e5;
    static int[] nums = new int[N];

    class Fancy {

        class Node {
            long f, c;
            Node(long f, long c) {
                this.f = f;
                this.c = c;
            }

            void add(Node node) {
                this.f = this.f * node.f % M;
                this.c = (this.c * node.f + node.c) % M;
            }
        }

        Node[] nodes;

        int size;

        public Fancy() {
            nodes = new Node[N + 1];
            size = 0;
        }

        public void append(int val) {
            nums[size] = val;
            nodes[N - size] = new Node(1, 0);
            size++;
        }

        public void addAll(int inc) {
            if (size == 0) {
                return;
            }
            Node node = new Node(1, inc);
            int x = N - size + 1;
            while (x <= N) {
                nodes[x].add(node);
                x += x & (-x);
            }
        }

        public void multAll(int m) {
            if (size == 0) {
                return;
            }
            Node node = new Node(m, 0);
            int x = N - size + 1;
            while (x <= N) {
                nodes[x].add(node);
                x += x & (-x);
            }
        }

        public int getIndex(int idx) {
            if (idx >= size) {
                return -1;
            }
            int x = N - idx;
            Node node = new Node(nodes[x].f, nodes[x].c);
            x -= x & (-x);
            while (x > 0 && nodes[x] != null) {
                node.add(nodes[x]);
                x -= x & (-x);
            }
            return (int) ((nums[idx] * node.f + node.c) % M);
        }
    }

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */
}

class Fancy {

    static int M = (int) 1e9 + 7, N = (int) 1e5;

    class Info {
        long f, c;
        Info (long f, long c) {
            this.f = f;
            this.c = c;
        }

        void add(Info info) {
            this.f = this.f * info.f % M;
            this.c = (this.c * info.f + info.c) % M;
        }

        void reset() {
            f = 1;
            c = 0;
        }
    }

    class Node {
        int start, end;
        Node left, right;
        Info info;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
            this.info = new Info(1, 0);
        }

        int getMid() {
            return start + (end - start >> 1);
        }

        void update(int s, int e, Info other) {
            if (s > e) {
                return;
            }
            if (s <= start && e >= end || start == end) {
                info.add(other);
                return;
            }
            lazyUpdate();
            int mid = getMid();
            if (s <= mid) {
                left.update(s, Math.min(mid, e), other);
            }
            if (e > mid) {
                right.update(Math.max(mid + 1, s), e, other);
            }
        }

        Info query(int idx) {
            if (start == end) {
                return info;
            }
            lazyUpdate();
            if (idx <= getMid()) {
                return left.query(idx);
            }
            return right.query(idx);
        }

        void lazyUpdate() {
            int mid = getMid();
            if (left == null) {
                left = new Node(start, mid);
            }
            if (right == null) {
                right = new Node(mid + 1, end);
            }
            left.info.add(this.info);
            right.info.add(this.info);
            this.info.reset();
        }
    }

    Node root;
    static int[] nums = new int[N];
    int size;

    public Fancy() {
        root = new Node(0, N);
        size = 0;
    }

    public void append(int val) {
        nums[size++] = val;
    }

    public void addAll(int inc) {
        if (size == 0) {
            return;
        }
        root.update(0, size - 1, new Info(1, inc));
    }

    public void multAll(int m) {
        if (size == 0) {
            return;
        }
        root.update(0, size - 1, new Info(m, 0));
    }

    public int getIndex(int idx) {
        if (idx >= size) {
            return -1;
        }
        Info info = root.query(idx);
        return (int) ((nums[idx] * info.f + info.c) % M);
    }
}

