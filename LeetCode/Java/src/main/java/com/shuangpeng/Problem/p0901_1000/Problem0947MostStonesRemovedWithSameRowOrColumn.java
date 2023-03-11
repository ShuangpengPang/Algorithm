package com.shuangpeng.Problem.p0901_1000;

import java.util.ArrayList;
import java.util.Arrays;
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
