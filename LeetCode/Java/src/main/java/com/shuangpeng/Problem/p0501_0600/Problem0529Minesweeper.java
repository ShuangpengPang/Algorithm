package com.shuangpeng.Problem.p0501_0600;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0529Minesweeper（扫雷游戏）
 * @date 2023/2/2 3:46 PM
 */
public class Problem0529Minesweeper {

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        int m = board.length, n = board[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.offer(click);
        board[click[0]][click[1]] = '.';
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int x = p[0], y = p[1], c = 0;
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    int nx = x + i, ny = y + j;
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                        continue;
                    }
                    if (board[nx][ny] == 'M') {
                        c++;
                    }
                }
            }
            if (c != 0) {
                board[x][y] = (char) ('0' + c);
            } else {
                board[x][y] = 'B';
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        int nx = x + i, ny = y + j;
                        if (nx < 0 || nx >= m || ny < 0 || ny >= n || board[nx][ny] != 'E') {
                            continue;
                        }
                        board[nx][ny] = '.';
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        return board;
    }
}
