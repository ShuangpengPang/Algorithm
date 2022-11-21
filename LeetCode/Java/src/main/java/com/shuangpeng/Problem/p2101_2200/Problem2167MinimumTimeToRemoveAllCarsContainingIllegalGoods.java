package com.shuangpeng.Problem.p2101_2200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2167MinimumTimeToRemoveAllCarsContainingIllegalGoods（移除所有载有违禁货物车厢所需的最少时间）
 * @date 2022/11/21 5:20 PM
 */
public class Problem2167MinimumTimeToRemoveAllCarsContainingIllegalGoods {

    public int minimumTime0(String s) {
        int n = s.length();
        int[] right = new int[n];
        right[n - 1] = s.charAt(n - 1) == '1' ? 1 : 0;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.min(n - i, right[i + 1] + (s.charAt(i) == '1' ? 2 : 0));
        }
        int ans = right[0], min = 0;
        for (int i = 0; i < n - 1; i++) {
            min = Math.min(i + 1, min + (s.charAt(i) == '1' ? 2 : 0));
            ans = Math.min(ans, min + right[i + 1]);
        }
        return ans;
    }

    public int minimumTime1(String s) {
        int n = s.length();
        int min = 0, sum = 0, ans = n;
        for (int i = 0; i < n; i++) {
            sum += s.charAt(i) == '1' ? 1 : 0;
            int j = i + 1 - (sum << 1);
            ans = Math.min(ans, min - j);
            min = Math.min(min, j);
        }
        return Math.min(ans + n, n);
    }

    public int minimumTime(String s) {
        int n = s.length(), ans = n, sum = 0, min = 0;
        for (int i = 0; i < n; i++) {
            min = i == 0 ? 0 : Math.min(min, i - (sum << 1));
            sum += s.charAt(i) - '0';
            ans = Math.min(ans, min + (sum << 1) - i);
        }
        return Math.min(ans + n - 1, n);
    }
}
