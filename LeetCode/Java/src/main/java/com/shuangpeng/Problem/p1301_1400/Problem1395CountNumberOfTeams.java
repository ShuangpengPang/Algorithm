package com.shuangpeng.Problem.p1301_1400;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1395CountNumberOfTeams（统计作战单位数）
 * @date 2023/8/18 5:32 PM
 */
public class Problem1395CountNumberOfTeams {

    public int numTeams(int[] rating) {
        int n = rating.length, max = 0;
        for (int r : rating) {
            max = Math.max(max, r);
        }
        int[] map = new int[max + 1], bit = new int[n + 1];
        for (int i = 0; i < n; i++) {
            map[rating[i]] = i + 1;
        }
        Arrays.sort(rating);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int idx = map[rating[i]];
            int x = idx;
            while (x <= n) {
                bit[x]++;
                x += x & -x;
            }
            x = idx - 1;
            int cnt = 0;
            while (x != 0) {
                 cnt += bit[x];
                 x -= x & -x;
            }
            ans += cnt * (n - i - 1 - (idx - cnt - 1)) + (idx - cnt - 1) * (i - cnt);
        }
        return ans;
    }
}
