package com.shuangpeng.Problem.p1201_1300;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: Problem1284MinimumNumberOfFlipsToConvertBinaryMatrixToZeroMatrix（转化为全零矩阵的最少翻转次数）
 * @Date 2022/7/29 3:18 PM
 * @Version 1.0
 */
public class Problem1284MinimumNumberOfFlipsToConvertBinaryMatrixToZeroMatrix {

    public int minFlips0(int[][] mat) {
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

class Problem1284MinimumNumberOfFlipsToConvertBinaryMatrixToZeroMatrix0 {

    int[] dirs = {-1, 0, 1, 0, -1};
    int[][] mat;
    int m, n, ans;

    public int minFlips(int[][] mat) {
        this.mat = mat;
        this.m = mat.length;
        this.n = mat[0].length;
        ans = Integer.MAX_VALUE;
        dfs(0, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void dfs(int index, int count) {
        if (count >= ans) {
            return;
        }
        if (index == m * n) {
            boolean valid = true;
            for (int i = 0; i < n; i++) {
                if (mat[m - 1][i] == 1) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                ans = count;
            }
            return;
        }
        int x = index / n, y = index % n;
        if (x == 0) {
            flip(x, y);
            dfs(index + 1, count + 1);
            flip(x, y);
            dfs(index + 1, count);
        } else {
            if (mat[x - 1][y] == 1) {
                flip(x, y);
                dfs(index + 1, count + 1);
                flip(x, y);
            } else {
                dfs(index + 1, count);
            }
        }
    }

    private void flip(int i, int j) {
        mat[i][j] ^= 1;
        for (int d = 0; d < 4; d++) {
            int x = i + dirs[d], y = j + dirs[d + 1];
            if (x >= 0 && x < m && y >= 0 && y < n) {
                mat[x][y] ^= 1;
            }
        }
    }
}

class Problem1284MinimumNumberOfFlipsToConvertBinaryMatrixToZeroMatrix1 {

    int[] dirs = {0, 0, -1, 0, 1, 0};

    public int minFlips(int[][] mat) {
        int m = mat.length, n = mat[0].length, M = m * n, N = 1 << n;
        int ans = Integer.MAX_VALUE;
        for (int b = 0; b < N; b++) {
            int[][] arr = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = mat[i][j];
                }
            }
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (((b >> i) & 1) == 1) {
                    count++;
                    if (count >= ans) {
                        break;
                    }
                    flip(arr, 0, i);
                }
            }
            for (int i = 1; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i - 1][j] == 1) {
                        count++;
                        if (count >= ans) {
                            break;
                        }
                        flip(arr, i, j);
                    }
                }
            }
            if (count >= ans) {
                continue;
            }
            boolean valid = true;
            for (int i = 0; i < n; i++) {
                if (arr[m - 1][i] == 1) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                ans = count;
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void flip(int[][] mat, int x, int y) {
        int m = mat.length, n = mat[0].length;
        for (int d = 0; d < 5; d++) {
            int i = x + dirs[d], j = y + dirs[d + 1];
            if (i >= 0 && i < m && j >= 0 && j < n) {
                mat[i][j] ^= 1;
            }
        }
    }
}


