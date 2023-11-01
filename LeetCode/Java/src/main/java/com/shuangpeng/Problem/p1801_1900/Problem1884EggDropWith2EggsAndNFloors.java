package com.shuangpeng.Problem.p1801_1900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1884EggDropWith2EggsAndNFloors（鸡蛋掉落-两枚鸡蛋）
 * @date 2023/11/1 11:06 AM
 */
public class Problem1884EggDropWith2EggsAndNFloors {

    public int twoEggDrop(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; j < dp[i]; j++) {
                dp[i] = Math.min(dp[i], Math.max(j, dp[i - j] + 1));
            }
        }
        return dp[n];
    }
}
