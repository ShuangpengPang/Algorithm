package com.shuangpeng.Problem.p0401_0500;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0455AssignCookies（分发饼干）
 * @date 2023/3/15 11:20 PM
 */
public class Problem0455AssignCookies {

    public int findContentChildren0(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int n1 = g.length, n2 = s.length, ans = 0;
        for (int i = 0, j = 0; j < n2 && i < n1; i++, j++) {
            while (j < n2 && s[j] < g[i]) {
                j++;
            }
            if (j < n2) {
                ans++;
            }
        }
        return ans;
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int ans = 0;
        for (int i = g.length - 1, j = s.length - 1; i >= 0 && j >= 0; i--) {
            if (s[j] >= g[i]) {
                ans++;
                j--;
            }
        }
        return ans;
    }
}
