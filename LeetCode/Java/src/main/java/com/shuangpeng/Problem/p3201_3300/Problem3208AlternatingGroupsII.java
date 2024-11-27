package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3208AlternatingGroupsII（交替组II）
 * @date 2024/11/27 11:27 AM
 */
public class Problem3208AlternatingGroupsII {

    public int numberOfAlternatingGroups0(int[] colors, int k) {
        int n = colors.length, m = n + k - 1;
        int[] arr = new int[m];
        System.arraycopy(colors, 0, arr, 0, n);
        for (int i = n; i < m; i++) {
            arr[i] = colors[i - n];
        }
        int ans = 0;
        for (int i = 0, j = 1; j < m; j++) {
            if (arr[j] == arr[j - 1]) {
                i = j;
            }
            if (j - i + 1 >= k) {
                ans++;
            }
        }
        return ans;
    }

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
