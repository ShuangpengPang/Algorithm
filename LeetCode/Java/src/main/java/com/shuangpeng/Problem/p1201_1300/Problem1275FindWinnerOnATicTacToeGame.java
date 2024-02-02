package com.shuangpeng.Problem.p1201_1300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1275FindWinnerOnATicTacToeGame（找出井字棋的获胜者）
 * @date 2024/2/2 9:33 AM
 */
public class Problem1275FindWinnerOnATicTacToeGame {

    public String tictactoe(int[][] moves) {
        int n = moves.length, N = 3;
        int[] rows = new int[N], cols = new int[N], diagonal = new int[2];
        String[] winner = {"A", "B"};
        for (int i = 0; i < n; i++) {
            int x = moves[i][0], y = moves[i][1], d = ((i & 1) << 1) - 1;
            rows[x] += d;
            cols[y] += d;
            if (x == y) {
                diagonal[0] += d;
            }
            if (x + y == N - 1) {
                diagonal[1] += d;
            }
            if (Math.abs(rows[x]) == N || Math.abs(cols[y]) == N || Math.abs(diagonal[0]) == N || Math.abs(diagonal[1]) == N) {
                return winner[i & 1];
            }
        }
        return n < 9 ? "Pending" : "Draw";
    }
}
