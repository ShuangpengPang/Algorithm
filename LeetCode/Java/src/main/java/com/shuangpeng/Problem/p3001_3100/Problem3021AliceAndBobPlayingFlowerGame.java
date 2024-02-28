package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3021AliceAndBobPlayingFlowerGame（Alice和Bob玩鲜花游戏）
 * @date 2024/2/28 6:03 PM
 */
public class Problem3021AliceAndBobPlayingFlowerGame {

    public long flowerGame0(int n, int m) {
        return (long) (n >> 1) * (m + 1 >> 1) + (long) (n + 1 >> 1) * (m >> 1);
    }

    public long flowerGame(int n, int m) {
        return (long) n * m >> 1;
    }
}
