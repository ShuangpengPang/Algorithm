package com.shuangpeng.competition.双周赛.第058场双周赛;

public class Problem1958 {

    public boolean checkMove0(char[][] board, int rMove, int cMove, char color) {
        int n = 8;
        int r = rMove, c = cMove;
        while (r - 1 >= 0 && board[r - 1][c] != '.' && board[r - 1][c] != color) {
            r--;
        }
        if (r - 1 >= 0 && r - 1 < rMove - 1 && board[r - 1][c] == color) {
            return true;
        }
        r = rMove - 1;
        c = cMove + 1;
        while (r >= 0 && c < n && board[r][c] != '.' && board[r][c] != color) {
            r--;
            c++;
        }
        if (r >= 0 && c < n && rMove - r >= 2 && board[r][c] == color) {
            return true;
        }
        r = rMove;
        c = cMove + 1;
        while (c < n && board[r][c] != '.' && board[r][c] != color) {
            c++;
        }
        if (c < n && c - cMove >= 2 && board[r][c] == color) {
            return true;
        }
        r = rMove + 1;
        c = cMove + 1;
        while (r < n && c < n && board[r][c] != '.' && board[r][c] != color) {
            r++;
            c++;
        }
        if (r < n && c < n && r - rMove >= 2 && board[r][c] == color) {
            return true;
        }
        r = rMove + 1;
        c = cMove;
        while (r < n && board[r][c] != '.' && board[r][c] != color) {
            r++;
        }
        if (r < n && r - rMove >= 2 && board[r][c] == color) {
            return true;
        }
        r = rMove + 1;
        c = cMove - 1;
        while (r < n && c >= 0 && board[r][c] != '.' && board[r][c] != color) {
            r++;
            c--;
        }
        if (r < n && c >= 0 && r - rMove >= 2 && board[r][c] == color) {
            return true;
        }
        r = rMove;
        c = cMove - 1;
        while (c >= 0 && board[r][c] != '.' && board[r][c] != color) {
            c--;
        }
        if (c >= 0 && cMove - c >= 2 && board[r][c] == color) {
            return true;
        }
        r = rMove - 1;
        c = cMove - 1;
        while (r >= 0 && c >= 0 && board[r][c] != '.' && board[r][c] != color) {
            r--;
            c--;
        }
        if (r >= 0 && c >= 0 && rMove - r >= 2 && board[r][c] == color) {
            return true;
        }
        return false;
    }

    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        int n = board.length;
        int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
        for (int[] dir : dirs) {
            int r = rMove + dir[0];
            int c = cMove + dir[1];
            while (r >= 0 && r < n && c >= 0 && c < n && board[r][c] != '.' && board[r][c] != color) {
                r += dir[0];
                c += dir[1];
            }
            if (r >= 0 && r < n && c >= 0 && c < n && board[r][c] == color
                    && (Math.abs(rMove - r) >= 2 || Math.abs(cMove - c) >= 2)) {
                return true;
            }
        }
        return false;
    }
}
