package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3208AlternatingGroupsII（交替组II）
 * @date 2024/11/27 11:27 AM
 */
public class Problem3208AlternatingGroupsII {

    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length, ans = 0;
        for (int i = 0, j = 0; j < n + k - 1; j++) {
            if (colors[j % n] == colors[(j - 1 + n) % n]) {
                i = j;
            }
            if (j - i + 1 >= k) {
                ans++;
            }
        }
        return ans;
    }
}
