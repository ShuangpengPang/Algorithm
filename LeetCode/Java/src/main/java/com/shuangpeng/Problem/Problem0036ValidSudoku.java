package com.shuangpeng.Problem;

public class Problem0036ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9
                || board[0] == null || board[0].length != 9) {
            return false;
        }
        int length = board.length;
        for (int i = 0; i < length; i++) {
            boolean[] row = new boolean[length + 1];
            boolean[] col = new boolean[length + 1];
            for (int j = 0; j < length; j++) {
                if (board[i][j] != '.') {
                    if (row[board[i][j] - '0']) {
                        return false;
                    }
                    row[board[i][j] - '0'] = true;
                }
                if (board[j][i] != '.') {
                    if (col[board[j][i] - '0']) {
                        return false;
                    }
                    col[board[j][i] - '0'] = true;
                }
            }
        }
        for (int height = 3; height <= length; height += 3) {
            for (int width = 3; width <= length; width += 3) {
                boolean[] visited = new boolean[length + 1];
                for (int i = height - 3; i < height; i++) {
                    for (int j = width - 3; j < width; j++) {
                        if (board[i][j] != '.') {
                            if (visited[board[i][j] - '0']) {
                                return false;
                            }
                            visited[board[i][j] - '0'] = true;
                        }
                    }
                }
            }
        }
        return true;
    }
}
