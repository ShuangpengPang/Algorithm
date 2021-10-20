package com.shuangpeng.competition.第260场周赛;

public class Problem2018 {

    public boolean placeWordInCrossword(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        int length = word.length();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j + length - 1 < n; ++j) {
                if (board[i][j] != '#' && (j == 0 || board[i][j - 1] == '#')) {
                    if (checkHorizontally(board, i, j, word)) {
                        return true;
                    }
                }
            }
        }
        for (int i = 0; i + length - 1 < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] != '#' && (i == 0 || board[i - 1][j] == '#')) {
                    if (checkVertically(board, i, j, word)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkHorizontally(char[][] board, int i, int j, String word) {
        int length = word.length();
        boolean valid = true;
        for (int k = 0; k < length; ++k) {
            char c = board[i][j + k];
            if (c == '#') {
                return false;
            }
            if (c != ' ' && c != word.charAt(k)) {
                valid = false;
                break;
            }
        }
        if (!valid) {
            for (int k = 0; k < length; ++k) {
                char c = board[i][j + k];
                if (c != ' ' && c != word.charAt(length - k - 1)) {
                    return false;
                }
            }
        }
        return j + length == board[0].length || board[i][j + length] == '#';
    }

    private boolean checkVertically(char[][] board, int i, int j, String word) {
        int length = word.length();
        boolean valid = true;
        for (int k = 0; k < length; ++k) {
            char c = board[i + k][j];
            if (c == '#') {
                return false;
            }
            if (c != ' ' && c != word.charAt(k)) {
                valid = false;
                break;
            }
        }
        if (!valid) {
            for (int k = 0; k < length; ++k) {
                char c = board[i + k][j];
                if (c != ' ' && c != word.charAt(length - k - 1)) {
                    return false;
                }
            }
        }
        return i + length == board.length || board[i + length][j] == '#';
    }
}
