package com.shuangpeng.Problem.p1601_1700;

/**
 * @Description: Problem1620CoordinateWithMaximumNetworkQuality（网络信号最好的坐标）
 * @Date 2022/11/2 10:25 AM
 * @Version 1.0
 */
public class Problem1620CoordinateWithMaximumNetworkQuality {

    public int[] bestCoordinate0(int[][] towers, int radius) {
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        for (int[] tower : towers) {
            int x = tower[0], y = tower[1];
            minX = Math.min(minX, x - radius);
            maxX = Math.max(maxX, x + radius);
            minY = Math.min(minY, y - radius);
            maxY = Math.max(maxY, y + radius);
        }
        minX = Math.min(minX, 0);
        minY = Math.min(minY, 0);
        int maxQuality = -1;
        int X = -1, Y = -1;
        int square = radius * radius;
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                int q = 0;
                for (int[] tower : towers) {
                    int x = tower[0], y = tower[1];
                    int v = (i - x) * (i - x) + (j - y) * (j - y);
                    if (v > square) {
                        continue;
                    }
                    q += (int) ((double) tower[2] / (1 + Math.sqrt(v)));
                }
                if (q > maxQuality) {
                    maxQuality = q;
                    X = i;
                    Y = j;
                } else if (q == maxQuality) {
                    if (X >= 0 && Y >= 0 && (i < 0 || j < 0)) {
                        continue;
                    }
                    if ((X < 0 || Y < 0) && (i >= 0 && j >= 0)) {
                        X = i;
                        Y = j;
                    } else if (i < X || (i == X && j < Y)) {
                        X = i;
                        Y = j;
                    }
                }
            }
        }
        return new int[]{X, Y};
    }

    public int[] bestCoordinate(int[][] towers, int radius) {
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        for (int[] tower : towers) {
            maxX = Math.max(maxX, tower[0]);
            maxY = Math.max(maxY, tower[1]);
        }
        int maxQ = -1, x = 0, y = 0, square = radius * radius;
        for (int i = 0; i <= maxX; i++) {
            for (int j = 0; j <= maxY; j++) {
                int q = 0;
                for (int[] tower : towers) {
                    int tx = tower[0] - i, ty = tower[1] - j;
                    int v = tx * tx + ty * ty;
                    if (v > square) {
                        continue;
                    }
                    q += (int) ((double) tower[2] / (1 + Math.sqrt(v)));
                }
                if (q > maxQ) {
                    maxQ = q;
                    x = i;
                    y = j;
                }
            }
        }
        return new int[]{x, y};
    }
}