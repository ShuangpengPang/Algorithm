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
        boolean[] invalid = new boolean[max + 1];
        for (int i = 0; i < n; i++) {
            if (fronts[i] == backs[i]) {
                invalid[fronts[i]] = true;
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (!invalid[fronts[i]]) {
                ans = Math.min(ans, fronts[i]);
            }
            if (!invalid[backs[i]]) {
                ans = Math.min(ans, backs[i]);
            }
        }
        return ans % Integer.MAX_VALUE;
    }
}
