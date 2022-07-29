package com.shuangpeng.Problem.p1201_1300;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: Problem1284MinimumNumberOfFlipsToConvertBinaryMatrixToZeroMatrix（转化为全零矩阵的最少翻转次数）
 * @Date 2022/7/29 3:18 PM
 * @Version 1.0
 */
public class Problem1284MinimumNumberOfFlipsToConvertBinaryMatrixToZeroMatrix {

    public int minFlips(int[][] mat) {
        int m = mat.length, n = mat[0].length, N = m * n;
        int s = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                s |= mat[i][j] << (i * n + j);
            }
        }
        if (s == 0) {
            return 0;
        }
        boolean[] visited = new boolean[1 << N];
        Queue<Integer> q = new LinkedList<>();
        visited[s] = true;
        q.offer(s);
        int flip = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            flip++;
            for (int c = q.size() - 1; c >= 0; c--) {
                int cur = q.poll();
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        int next = cur;
                        next ^= 1 << (i * n + j);
                        for (int d = 0; d < 4; d++) {
                            int x = i + dirs[d], y = j + dirs[d + 1];
                            if (x >= 0 && x < m && y >= 0 && y < n) {
                                next ^= 1 << (x * n + y);
                            }
                        }
                        if (next == 0) {
                            return flip;
                        }
                        if (!visited[next]) {
                            visited[next] = true;
                            q.offer(next);
                        }
                    }
                }
            }
        }
        return -1;
    }
}
