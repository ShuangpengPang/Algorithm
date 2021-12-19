package com.shuangpeng.Problem.p0401_0500;

public class Problem0419BattleshipsInABoard {

    public int countBattleships(char[][] board) {
        int m = board.length, n = board[0].length;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'X') {
                    if ((j == 0 || board[i][j - 1] == '.') && (i == 0 || board[i - 1][j] == '.')) {
                        ++ans;
                    }
                }
            }
        }
        return ans;
    }
}
