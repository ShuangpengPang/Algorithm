package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3185CountPairsThatFormACompleteDayII（构成整天的下标对数目II）
 * @date 2024/6/19 5:32 PM
 */
public class Problem3185CountPairsThatFormACompleteDayII {

    public long countCompleteDayPairs0(int[] hours) {
        int[] cnt = new int[24];
        for (int h : hours) {
            cnt[h % 24]++;
        }
        long ans = (long) cnt[0] * (cnt[0] - 1) / 2 + (long) cnt[12] * (cnt[12] - 1) / 2;
        for (int i = 1; i < 12; i++) {
            ans += (long) cnt[i] * cnt[24 - i];
        }
        return ans;
    }

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
