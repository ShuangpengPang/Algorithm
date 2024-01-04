package com.shuangpeng.Problem.p0901_1000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0999AvailableCapturesForRook（可以被一步捕获的棋子数）
 * @date 2024/1/4 12:58 PM
 */
public class Problem0999AvailableCapturesForRook {

    private static int[] dirs = {-1, 0, 1, 0, -1};

    public int numRookCaptures(char[][] board) {
        int m = board.length, n = board[0].length;
        int x = 0, y = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'R') {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        int cnt = 0;
        for (int d = 0; d < 4; d++) {
            for (int i = x + dirs[d], j = y + dirs[d + 1]; i >= 0 && i < m && j >= 0 && j < n; i += dirs[d], j += dirs[d + 1]) {
                char c = board[i][j];
                if (c == 'p' || c == 'B') {
                    if (c == 'p') {
                        cnt++;
                    }
                    break;
                }
            }
        }
        return cnt;
    }
}
