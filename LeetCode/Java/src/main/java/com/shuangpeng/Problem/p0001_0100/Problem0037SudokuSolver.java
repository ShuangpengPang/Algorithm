package com.shuangpeng.Problem.p0001_0100;

import java.util.ArrayList;
import java.util.List;

public class Problem0037SudokuSolver {

    public void solveSudoku0(char[][] board) {
        int n = board.length;
        boolean[][] rows = new boolean[n][n];
        boolean[][] columns = new boolean[n][n];
        boolean[][] blocks = new boolean[n][n];
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                char c = board[i][j];
                if (c == '.') {
                    list.add(new int[]{i, j});
                } else {
                    rows[i][c - '1'] = true;
                    columns[j][c - '1'] = true;
                    blocks[i / 3 * 3 + j / 3][c - '1'] = true;
                }
            }
        }
        dfs(board, rows, columns, blocks, list, 0, new boolean[]{false});
    }

    private void dfs(char[][] board, boolean[][] rows, boolean[][] columns, boolean[][] blocks, List<int[]> list, int pos, boolean[] res) {
        if (pos == list.size()) {
            res[0] = true;
            return;
        }
        int i = list.get(pos)[0], j = list.get(pos)[1];
        for (int d = 0; d < 9; ++d) {
            if (!res[0] && !rows[i][d] && !columns[j][d] && !blocks[i / 3 * 3 + j / 3][d]) {
                board[i][j] = (char) ('1' + d);
                rows[i][d] = columns[j][d] = blocks[i / 3 * 3 + j / 3][d] = true;
                dfs(board, rows, columns, blocks, list, pos + 1, res);
                rows[i][d] = columns[j][d] = blocks[i / 3 * 3 + j / 3][d] = false;
            }
        }
    }

    public void solveSudoku(char[][] board) {
        int n = board.length;
        int[] rows = new int[n];
        int[] columns = new int[n];
        int[][] blocks = new int[n / 3][n / 3];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] != '.') {
                    flip(rows, columns, blocks, i, j, board[i][j] - '1');
                }
            }
        }
        boolean modified = true;
        while (modified) {
            modified = false;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (board[i][j] == '.') {
                        int mask = ~(rows[i] | columns[j] | blocks[i / 3][j / 3]) & 0x1ff;
                        if (mask != 0 && (mask & (mask - 1)) == 0) {
                            int d = Integer.bitCount((mask & -mask) - 1);
                            flip(rows, columns, blocks, i, j, d);
                            board[i][j] = (char) ('1' + d);
                            modified = true;
                        }
                    }
                }
            }
        }
        List<int[]> spaces = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                }
            }
        }
        dfs(board, rows, columns, blocks, spaces, 0, new boolean[]{false});
    }

    private void flip(int[] rows, int[] columns, int[][] blocks, int i, int j, int digit) {
        rows[i] ^= 1 << digit;
        columns[j] ^= 1 << digit;
        blocks[i / 3][j / 3] ^= 1 << digit;
    }

    private void dfs(char[][] board, int[] rows, int[] columns, int[][] blocks, List<int[]> spaces, int pos, boolean[] res) {
        if (pos == spaces.size()) {
            res[0] = true;
            return;
        }
        int i = spaces.get(pos)[0], j = spaces.get(pos)[1];
        int mask = ~(rows[i] | columns[j] | blocks[i / 3][j / 3]) & 0x1ff;
        for (; !res[0] && mask != 0; mask &= mask - 1) {
            int d = Integer.bitCount((mask & -mask) - 1);
            flip(rows, columns, blocks, i, j, d);
            board[i][j] = (char) ('1' + d);
            dfs(board, rows, columns, blocks, spaces, pos + 1, res);
            flip(rows, columns, blocks, i, j, d);
        }
    }
}
