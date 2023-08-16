package com.shuangpeng.Problem.p2601_2700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2660DetermineTheWinnerOfABowlingGame（保龄球游戏的获胜者）
 * @date 2023/8/16 12:40 AM
 */
public class Problem2660DetermineTheWinnerOfABowlingGame {

    public int isWinner(int[] player1, int[] player2) {
        int n = player1.length, diff = 0;
        for (int i = 0; i < n; i++) {
            diff += i >= 1 && player1[i - 1] == 10 || i >= 2 && player1[i - 2] == 10 ? player1[i] << 1 : player1[i];
            diff -= i >= 1 && player2[i - 1] == 10 || i >= 2 && player2[i - 2] == 10 ? player2[i] << 1 : player2[i];
        }
        return diff > 0 ? 1 : diff == 0 ? 0 : 2;
    }
}
