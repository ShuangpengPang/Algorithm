package com.shuangpeng.Problem.p1401_1500;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1496PathCrossing（判断路径是否相交）
 * @date 2024/3/6 10:48 AM
 */
public class Problem1496PathCrossing {

    private static final int N = (int) 1e4;
    private int nx, ny;

    public boolean isPathCrossing(String path) {
        Set<Integer> visited = new HashSet<>();
        nx = ny = N;
        visited.add(nx << 15 | ny);
        for (char c : path.toCharArray()) {
            if (check(c, nx, ny, visited)) {
                return true;
            }
        }
        return false;
    }

    private boolean check(char c, int x, int y, Set<Integer> visited) {
        if (c == 'N') {
            ny++;
        } else if (c == 'S') {
            ny--;
        } else if (c == 'E') {
            nx++;
        } else {
            nx--;
        }
        return !visited.add(nx << 15 | ny);
    }
}

class Problem1496PathCrossing0 {

    private static final int[][] dirs = new int[26][];

    static {
        dirs['N' - 'A'] = new int[]{0, 1};
        dirs['S' - 'A'] = new int[]{0, -1};
        dirs['W' - 'A'] = new int[]{-1, 0};
        dirs['E' - 'A'] = new int[]{1, 0};
    }

    public boolean isPathCrossing(String path) {
        Map<Integer, Set<Integer>> visited = new HashMap<>();
        visited.computeIfAbsent(0, k -> new HashSet<>()).add(0);
        int x = 0, y = 0;
        for (char c : path.toCharArray()) {
            x += dirs[c - 'A'][0];
            y += dirs[c - 'A'][1];
            if (visited.containsKey(x) && visited.get(x).contains(y)) {
                return true;
            }
            visited.computeIfAbsent(x, k -> new HashSet<>()).add(y);
        }
        return false;
    }
}
