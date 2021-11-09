package com.shuangpeng.Problem.p0901_1000;

import java.util.LinkedList;
import java.util.Queue;

public class Problem0909SnakesAndLadders {

    public int snakesAndLadders0(int[][] board) {
        int N = board.length * board.length;
        int S = 6;
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int s = queue.poll();
                for (int j = 1; j <= S; j++) {
                    int k = s + j;
                    if (k == N) {
                        return step;
                    }
                    if (!visited[k]) {
                        visited[k] = true;
                        int value = getValue(board, k);
                        if (value == -1) {
                            queue.offer(k);
                        } else if (value == N) {
                            return step;
                        } else if (value != -1 && !visited[value]) {
                            queue.offer(value);
                        }
                    }
                }
            }
        }
        return -1;
    }

    private int getValue(int[][] board, int i) {
        int n = board.length;
        int j = i / n, k = i % n;
        if (k == 0) {
            if ((j & 1) == 0) {
                return board[n - j][0];
            }
            return board[n - j][n - 1];
        }
        if ((j & 1) == 0) {
            return board[n - j - 1][k - 1];
        }
        return board[n - j - 1][n - k];
    }

    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] vis = new boolean[n * n + 1];
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{1, 0});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            for (int i = 1; i <= 6; ++i) {
                int nxt = p[0] + i;
                if (nxt > n * n) { // 超出边界
                    break;
                }
                int[] rc = id2rc(nxt, n); // 得到下一步的行列
                if (board[rc[0]][rc[1]] > 0) { // 存在蛇或梯子
                    nxt = board[rc[0]][rc[1]];
                }
                if (nxt == n * n) { // 到达终点
                    return p[1] + 1;
                }
                if (!vis[nxt]) {
                    vis[nxt] = true;
                    queue.offer(new int[]{nxt, p[1] + 1}); // 扩展新状态
                }
            }
        }
        return -1;
    }

    public int[] id2rc(int id, int n) {
        int r = (id - 1) / n, c = (id - 1) % n;
        if (r % 2 == 1) {
            c = n - 1 - c;
        }
        return new int[]{n - 1 - r, c};
    }

//    public static void main(String[] args) {
//        Problem0909SnakesAndLadders a = new Problem0909SnakesAndLadders();
//        a.snakesAndLadders(new int[][]{{1, 1, -1}, {1, 1, 1}, {-1, 1, 1}});
//    }

//    private int getIndex(int[][] board, int i, int j) {
//        int n = board.length;
//        int r = n - i - 1;
//        if ((r & 1) == 0) {
//            return n * r + j + 1;
//        }
//        return n * r + n - j;
//    }
}
