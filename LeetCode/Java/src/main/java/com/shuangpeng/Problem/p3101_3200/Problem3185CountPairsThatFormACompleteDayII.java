package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3185CountPairsThatFormACompleteDayII（构成整天的下标对数目II）
 * @date 2024/6/19 5:32 PM
 */
public class Problem3185CountPairsThatFormACompleteDayII {

    public long countCompleteDayPairs(int[] hours) {
        int[] cnt = new int[24];
        long ans = 0;
        for (int h : hours) {
            int m = h % 24;
            ans += cnt[(24 - m) % 24];
            cnt[m]++;
        }
        return ans;
    }
}
