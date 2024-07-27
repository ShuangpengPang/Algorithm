package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3184CountPairsThatFormACompleteDayI（构成整天的下标对数目 I）
 * @date 2024/7/27 10:28 PM
 */
public class Problem3184CountPairsThatFormACompleteDayI {

    public int countCompleteDayPairs(int[] hours) {
        int[] cnt = new int[24];
        int ans = 0;
        for (int h : hours) {
            int m = h % 24;
            ans += cnt[(24 - m) % 24];
            cnt[m]++;
        }
        return ans;
    }
}
