package com.shuangpeng.Problem.p0501_0600;

import java.util.*;

public class Problem0587ErectTheFence {

    public int[][] outerTrees0(int[][] trees) {
        if (trees.length < 4) {
            return trees;
        }
        TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();
        for (int[] tree : trees) {
            int x = tree[0], y = tree[1];
            map.putIfAbsent(x, new TreeSet<>());
            map.get(x).add(y);
        }
        if (map.size() == 1) {
            return trees;
        }
        List<int[]> extreme = new ArrayList<>();
        for (Map.Entry<Integer, TreeSet<Integer>> entry : map.entrySet()) {
            int x = entry.getKey();
            TreeSet<Integer> value = entry.getValue();
            int first = value.first();
            int last = value.last();
            extreme.add(new int[]{x, first});
            if (first != last) {
                extreme.add(new int[]{x, last});
            }
        }
        Set<int[]> set = new HashSet<>();
        int p = 0, q = 1;
        set.add(extreme.get(p));
        int n = extreme.size();
        do {
            for (int r = 0; r < n; ++r) {
                if (r != p && r != q && orientation(extreme, p, q, r) > 0) {
                    q = r;
                }
            }
            set.add(extreme.get(q));
            addBetween(set, map, extreme, p, q);
            p = q;
            q = (p + 1) % n;
        } while (p != 0);
        int size = set.size();
        int[][] ans = new int[size][2];
        int idx = 0;
        for (int[] point : set) {
            ans[idx++] = point;
        }
        return ans;
    }

    private int orientation(List<int[]> extreme, int p, int q, int r) {
        int[] pointP = extreme.get(p);
        int[] pointQ = extreme.get(q);
        int[] pointR = extreme.get(r);
        return (pointR[0] - pointP[0]) * (pointQ[1] - pointR[1]) - (pointR[1] - pointP[1]) * (pointQ[0] - pointR[0]);
    }

    private void addBetween(Set<int[]> set, TreeMap<Integer, TreeSet<Integer>> map, List<int[]> extreme, int p, int q) {
        int[] pointP = extreme.get(p);
        int[] pointQ = extreme.get(q);
        int x1 = pointP[0], y1 = pointP[1], x2 = pointQ[0], y2 = pointQ[1];
        if (x1 == x2) {
            for (int y : map.get(x1)) {
                if (y != y1 && y != y2) {
                    set.add(new int[]{x1, y});
                }
            }
        } else {
            int n = extreme.size();
            for (int r = 0; r < n; ++r) {
                if (r != p && r != q && orientation(extreme, p, q, r) == 0 && dotProduct(extreme, p, q, r) < 0) {
                    set.add(extreme.get(r));
                }
            }
        }
    }

    private int dotProduct(List<int[]> extreme, int p, int q, int r) {
        int[] pointP = extreme.get(p);
        int[] pointQ = extreme.get(q);
        int[] pointR = extreme.get(r);
        return (pointR[0] - pointP[0]) * (pointR[0] - pointQ[0]) + (pointR[1] - pointP[1]) * (pointR[1] - pointQ[1]);
    }

    public int[][] outerTrees1(int[][] trees) {
        int n = trees.length;
        if (n < 4) {
            return trees;
        }
        int[] bm = bottomLeft(trees);
        Arrays.sort(trees, (p, q) -> {
            int diff = orientation(bm, q, p);
            if (diff == 0) {
                return distance(bm, p) - distance(bm, q);
            }
            return diff;
        });
        int idx = n - 1;
        while (idx >= 0 && orientation(bm, trees[n - 1], trees[idx]) == 0) {
            --idx;
        }
        for (int i = idx + 1, j = n - 1; i < j; ++i, --j) {
            int[] temp = trees[i];
            trees[i] = trees[j];
            trees[j] = temp;
        }
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(trees[0]);
        stack.push(trees[1]);
        for (int i = 2; i < n; ++i) {
            int[] top = stack.pop();
            while (orientation(stack.peek(), top, trees[i]) < 0) {
                top = stack.pop();
            }
            stack.push(top);
            stack.push(trees[i]);
        }
        int[][] ans = new int[stack.size()][2];
        int i = 0;
        while (!stack.isEmpty()) {
            ans[i++] = stack.pop();
        }
        return ans;
    }

    private int[] bottomLeft(int[][] trees) {
        int[] p = trees[0];
        for (int[] q : trees) {
            if (q[0] < p[0] || (q[0] == p[0] && q[1] < p[1])) {
                p = q;
            }
        }
        return p;
    }

    private int distance(int[] p, int[] q) {
        return (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
    }

    private int orientation(int[] p, int[] q, int[] r) {
        return -((r[0] - p[0]) * (q[1] - r[1]) - (r[1] - p[1]) * (q[0] - r[0]));
    }

    public int[][] outerTrees(int[][] trees) {
        int n = trees.length;
        if (n < 4) {
            return trees;
        }
        Arrays.sort(trees, (p, q) -> p[0] != q[0] ? p[0] - q[0] : p[1] - q[1]);
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        stack.push(1);
        for (int i = 2; i < n; ++i) {
            int top = stack.pop();
            while (!stack.isEmpty() && orientation(trees[stack.peek()], trees[top], trees[i]) < 0) {
                top = stack.pop();
            }
            stack.push(top);
            stack.push(i);
        }
        stack.push(n - 1);
        stack.push(n - 2);
        for (int i = n - 3; i >= 0; --i) {
            int top = stack.pop();
            while (!stack.isEmpty() && orientation(trees[stack.peek()], trees[top], trees[i]) < 0) {
                top = stack.pop();
            }
            stack.push(top);
            stack.push(i);
        }
        Set<int[]> set = new HashSet<>();
        while (!stack.isEmpty()) {
            set.add(trees[stack.pop()]);
        }
        int[][] ans = new int[set.size()][2];
        int idx = 0;
        for (int[] p : set) {
            ans[idx++] = p;
        }
        return ans;
    }

//    public int[][] outerTrees(int[][] trees) {
//
//    }
}
