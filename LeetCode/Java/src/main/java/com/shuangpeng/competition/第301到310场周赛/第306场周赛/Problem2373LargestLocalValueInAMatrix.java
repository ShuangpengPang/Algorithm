package com.shuangpeng.competition.第301到310场周赛.第306场周赛;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: Problem2373LargestLocalValueInAMatrix（矩阵中的局部最大值）
 * @Date 2022/9/1 5:51 PM
 * @Version 1.0
 */
public class Problem2373LargestLocalValueInAMatrix {

    // 比赛时写法
    public int[][] largestLocal0(int[][] grid) {
        int n = grid.length;
        int[][] ans = new int[n - 2][n - 2];
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                for (int x = i; x < i + 3; x++) {
                    for (int y = j; y < j + 3; y++) {
                        ans[i][j] = Math.max(ans[i][j], grid[x][y]);
                    }
                }
            }
        }
        return ans;
    }

    public int[][] largestLocal1(int[][] grid) {
        int n = grid.length;
        int[][] ans = new int[n - 2][n - 2];
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                int max = 0;
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        max = Math.max(max, grid[i + x][j + y]);
                    }
                }
                ans[i][j] = max;
            }
        }
        return ans;
    }

    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] row = new int[n][n - 2];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            q.clear();
            for (int j = 0; j < n; j++) {
                while (!q.isEmpty() && grid[i][q.peekLast()] <= grid[i][j]) {
                    q.pollLast();
                }
                q.offerLast(j);
                while (!q.isEmpty() && q.peekFirst() <= j - 3) {
                    q.pollFirst();
                }
                if (j >= 2) {
                    row[i][j - 2] = grid[i][q.peekFirst()];
                }
            }
        }
        int[][] ans = new int[n - 2][n - 2];
        for (int j = 0; j < n - 2; j++) {
            q.clear();
            for (int i = 0; i < n; i++) {
                while (!q.isEmpty() && row[q.peekLast()][j] <= row[i][j]) {
                    q.pollLast();
                }
                q.offerLast(i);
                while (!q.isEmpty() && q.peekFirst() <= i - 3) {
                    q.pollFirst();
                }
                if (i >= 2) {
                    ans[i - 2][j] = row[q.peekFirst()][j];
                }
            }
        }
        return ans;
    }
}
