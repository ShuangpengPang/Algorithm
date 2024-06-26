package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3175FindTheFirstPlayerToWinKGamesInARow（找到连续赢K场比赛的第一个玩家）
 * @date 2024/6/13 12:13 AM
 */
public class Problem3175FindTheFirstPlayerToWinKGamesInARow {

    public int findWinningPlayer(int[] skills, int k) {
        int n = skills.length, idx = 0, win = 0;
        for (int i = 1; i < n && win < k; i++) {
            if (skills[idx] < skills[i]) {
                idx = i;
                win = 0;
            }
            win++;
        }
        return idx;
    }
}
