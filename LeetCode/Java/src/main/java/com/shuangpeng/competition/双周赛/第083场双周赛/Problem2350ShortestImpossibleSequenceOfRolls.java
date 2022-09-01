package com.shuangpeng.competition.双周赛.第083场双周赛;

import java.util.Arrays;

/**
 * @Description: Problem2350ShortestImpossibleSequenceOfRolls（不可能得到的最短骰子序列）
 * @Date 2022/8/20 3:58 PM
 * @Version 1.0
 */
public class Problem2350ShortestImpossibleSequenceOfRolls {

    // 比赛时写法
    public int shortestSequence0(int[] rolls, int k) {
        boolean[] has = new boolean[k];
        int cnt = 0;
        int n = rolls.length;
        int c = 0;
        for (int i = 0; i < n; i++) {
            int j = rolls[i] - 1;
            if (!has[j]) {
                has[j] = true;
                c++;
                if (c == k) {
                    c = 0;
                    cnt++;
                    Arrays.fill(has, false);
                }
            }
        }
        return cnt + 1;
    }

    public int shortestSequence1(int[] rolls, int k) {
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

    public int shortestSequence(int[] rolls, int k) {
        int[] cnt = new int[k + 1];
        int ans = 1, left = k;
        for (int r : rolls) {
            if (cnt[r] < ans) {
                cnt[r]++;
                if (--left == 0) {
                    left = k;
                    ans++;
                }
            }
        }
        return ans;
    }
}

