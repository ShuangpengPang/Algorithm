package com.shuangpeng.Problem.p0201_0300;

public class Problem0289GameOfLife {

    // 1 -> 1 : 2, 3
    // 0 -> 1 : 3

    // 1 -> 0 :: 3
    // 0 -> 1 :: 2
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int[] coord = {-1, 0, 1};
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = getLiveCount(board, coord, m, n, i, j);
                boolean live = board[i][j] == 1;
                if (live && (count < 2 || count > 3)) {
                    board[i][j] = 3;
                } else if (!live && count == 3) {
                    board[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 3) {
                    board[i][j] = 0;
                } else if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
            }
        }
    }

    private int getLiveCount(int[][] board, int[] coord,
                             int m, int n, int i, int j) {
        int count = 0;
        for (int a = 0; a < coord.length; a++) {
            for (int b = 0; b < coord.length; b++) {
                int x = i + coord[a];
                int y = j + coord[b];
                if ((x == i && y == j) || x < 0 || x >= m || y < 0 || y >= n) {
                    continue;
                }
                count += (board[x][y] & 1);
            }
        }
        return count;
    }
}
