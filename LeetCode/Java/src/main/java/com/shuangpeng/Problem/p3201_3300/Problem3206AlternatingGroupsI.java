package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3206AlternatingGroupsI（交替组I）
 * @date 2024/11/26 10:49 AM
 */
public class Problem3206AlternatingGroupsI {

    public int numberOfAlternatingGroups(int[] colors) {
        int n = colors.length, ans = 0;
        for (int i = 0; i < n; i++) {
            if (colors[i] != colors[(i + 1) % n] && colors[i] != colors[(i + n - 1) % n]) {
                ans++;
            }
        }
        return ans;
    }
}
