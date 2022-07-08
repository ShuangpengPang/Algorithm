package com.shuangpeng.Problem.p1201_1300;

/**
 * @Description: Problem1217MinimumCostToMoveChipsToTheSamePosition（玩筹码）
 * @Date 2022/7/8 9:47 AM
 * @Version 1.0
 */
public class Problem1217MinimumCostToMoveChipsToTheSamePosition {

    public int minCostToMoveChips(int[] position) {
        int a = 0, b = 0;
        for (int p : position) {
            if ((p & 1) == 0) {
                a++;
            } else {
                b++;
            }
        }
        return Math.min(a, b);
    }
}
