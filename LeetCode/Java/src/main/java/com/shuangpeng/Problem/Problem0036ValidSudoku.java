package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.List;

public class Problem0036ValidSudoku {

    public boolean isValidSudoku0(char[][] board) {
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

    // i, j
    // 3 * (i / 3) + (j / 3)

//    public static void main(String[] args) {
//        char[][] board = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
//        Problem0036ValidSudoku a = new Problem0036ValidSudoku();
//        a.isValidSudoku(board);
//    }

    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9
                || board[0] == null || board[0].length != 9) {
            return false;
        }
        int length = 9;
        List<boolean[]> row = new ArrayList<>(length);
        List<boolean[]> col = new ArrayList<>(length);
        List<boolean[]> sub = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            row.add(new boolean[length + 1]);
            col.add(new boolean[length + 1]);
            sub.add(new boolean[length + 1]);
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (board[i][j] != '.') {
                    int digit = board[i][j] - '0';
                    if (row.get(i)[digit]) {
                        return false;
                    }
                    if (col.get(j)[digit]) {
                        return false;
                    }
                    int index = i / 3 * 3 + j / 3;
                    if (sub.get(index)[digit]) {
                        return false;
                    }
                    row.get(i)[digit] = true;
                    col.get(j)[digit] = true;
                    sub.get(index)[digit] = true;
                }
            }
        }
        return true;
    }
}
