package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1578MinimumTimeToMakeRopeColorful（使绳子变成彩色的最短时间）
 * @date 2025/4/25 13:48
 */
public class Problem1578MinimumTimeToMakeRopeColorful {

    public int minCost0(String colors, int[] neededTime) {
        char[] cs = colors.toCharArray();
        int n = cs.length, cnt = 0, s = 0, mx = Integer.MIN_VALUE;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            cnt++;
            s += neededTime[i];
            mx = Math.max(mx, neededTime[i]);
            if (i == n - 1 || cs[i] != cs[i + 1]) {
                if (cnt > 1) {
                    ans += s - mx;
                }
                cnt = 0;
                s = 0;
                mx = Integer.MIN_VALUE;
            }
        }
        return ans;
    }

    public int minCost(String colors, int[] neededTime) {
        char[] cs = colors.toCharArray();
        int n = cs.length, maxTime = 0, ans = 0;
        char preColor = '-';
        for (int i = 0; i < n; i++) {
            if (cs[i] == preColor) {
                if (neededTime[i] > maxTime) {
                    ans += maxTime;
                    maxTime = neededTime[i];
                } else {
                    ans += neededTime[i];
                }
            } else {
                preColor = cs[i];
                maxTime = neededTime[i];
            }
        }
        return ans;
    }
}
