package com.shuangpeng.Problem.p1401_1500;

import java.util.HashSet;
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
