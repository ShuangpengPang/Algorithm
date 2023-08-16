package com.shuangpeng.Problem.p0801_0900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0822CardFlippingGame（翻转卡片游戏）
 * @date 2023/8/2 10:17 AM
 */
public class Problem0822CardFlippingGame {

    public int flipgame0(int[] fronts, int[] backs) {
        int n = fronts.length, max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, Math.max(fronts[i], backs[i]));
        }
        int[] visited = new int[max + 1];
        for (int i = 0; i < n; i++) {
            if (fronts[i] == backs[i]) {
                visited[fronts[i]] = 2;
            } else {
                visited[fronts[i]] = Math.max(visited[fronts[i]], 1);
                visited[backs[i]] = Math.max(visited[backs[i]], 1);
            }
        }
        for (int i = 0; i <= max; i++) {
            if (visited[i] == 1) {
                return i;
            }
        }
        return 0;
    }

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
