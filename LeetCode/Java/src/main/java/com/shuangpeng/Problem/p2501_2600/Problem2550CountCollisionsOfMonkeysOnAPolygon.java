package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2550CountCollisionsOfMonkeysOnAPolygon（猴子碰撞的方法数）
 * @date 2023/11/29 1:45 PM
 */
public class Problem2550CountCollisionsOfMonkeysOnAPolygon {

    public int monkeyMove(int n) {
        long ans = 1, e = 2, N = (int) 1e9 + 7;
        while (n > 0) {
            if ((n & 1) == 1) {
                ans = ans * e % N;
            }
            e = (e * e) % N;
            n >>= 1;
        }
        return (int ) ((ans + N - 2) % N);
    }
}
