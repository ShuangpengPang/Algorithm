package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2222NumberOfWaysToSelectBuildings（选择建筑的方案数）
 * @date 2023/11/15 3:36 PM
 */
public class Problem2222NumberOfWaysToSelectBuildings {

    public long numberOfWays(String s) {
        int n = s.length();
        int[][] cnt = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            cnt[i][0] = cnt[i - 1][0];
            cnt[i][1] = cnt[i - 1][1];
            cnt[i][s.charAt(i - 1) - '0']++;
        }
        long ans = 0, cnt0 = 0, cnt1 = 0;
        for (int i = n; i > 0; i--) {
            if (s.charAt(i - 1) - '0' == 0) {
                ans += cnt[i - 1][1] * cnt1;
                cnt0++;
            } else {
                ans += cnt[i - 1][0] * cnt0;
                cnt1++;
            }
        }
        return ans;
    }
}
