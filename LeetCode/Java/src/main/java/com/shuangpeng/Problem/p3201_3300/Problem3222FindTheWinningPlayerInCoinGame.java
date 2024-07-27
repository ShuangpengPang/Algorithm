package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3222FindTheWinningPlayerInCoinGame（求出硬币游戏的赢家）
 * @date 2024/7/28 12:41 AM
 */
public class Problem3222FindTheWinningPlayerInCoinGame {

    public String losingPlayer(int x, int y) {
        return (Math.min(x, y >> 2) & 1) == 1 ? "Alice" : "Bob";
    }
}
