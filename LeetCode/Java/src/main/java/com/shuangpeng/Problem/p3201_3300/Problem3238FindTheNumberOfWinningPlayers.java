package com.shuangpeng.Problem.p3201_3300;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3238FindTheNumberOfWinningPlayers（求出胜利玩家的数目）
 * @date 2024/11/6 6:09 PM
 */
public class Problem3238FindTheNumberOfWinningPlayers {

    public int winningPlayerCount0(int n, int[][] pick) {
        Arrays.sort(pick, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        int ans = 0, m = pick.length;
        int i = 0;
        while (i < m) {
            int p = pick[i][0];
            boolean win = false;
            while (i < m && pick[i][0] == p) {
                int j = i;
                i++;
                while (i < m && pick[i][0] == p && pick[i][1] == pick[i - 1][1]) {
                    i++;
                }
                if (i - j > p) {
                    win = true;
                }
            }
            if (win) {
                ans++;
            }
        }
        return ans;
    }

    public int winningPlayerCount(int n, int[][] pick) {
        int[][] cnt = new int[n][11];
        for (int[] p : pick) {
            cnt[p[0]][p[1]]++;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int c : cnt[i]) {
                if (c > i) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }
}
