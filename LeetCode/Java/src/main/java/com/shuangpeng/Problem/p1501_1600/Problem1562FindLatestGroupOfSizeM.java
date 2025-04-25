package com.shuangpeng.Problem.p1501_1600;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1562FindLatestGroupOfSizeM（查找大小为M的最新分组）
 * @date 2023/9/6 5:26 PM
 */
public class Problem1562FindLatestGroupOfSizeM {

//    class Node {
//        Node left, right;
//        int start, end;
//
//        public Node(int start, int end) {
//            this.start = start;
//            this.end = end;
//        }
//
//        public boolean split(int x, int m) {
//            if (start >= end) {
//                return m == 0;
//            }
//            if (left == null) {
//                left = new Node(start, x - 1);
//                right = new Node(x + 1, end);
//                return x - start == m || end - x == m;
//            }
//            if (x <= left.end) {
//                return left.split(x, m);
//            }
//            return right.split(x, m);
//        }
//    }
//
//    public int findLatestStep(int[] arr, int m) {
//        int n = arr.length;
//        if (m == n) {
//            return n;
//        }
//        Node node = new Node(1, n);
//        for (int i = n - 1; i >= 0; i--) {
//            if (node.split(arr[i], m)) {
//                return i;
//            }
//        }
//        return -1;
//    }

    public int findLatestStep0(int[] arr, int m) {
        int n = arr.length;
        int[] parent = new int[n], size = new int[n];
        Arrays.fill(parent, -1);
        int ans = -1;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int x = arr[i] - 1;
            parent[x] = x;
            size[x] = 1;
            if (x < n - 1) {
                int p = find(parent, x + 1);
                if (p != -1) {
                    if (size[p] == m) {
                        cnt--;
                    }
                    union(parent, size, x, p, m);
                }
            }
            if (x > 0) {
                int p = find(parent, x - 1);
                if (p != -1) {
                    if (size[p] == m) {
                        cnt--;
                    }
                    union(parent, size, p, x, m);
                }
            }
            if (size[find(parent, x)] == m) {
                cnt++;
            }
            if (cnt > 0) {
                ans  = i;
            }
        }
        return ans == -1 ? -1 : ans + 1;
    }

    private int find(int[] parent, int x) {
        if (parent[x] == -1) {
            return -1;
        }
        return parent[x] = x == parent[x] ? x : find(parent, parent[x]);
    }

    private void union(int[] parent, int[] size, int x, int y, int m) {
        int px = find(parent, x), py = find(parent, y);
        if (px == -1 || py == -1) {
            return;
        }
        parent[px] = py;
        size[py] += size[px];
    }

    public int findLatestStep(int[] arr, int m) {
        int n = arr.length, N = Integer.MAX_VALUE / 3;
        int[][] segment = new int[n + 2][2];
        for (int i = 0; i <= n + 1; i++) {
            segment[i][0] = N;
            segment[i][1] = -N;
        }
        int ans = -1, cnt = 0;
        for (int i = 0; i < n; i++) {
            int p = arr[i], left = p, right = p;
            int s1 = segment[p - 1][0], e1 = segment[p - 1][1];
            int s2 = segment[p + 1][0], e2 = segment[p + 1][1];
            if (e1 - s1 + 1 == m) {
                cnt--;
            }
            if (e2 - s2 + 1 == m) {
                cnt--;
            }
            left = Math.min(left, s1);
            right = Math.max(right, e2);
            segment[left][0] = segment[right][0] = left;
            segment[left][1] = segment[right][1] = right;
            if (right - left + 1 == m) {
                cnt++;
            }
            if (cnt > 0) {
                ans = i + 1;
            }
        }
        return ans;
    }
}
