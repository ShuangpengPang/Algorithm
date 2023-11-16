package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2249CountLatticePointsInsideACircle（统计园内格点数目）
 * @date 2023/11/16 9:40 AM
 */
public class Problem2249CountLatticePointsInsideACircle {

    public int countLatticePoints(int[][] circles) {
        int N = Integer.MAX_VALUE;
        int x1 = N, x2 = -N, y1 = N, y2 = -N;
        for (int[] circle : circles) {
            int x = circle[0], y = circle[1], r = circle[2];
            x1 = Math.min(x1, x - r);
            x2 = Math.max(x2, x + r);
            y1 = Math.min(y1, y - r);
            y2 = Math.max(y2, y + r);
        }
        int ans = 0;
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                for (int[] circle : circles) {
                    int d1 = x - circle[0], d2 = y - circle[1], r = circle[2];
                    if (d1 * d1 + d2 * d2 <= r * r) {
                        ans++;
                        break;
                    }
                }
            }
        }
        return ans;
    }
}
