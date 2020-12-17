package com.shuangpeng.Problem;

import java.util.LinkedList;
import java.util.Queue;

public class Problem0130SurroundedRegions {

    public void solve0(char[][] board) {
        if (board == null || board.length == 0
                || board[0] == null || board[0].length == 0) {
            return;
        }
        int rows = board.length;
        int cols = board[0].length;
        int[][] parent = new int[rows][cols];
        int[][] setSize = new int[rows][cols];
        char ch = 'O';
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == ch) {
                    int value = i * cols + j;
                    if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
                        parent[i][j] = -value;
                        setSize[i][j] = 1;
                    } else {
                        parent[i][j] = value;
                        setSize[i][j] = 1;
                    }
                    if (i > 0 && board[i - 1][j] == ch) {
                        int parent1 = findParent(parent, i - 1, j, cols);
                        int parent2 = findParent(parent, i, j, cols);
                        union(parent, setSize, parent1, parent2, cols);
                    }
                    if (j > 0 && board[i][j - 1] == ch) {
                        int parent1 = findParent(parent, i, j - 1, cols);
                        int parent2 = findParent(parent, i, j, cols);
                        union(parent, setSize, parent1, parent2, cols);
                    }
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == ch && findParent(parent, i, j, cols) > 0) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public int findParent(int[][] parent, int i, int j, int cols) {
        while (parent[i][j] > 0 && parent[i][j] != (i * cols + j)) {
            int value = parent[i][j];
            int x = value / cols;
            int y = value % cols;
            parent[i][j] = parent[x][y];
            value = parent[x][y];
            value = value > 0 ? value : -value;
            i = value / cols;
            j = value % cols;
        }
        return parent[i][j];
    }

    public void union(int[][] parent, int[][] setSize, int parent1, int parent2, int cols) {
        int value1 = parent1 > 0 ? parent1 : -parent1;
        int value2 = parent2 > 0 ? parent2 : -parent2;
        int i = value1 / cols;
        int j = value1 % cols;
        int m = value2 / cols;
        int n = value2 % cols;
        if ((parent1 <= 0 && parent2 <= 0) || (parent1 > 0 && parent2 > 0)) {
            if (setSize[i][j] >= setSize[m][n]) {
                parent[m][n] = parent[i][j];
                setSize[i][j] += setSize[m][n];
            } else if (setSize[i][j] < setSize[m][n]) {
                parent[i][j] = parent[m][n];
                setSize[m][n] += setSize[i][j];
            }
        } else if (parent1 <= 0) {
            parent[m][n] = parent[i][j];
            setSize[i][j] += setSize[m][n];
        } else {
            parent[i][j] = parent[m][n];
            setSize[m][n] += setSize[i][j];
        }
    }

    public void solve1(char[][] board) {
        if (board == null || board.length == 0
                || board[0] == null || board[0].length == 0) {
            return;
        }
        int rows = board.length;
        int cols = board[0].length;
        char ch = 'O';
        for (int i = 0; i < cols; i++) {
            if (board[0][i] == ch) {
                dfs(board, 0, i);
            }
            if (board[rows - 1][i] == ch) {
                dfs(board, rows - 1, i);
            }
        }
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == ch) {
                dfs(board, i, 0);
            }
            if (board[i][cols - 1] == ch) {
                dfs(board, i, cols - 1);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == '*') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board, int i, int j) {
        board[i][j] = '*';
        int rows = board.length;
        int cols = board[0].length;
        int[][] coord = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        for (int k = 0; k < coord.length; k++) {
            int x = i + coord[k][0];
            int y = j + coord[k][1];
            if (x >= 0 && x < rows && y >= 0 && y < cols && board[x][y] == 'O') {
                dfs(board, x, y);
            }
        }
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0
                || board[0] == null || board[0].length == 0) {
            return;
        }
        int rows = board.length;
        int cols = board[0].length;
        char ch = 'O';
        for (int i = 0; i < cols; i++) {
            if (board[0][i] == ch) {
                bfs(board, 0, i);
            }
            if (board[rows - 1][i] == ch) {
                bfs(board, rows - 1, i);
            }
        }
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == ch) {
                bfs(board, i, 0);
            }
            if (board[i][cols - 1] == ch) {
                bfs(board, i, cols - 1);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == '*') {
                    board[i][j] = ch;
                } else if (board[i][j] == ch) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void bfs(char[][] board, int i, int j) {
        int[][] coord = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        int rows = board.length;
        int cols = board[0].length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i * cols + j);
        board[i][j] = '*';
        while (!queue.isEmpty()) {
            int value = queue.poll();
            int m = value / cols;
            int n = value % cols;
            for (int k = 0; k < coord.length; k++) {
                int x = m + coord[k][0];
                int y = n + coord[k][1];
                if (x >= 0 && x < rows && y >= 0 && y < cols && board[x][y] == 'O') {
                    board[x][y] = '*';
                    queue.offer(x * cols + y);
                }
            }
        }
    }
}
