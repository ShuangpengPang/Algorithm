package com.shuangpeng.Problem.P2201_2300;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2213LongestSubstringOfOneRepeatingCharacter（由单个字符重复的最长子字符串）
 * @date 2022/12/1 11:43 AM
 */
public class Problem2213LongestSubstringOfOneRepeatingCharacter {

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[1] - b[0]) - (a[1] - a[0]));
        int start = 0, end = 0;
        for (int i = 1; i < n; i++) {
            if (cs[i] == cs[i - 1]) {
                end++;
            } else {
                pq.offer(new int[]{start, end});
                map.put(start, end);
                start = end = i;
            }
        }
        pq.offer(new int[]{start, end});
        map.put(start, end);
        int k = queryIndices.length;
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i), c1 = cs[idx];
            if (c != c1) {
                cs[idx] = c;
                merge(cs, map, pq, idx, idx);
            }
            while (!map.containsKey(pq.peek()[0]) || pq.peek()[1] != map.get(pq.peek()[0])) {
                pq.poll();
            }
            ans[i] = pq.peek()[1] - pq.peek()[0] + 1;
        }
        return ans;
    }

    private void split(TreeMap<Integer, Integer> map, PriorityQueue<int[]> pq, int idx) {
        int key = map.floorKey(idx);
        if (key == idx || map.get(key) < idx) {
            return;
        }
        int val = map.get(key);
        map.put(key, idx - 1);
        map.put(idx, val);
        pq.offer(new int[]{key, idx - 1});
        pq.offer(new int[]{idx, val});
    }

    private void merge(char[] cs, TreeMap<Integer, Integer> map, PriorityQueue<int[]> pq, int l, int r) {
        split(map, pq, l);
        split(map, pq, r + 1);
        map.subMap(l, r + 1).clear();
        Integer left = map.floorKey(l - 1), right = map.ceilingKey(r + 1);
        if (left != null && cs[left] == cs[l]) {
            map.remove(left);
            l = left;
        }
        if (right != null && cs[right] == cs[l]) {
            r = map.get(right);
            map.remove(right);
        }
        map.put(l, r);
        pq.offer(new int[]{l, r});
    }
}

class Problem2213LongestSubstringOfOneRepeatingCharacter0 {

    // 在珂朵莉树的基础上，添加查询最长子串操作, 使用 优先队列+懒惰删除策略
    static class ODTTree {

        static class Node {
            int l, r, v;
            public Node(int l, int r, int v) {
                this.l = l;
                this.r = r;
                this.v = v;
            }
        }

        TreeMap<Integer, Node> tree = new TreeMap<>();

        // 大顶堆 三元组(l, r, v)
        PriorityQueue<Node> pp = new PriorityQueue<>((a, b) -> -Integer.compare(a.r - a.l, b.r - b.l));

        public ODTTree() {
        }

        public ODTTree(int l, int r, int v) {
            tree.put(l, new Node(l, r, v));
            pp.offer(new Node(l, r, v));
        }

        public void split(int l) {
            Map.Entry<Integer, Node> prev = tree.floorEntry(l);
            if (prev == null || prev.getKey() == l || prev.getValue().r < l) return;

            Node cur = prev.getValue();
            int r = cur.r, v = cur.v;

            tree.remove(prev.getKey());
            tree.put(prev.getKey(), new Node(cur.l, l - 1, v));
            tree.put(l, new Node(l, r, v));

            pp.offer(new Node(cur.l, l - 1, v));
            pp.offer(new Node(l, r, v));
        }

        public void merge(int l, int r, int v) {
            this.split(l);
            this.split(r + 1);

            // l闭区间, r+1开区间
            tree.subMap(l, r + 1).clear();

            // 再做一个合并
            //      和前一个元素进行合并
            Map.Entry<Integer, Node> prev = tree.floorEntry(l - 1);
            if (prev != null && prev.getValue().v == v && prev.getValue().r == l - 1) {
                tree.remove(prev.getKey());
                l = prev.getKey();
            }

            //      和后一个区间进行合并
            Map.Entry<Integer, Node> next = tree.ceilingEntry(r + 1);
            if (next != null && next.getValue().v == v && next.getKey() == r + 1) {
                tree.remove(next.getKey());
                r = next.getValue().r;
            }
            tree.put(l, new Node(l, r, v));
            pp.offer(new Node(l, r, v));
        }

        public int queryMax() {
            while (!pp.isEmpty()) {
                Node cur = pp.peek();
                // 如果能查询到并匹配，则就是非过期的
                Node q = tree.get(cur.l);
                if (q != null && q.l == cur.l && q.r == cur.r && q.v == cur.v) {
                    return q.r - q.l + 1;
                }
                pp.poll();
            }
            // unreachable
            return 0;
        }
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {

        ODTTree odt = new ODTTree();

        char[] buf = s.toCharArray();

        int i = 0;
        while (i < buf.length) {
            char c = buf[i];
            int j = i + 1;
            while (j < buf.length && buf[j] == c) {
                j++;
            }
            odt.merge(i, j - 1, (int)(c - 'a'));
            i = j;
        }

        int m = queryIndices.length;
        int[] res = new int[m];

        for (int k = 0; k < m; k++) {
            int idx = queryIndices[k];
            int v = (int)(queryCharacters.charAt(k) - 'a');
            odt.merge(idx, idx, v);

            res[k] = odt.queryMax();
        }
        return res;
    }
}

class Problem2213LongestSubstringOfOneRepeatingCharacter1 {

    char[] cs;

    class Node {
        int start, end;
        Node left, right;
        int pre, suf, max;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private void build(Node node) {
        if (node.start == node.end) {
            node.pre = node.suf = node.max = 1;
            return;
        }
        int mid = node.start + (node.end - node.start >> 1);
        node.left = new Node(node.start, mid);
        node.right = new Node(mid + 1, node.end);
        build(node.left);
        build(node.right);
        merge(node, mid);
    }

    private void update(Node node, int idx) {
        if (node.start == node.end) {
            return;
        }
        int mid = node.start + (node.end - node.start >> 1);
        if (idx <= mid) {
            update(node.left, idx);
        } else {
            update(node.right, idx);
        }
        merge(node, mid);
    }

    private void merge(Node node, int mid) {
        node.pre = node.left.pre;
        node.suf = node.right.suf;
        node.max = Math.max(node.left.max, node.right.max);
        if (cs[mid] == cs[mid + 1]) {
            if (node.pre == mid - node.start + 1) {
                node.pre += node.right.pre;
            }
            if (node.suf == node.end - mid) {
                node.suf += node.left.suf;
            }
            node.max = Math.max(node.max, node.left.suf + node.right.pre);
        }
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        this.cs = s.toCharArray();
        Node root = new Node(0, cs.length - 1);
        build(root);
        int k = queryIndices.length;
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            cs[idx] = queryCharacters.charAt(i);
            update(root, idx);
            ans[i] = root.max;
        }
        return ans;
    }
}
