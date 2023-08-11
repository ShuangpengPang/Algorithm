package com.shuangpeng.Problem.p0901_1000;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0947MostStonesRemovedWithSameRowOrColumn（移除最多的同行或同列石头）
 * @date 2023/3/11 3:53 PM
 */
public class Problem0947MostStonesRemovedWithSameRowOrColumn {

    public int removeStones0(int[][] stones) {
        Map<Integer, List<Integer>> row = new HashMap<>(), col = new HashMap<>();
        for (int[] s : stones) {
            int x = s[0], y = s[1];
            row.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
            col.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
        }
        Set<Integer> visited = new HashSet<>();
        int part = 0;
        for (int[] s : stones) {
            if (visited.add(s[0] << 14 | s[1])) {
                part++;
                dfs(row, col, visited, s[0], s[1]);
            }
        }
        return stones.length - part;
    }

    private void dfs(Map<Integer, List<Integer>> row, Map<Integer, List<Integer>> col, Set<Integer> visited, int x, int y) {
        for (int y1 : row.get(x)) {
            if (visited.add(x << 14 | y1)) {
                dfs(row, col, visited, x, y1);
            }
        }
        for (int x1 : col.get(y)) {
            if (visited.add(x1 << 14 | y)) {
                dfs(row, col, visited, x1, y);
            }
        }
    }

    public int removeStones1(int[][] stones) {
        Map<Integer, List<Integer>> row = new HashMap<>(), col = new HashMap<>();
        for (int[] s : stones) {
            int x = s[0], y = s[1];
            row.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
            col.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
        }
        Set<Integer> visited = new HashSet<>();
        Deque<int[]> q = new ArrayDeque<>();
        int part = 0;
        for (int[] s : stones) {
            int x = s[0], y = s[1];
            if (visited.add(x << 14 | y)) {
                part++;
                q.addLast(new int[]{x, y});
                while (!q.isEmpty()) {
                    int[] p = q.pollFirst();
                    int x1 = p[0], y1 = p[1];
                    for (int y2 : row.get(x1)) {
                        if (visited.add(x1 << 14 | y2)) {
                            q.addLast(new int[]{x1, y2});
                        }
                    }
                    for (int x2 : col.get(y1)) {
                        if (visited.add(x2 << 14 | y1)) {
                            q.addLast(new int[]{x2, y1});
                        }
                    }
                }
            }
        }
        return stones.length - part;
    }

    public int removeStones(int[][] stones) {
        int n = stones.length, maxX = 0, maxY = 0;
        for (int[] s : stones) {
            maxX = Math.max(maxX, s[0]);
            maxY = Math.max(maxY, s[1]);
        }
        int[] row = new int[maxX + 1], col = new int[maxY + 1], parent = new int[n];
        Arrays.fill(row, -1);
        Arrays.fill(col, -1);
        Arrays.setAll(parent, i -> i);
        return union(stones, parent, row, true) + union(stones, parent, col, false);
    }

    private int union(int[][] stones, int[] parent, int[] nums, boolean isRow) {
        int ans = 0, n = stones.length;
        for (int i = 0; i < n; i++) {
            int num = isRow ? stones[i][0] : stones[i][1];
            if (nums[num] == -1) {
                nums[num] = i;
                continue;
            }
            int p1 = find(parent, nums[num]), p2 = find(parent, i);
            if (p1 != p2) {
                parent[p2] = p1;
                ans++;
            }
        }
        return ans;
    }

    private int find(int[] parent, int x) {
        return parent[x] = parent[x] == x ? x :find(parent, parent[x]);
    }
}

class Problem0947MostStonesRemovedWithSameRowOrColumn0 {

    public int removeStones(int[][] stones) {
        int n = stones.length, N = (int) 1e4 + 1;
        int[] parent = new int[n], row = new int[N], col = new int[N];
        Arrays.setAll(parent, i -> i);
        for (int i = 0; i < N; i++) {
            row[i] = -1;
            col[i] = -1;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = stones[i][0], y = stones[i][1];
            int s1 = row[x] == -1 ? i : row[x];
            int s2 = col[y] == -1 ? i : col[y];
            ans += union(parent, s1, s2);
            ans += union(parent, s1, i);
            row[x] = col[y] = i;
        }
        return ans;
    }

    private int find(int[] parent, int x) {
        return parent[x] = parent[x] == x ? x : find(parent, parent[x]);
    }

    private int union(int[] parent, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px == py) {
            return 0;
        }
        parent[py] = px;
        return 1;
    }
}

class Problem0947MostStonesRemovedWithSameRowOrColumn1 {

    public int removeStones(int[][] stones) {
        Map<Integer, Integer> parent = new HashMap<>();
        int part = 0;
        for (int[] s : stones) {
            part += union(parent, s[0] + 10001, s[1]);
        }
        return stones.length - part;
    }

    private int find(Map<Integer, Integer> parent, int x) {
        int p = parent.getOrDefault(x, -1);
        if (p == x || p == -1) {
            return p;
        }
        p = find(parent, p);
        parent.put(x, p);
        return p;
    }

    private int union(Map<Integer, Integer> parent, int x, int y) {
        int count = 0;
        int px = find(parent, x), py = find(parent, y);
        if (px == -1) {
            count++;
            px = x;
            parent.put(x, x);
        }
        if (py == -1) {
            count++;
            py = y;
            parent.put(y, y);
        }
        if (px != py) {
            count--;
            parent.put(py, px);
        }
        return count;
    }
}

//public class Solution {
//
//    public int removeStones(int[][] stones) {
//        UnionFind unionFind = new UnionFind();
//
//        for (int[] stone : stones) {
//            // 下面这三种写法任选其一
//            // unionFind.union(~stone[0], stone[1]);
//            // unionFind.union(stone[0] - 10001, stone[1]);
//            unionFind.union(stone[0] + 10001, stone[1]);
//        }
//        return stones.length - unionFind.getCount();
//    }
//
//    private class UnionFind {
//
//        private Map<Integer, Integer> parent;
//        private int count;
//
//        public UnionFind() {
//            this.parent = new HashMap<>();
//            this.count = 0;
//        }
//
//        public int getCount() {
//            return count;
//        }
//
//        public int find(int x) {
//            if (!parent.containsKey(x)) {
//                parent.put(x, x);
//                // 并查集集中新加入一个结点，结点的父亲结点是它自己，所以连通分量的总数 +1
//                count++;
//            }
//
//            if (x != parent.get(x)) {
//                parent.put(x, find(parent.get(x)));
//            }
//            return parent.get(x);
//        }
//
//        public void union(int x, int y) {
//            int rootX = find(x);
//            int rootY = find(y);
//            if (rootX == rootY) {
//                return;
//            }
//
//            parent.put(rootX, rootY);
//            // 两个连通分量合并成为一个，连通分量的总数 -1
//            count--;
//        }
//    }
//}

class Problem0947MostStonesRemovedWithSameRowOrColumn2 {

    public int removeStones(int[][] stones) {
        Map<Integer, Integer> parent = new HashMap<>();
        for (int[] s : stones) {
            union(parent, s[0], s[1] + 1 << 15);
        }
        int cnt = 0;
        for (Map.Entry<Integer, Integer> entry : parent.entrySet()) {
            if (entry.getKey().equals(entry.getValue())) {
                cnt++;
            }
        }
        return stones.length - cnt;
    }

    private int find(Map<Integer, Integer> parent, int x) {
        if (!parent.containsKey(x)) {
            parent.put(x, x);
            return x;
        }
        int p = parent.get(x);
        if (p != x) {
            p = find(parent, p);
            parent.put(x, p);
        }
        return p;
    }

    private void union(Map<Integer, Integer> parent, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px != py) {
            parent.put(py, px);
        }
    }
}

class Problem0947MostStonesRemovedWithSameRowOrColumn3 {

    public int removeStones(int[][] stones) {
        int N = (int) 1e4 + 1, ans = 0;
        Map<Integer, Integer> parent = new HashMap<>();
        for (int[] s : stones) {
            int x = s[0], y = s[1] + N;
            ans += union(parent, x, y);
        }
        return ans;
    }

    private int find(Map<Integer, Integer> parent, int x) {
        int p = parent.getOrDefault(x, x);
        if (p != x) {
            p = find(parent, p);
        }
        parent.put(x, p);
        return p;
    }

    private int union(Map<Integer, Integer> parent, int x, int y) {
        boolean hasX = parent.containsKey(x), hasY = parent.containsKey(y);
        int px = find(parent, x), py = find(parent, y);
        int ans = !hasX && !hasY ? 0 : 1;
        if (px != py) {
            parent.put(py, px);
            ans = hasX && hasY ? ans + 1 : ans;
        }
        return ans;
    }
}

//class tmp {
//
//    public int removeStones(int[][] stones) {
//        int n = stones.length, N = (int) 1e4 + 1, ans = 0;
//        int[] parent = new int[n], rows = new int[N], cols = new int[N];
//        Arrays.setAll(parent, i -> i);
//        Arrays.fill(rows, -1);
//        Arrays.fill(cols, -1);
//        for (int i = 0; i < n; i++) {
//            int x = stones[i][0], y = stones[i][1];
//            int r = rows[x] == -1 ? i : rows[x];
//            int c = cols[y] == -1 ? i : cols[y];
//            int px = find(parent, r), py = find(parent, c);
//            if (px == py) {
//                ans += px == i ? 0 : 1;
//            } else {
//                ans += px == i || py == i ? 1 : 2;
//                parent[py] = px;
//            }
//            rows[x] = cols[y] = px;
//        }
//        return ans;
//    }
//
//    private int find(int[] parent, int x) {
//        return parent[x] = x == parent[x] ? x : find(parent, parent[x]);
//    }
//}
