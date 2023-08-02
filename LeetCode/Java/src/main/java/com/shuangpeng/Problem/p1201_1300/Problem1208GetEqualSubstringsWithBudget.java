package com.shuangpeng.Problem.p1201_1300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1208GetEqualSubstringsWithBudget（尽可能使字符串相等）
 * @date 2023/8/2 11:24 AM
 */
public class Problem1208GetEqualSubstringsWithBudget {

    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int[] cost = new int[n];
        for (int i = 0; i < n; i++) {
            cost[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int ans = 0;
        for (int i = 0, j = 0, c = 0; i < n; i++) {
            c += cost[i];
            while (c > maxCost) {
                c -= cost[j++];
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
