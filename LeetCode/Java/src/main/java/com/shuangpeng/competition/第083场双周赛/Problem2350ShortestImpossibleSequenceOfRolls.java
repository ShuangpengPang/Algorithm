package com.shuangpeng.competition.第083场双周赛;

/**
 * @Description: Problem2350ShortestImpossibleSequenceOfRolls（不可能得到的最短骰子序列）
 * @Date 2022/8/20 3:58 PM
 * @Version 1.0
 */
public class Problem2350ShortestImpossibleSequenceOfRolls {

    public int shortestSequence(int[] rolls, int k) {
        boolean[] has = new boolean[k];
        int ans = 0, cnt = 0;
        for (int r : rolls) {
            if (!has[r - 1]) {
                cnt++;
                has[r - 1] = true;
            }
            if (cnt == k) {
                cnt = 0;
                ans++;
                has = new boolean[k];
            }
        }
        return ans + 1;
    }
}

