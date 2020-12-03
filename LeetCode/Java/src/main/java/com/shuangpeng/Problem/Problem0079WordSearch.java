package com.shuangpeng.Problem;

public class Problem0079WordSearch {

//    [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
//            "ABCCED"

    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        if (word.length() > rows * cols) {
            return false;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        char c = word.charAt(0);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (c == board[i][j] && search(board, word, visited, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public boolean search(char[][] board, String word, boolean[][] visited, int i, int j, int index) {
        if (word.charAt(index) != board[i][j]) {
            return false;
        }
        if (word.length() == index + 1) {
            return true;
        }
        visited[i][j] = true;
        for (int m = 0; m < move.length; m++) {
            int newI = i + move[m][0];
            int newJ = j + move[m][1];
            if (newI >= 0 && newI < board.length && newJ >= 0
                    && newJ < board[0].length && !visited[newI][newJ]) {
                if (search(board, word, visited, newI, newJ, index + 1)) {
                    return true;
                }
            }
        }
        visited[i][j] = false;
        return false;
    }
}
