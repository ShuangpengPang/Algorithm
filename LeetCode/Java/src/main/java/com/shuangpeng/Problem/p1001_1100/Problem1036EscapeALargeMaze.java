package com.shuangpeng.Problem.p1001_1100;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Problem1036EscapeALargeMaze {

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        int n = blocked.length;
        if (n < 2) {
            return true;
        }
        final long N = (long) 1e6;
        Set<Long> set = new HashSet<>();
        for (int[] b : blocked) {
            set.add(b[0] * N + b[1]);
        }
        return check(set, source[0], source[1], target[0], target[1]) && check(set, target[0], target[1], source[0], source[1]);
    }

    private boolean check(Set<Long> set, int sx, int sy, int tx, int ty) {
        final long N = (long) 1e6;
        final int M = set.size() * (set.size() - 1) >> 1;
        final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Set<Long> visited = new HashSet<>();
        Queue<Long> queue = new LinkedList<>();
        queue.offer(sx * N + sy);
        visited.add(sx * N + sy);
        int count = 1;
        while (!queue.isEmpty() && count <= M) {
            long hash = queue.poll();
            long x = hash / N, y = hash % N;
            for (int[] dir : dirs) {
                long x1 = x + dir[0], y1 = y + dir[1];
                if (x1 == tx && y1 == ty) {
                    return true;
                }
                long h = x1 * N + y1;
                if (x1 < 0 || x1 >= N || y1 < 0 || y1 >= N || visited.contains(h) || set.contains(h)) {
                    continue;
                }
                ++count;
                queue.offer(h);
                visited.add(h);
            }
        }
        return count > M;
    }

//    public static void main(String[] args) {
//        Problem1036EscapeALargeMaze a = new Problem1036EscapeALargeMaze();
//        int[][] blocked = new int[0][2];
//        int[] source = new int[]{0, 0};
//        int[] target = new int[]{999999, 999999};
//        a.isEscapePossible(blocked, source, target);
//        int i = 1;
//    }

}
