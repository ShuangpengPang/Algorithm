package com.shuangpeng.Problem.p0701_0800;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Problem0780ReachingPoints {

    // TLE
    public boolean reachingPoints0(int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) {
            return true;
        }
        final long M = (long) 1e9 + 7;
        long current = sx * M + sy;
        Set<Long> seen = new HashSet<>();
        Queue<Long> q = new LinkedList<>();
        seen.add(current);
        q.offer(current);
        while (!q.isEmpty()) {
            long h = q.poll();
            long x = h / M, y = h % M;
            long s = x + y;
            if ((s == tx && y == ty) || (x == tx && s == ty)) {
                return true;
            }
            if (s <= tx) {
                long hash = s * M + y;
                if (!seen.contains(hash)) {
                    seen.add(hash);
                    q.offer(hash);
                }
            }
            if (s <= ty) {
                long hash = x * M + s;
                if (!seen.contains(hash)) {
                    seen.add(hash);
                    q.offer(hash);
                }
            }
        }
        return false;
        // x, y
        // x + y,  y
        // x, x + y
        // x + x + y,  x + y
        // (x + x + y) + x + y, x + y
    }

    public boolean reachingPoints1(int sx, int sy, int tx, int ty) {
        while ((tx != ty ) && (tx > sx || ty > sy)) {
            if (tx > ty) {
                int temp = tx;
                tx = (tx - sx) % ty;
                tx += sx;
                if (temp == tx) {
                    break;
                }
            } else {
                int temp = ty;
                ty = (ty - sy) % tx;
                ty += sy;
                if (temp == ty) {
                    break;
                }
            }
        }
        return sx == tx && sy == ty;
    }

    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx >= sx && ty >= sy) {
            if (tx == ty || (tx == sx && ty == sy)) {
                break;
            }
            if (tx > ty) {
                if (ty > sy) {
                    tx %= ty;
                } else {
                    return (tx - sx) % ty == 0;
                }
            } else {
                if (tx > sx) {
                    ty %= tx;
                } else {
                    return (ty - sy)% tx == 0;
                }
            }
        }
        return sx == tx && sy == ty;
    }
}
