package com.shuangpeng.Problem.p1601_1700;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1654MinimumJumpsToReachHome（到家的最少跳跃次数）
 * @date 2023/8/30 10:27 AM
 */
public class Problem1654MinimumJumpsToReachHome {

    public int minimumJumps0(int[] forbidden, int a, int b, int x) {
        Set<Integer> set = new HashSet<>();
        int max = 0;
        for (int f : forbidden) {
            set.add(f);
            max = Math.max(max, f);
        }
        max = Math.max(max + a, x) + b;
        Set<Integer>[] visited = new Set[2];
        Arrays.setAll(visited, i -> new HashSet<>());
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        visited[0].add(0);
        int jumps = 0;
        while (!q.isEmpty()) {
            for (int i = q.size() - 1; i >= 0; i--) {
                int[] p = q.poll();
                if (p[1] == x) {
                    return jumps;
                }
                int left = p[1] - b, right = p[1] + a;
                if (p[0] == 0 && left > 0 && !set.contains(left) && !visited[1].contains(left)) {
                    visited[1].add(left);
                    q.offer(new int[]{1, left});
                }
                if (right <= max && !visited[0].contains(right) && !set.contains(right)) {
                    visited[0].add(right);
                    q.offer(new int[]{0, right});
                }
            }
            jumps++;
        }
        return -1;
    }

    public int minimumJumps1(int[] forbidden, int a, int b, int x) {
        Set<Integer> set = new HashSet<>();
        int max = 0;
        for (int f : forbidden) {
            set.add(f);
            max = Math.max(max, f);
        }
        max = Math.max(max + a, x) + b;
        Set<Integer> visited = new HashSet<>();
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{1, 0});
        visited.add(0);
        int jumps = 0;
        while (!q.isEmpty()) {
            for (int i = q.size() - 1; i >= 0; i--) {
                int[] p = q.poll();
                if (p[1] == x) {
                    return jumps;
                }
                int left = p[1] - b, right = p[1] + a;
                if (p[0] == 1 && left > 0 && !set.contains(left) && !visited.contains(-left)) {
                    visited.add(-left);
                    q.offer(new int[]{-1, left});
                }
                if (right <= max && !visited.contains(right) && !set.contains(right)) {
                    visited.add(right);
                    q.offer(new int[]{1, right});
                }
            }
            jumps++;
        }
        return -1;
    }

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        int max = x;
        Set<Integer> set = new HashSet<>();
        for (int f : forbidden) {
            set.add(f);
            max = Math.max(max, f);
        }
        max += a + b;
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        visited.add(0);
        int jump = 0;
        while (!q.isEmpty()) {
            for (int i = q.size() - 1; i >= 0; i--) {
                int num = q.poll(), d = num >= 0 ? 1 : -1;
                num = d == 1 ? num : -num;
                if (num == x) {
                    return jump;
                }
                int left = num - b, right = num + a;
                if (right <= max && !set.contains(right) && visited.add(right)) {
                    q.offer(right);
                }
                if (d == 1 && left > 0 && !set.contains(left) && visited.add(-left)) {
                    q.offer(-left);
                }
            }
            jump++;
        }
        return -1;
    }
}
