package com.shuangpeng.Problem.p1001_1100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1010PairsOfSongsWithTotalDurationsDivisibleBy60（总持续时间可被60整除的歌曲）
 * @date 2023/5/9 2:26 PM
 */
public class Problem1010PairsOfSongsWithTotalDurationsDivisibleBy60 {

    public int numPairsDivisibleBy600(int[] time) {
        int[] cnt = new int[60];
        for (int t : time) {
            cnt[t % 60]++;
        }
        long ans = (long) cnt[0] * (cnt[0] - 1) / 2 + (long) cnt[30] * (cnt[30] - 1) / 2;
        for (int i = 1; i < 30; i++) {
            ans += cnt[i] * cnt[60 - i];
        }
        return (int) ans;
    }

    public int numPairsDivisibleBy60(int[] time) {
        int[] cnt = new int[60];
        int ans = 0;
        for (int t : time) {
            int m = t % 60;
            ans += cnt[m == 0 ? 0 : 60 - m];
            cnt[m]++;
        }
        return ans;
    }
}
