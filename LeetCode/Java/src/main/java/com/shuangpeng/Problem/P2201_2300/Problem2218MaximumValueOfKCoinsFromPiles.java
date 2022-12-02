package com.shuangpeng.Problem.P2201_2300;

import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2218MaximumValueOfKCoinsFromPiles（从栈中取出K个硬币的最大面值和）
 * @date 2022/12/1 6:03 PM
 */
public class Problem2218MaximumValueOfKCoinsFromPiles {

    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int sumN = 0;
        int[] dp = new int[k + 1];
        for (List<Integer> pile : piles) {
            int n = pile.size();
            sumN += n;
            for (int i = Math.min(sumN, k); i > 0; i--) {
                for (int j = 0, sum = 0; j < Math.min(i, n); j++) {
                    sum += pile.get(j);
                    dp[i] = Math.max(dp[i], sum + dp[i - j - 1]);
                }
            }
        }
        return dp[k];
    }
}