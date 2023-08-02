package com.shuangpeng.Problem.p0801_0900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0822CardFlippingGame（翻转卡片游戏）
 * @date 2023/8/2 10:17 AM
 */
public class Problem0822CardFlippingGame {

    public int flipgame(int[] fronts, int[] backs) {
        int max = 0, n = fronts.length;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, Math.max(fronts[i], backs[i]));
        }
        int[] arr = new int[max + 1];
        for (int i = 0; i < n; i++) {
            if (fronts[i] == backs[i]) {
                arr[fronts[i]] = -1;
            } else {
                if (arr[fronts[i]] != -1) {
                    arr[fronts[i]] = 1;
                }
                if (arr[backs[i]] != -1) {
                    arr[backs[i]] = 1;
                }
            }
        }
        for (int i = 0; i <= max; i++) {
            if (arr[i] == 1) {
                return i;
            }
        }
        return 0;
    }
}
