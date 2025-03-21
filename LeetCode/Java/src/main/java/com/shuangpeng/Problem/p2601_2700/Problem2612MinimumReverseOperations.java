package com.shuangpeng.Problem.p2601_2700;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2612MinimumReverseOperations（最少翻转操作数）
 * @date 2024/1/31 10:43 AM
 */
public class Problem2612MinimumReverseOperations {

    public int[] minReverseOperations0(int n, int p, int[] banned, int k) {
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int b : banned) {
            ans[b] = -2;
        }
        ans[p] = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(p);
        int step = 0;
        while (!q.isEmpty()) {
            step++;
            for (int i = q.size() - 1; i >= 0; i--) {
                int x = q.poll();
                int s = (Math.max(0, x - k + 1) << 1) + k - x - 1, e = (Math.min(n - 1, x + k - 1) << 1) - k - x + 1;
                int first = -1, last = -1;
                for (int j = s; j <= e; j += 2) {
                    if (ans[j] == -1) {
                        ans[j] = step;
                        if (first == -1) {
                            first = j;
                        } else {
                            last = j;
                        }
                    }
                }
                if (first != -1) {
                    q.offer(first);
                }
                if (last != -1) {
                    q.offer(last);
                }
            }
        }
        for (int b : banned) {
            ans[b] = -1;
        }
        return ans;
    }

    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        Set<Integer> set = new HashSet<>();
        for (int b : banned) {
            set.add(b);
        }
        TreeSet<Integer>[] sets = new TreeSet[2];
        Arrays.setAll(sets, i -> new TreeSet<>());
        for (int i = 0; i < n; i++) {
            if (i != p && !set.contains(i)) {
                sets[i & 1].add(i);
            }
        }
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Queue<Integer> q = new ArrayDeque<>(n);
        q.offer(p);
        ans[p] = 0;
        while (!q.isEmpty()) {
            int x = q.poll();
            int mn = Math.max(k - x - 1, x - k + 1);
            int mx = Math.min((n << 1) - k - x - 1, x + k - 1);
            TreeSet<Integer> s = sets[mn & 1];
            Integer y = s.ceiling(mn);
            while (y != null && y <= mx) {
                ans[y] = ans[x] + 1;
                s.remove(y);
                q.offer(y);
                y = s.higher(y);
            }
        }
        return ans;
    }
}

class Problem2612MinimumReverseOperations0 {

    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        int[] parent = new int[n + 2];
        Arrays.setAll(parent, i -> i);
        for (int b : banned) {
            parent[b] = b + 2;
        }
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        ans[p] = 0;
        parent[p] = p + 2;
        Queue<Integer> q = new ArrayDeque<>(n);
        q.offer(p);
        while (!q.isEmpty()) {
            int x = q.poll();
            int mn = Math.max(k - x - 1, x - k + 1);
            int mx = Math.min((n << 1) - k - x - 1, x + k - 1);
            int y = find(parent, mn);
            while (y <= mx) {
                q.offer(y);
                ans[y] = ans[x] + 1;
                parent[y] = y + 2;
                y = find(parent, y);
            }
        }
        return ans;
    }

    private int find(int[] parent, int x) {
        return parent[x] = x == parent[x] ? x : find(parent, parent[x]);
    }
}
