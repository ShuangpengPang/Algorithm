package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2516TakeKOfEachCharacterFromLeftAndRight（每种字符至少取K个）
 * @date 2023/11/28 11:38 PM
 */
public class Problem2516TakeKOfEachCharacterFromLeftAndRight {

    public int takeCharacters(String s, int k) {
        int n = s.length();
        if (n < 3 * k) {
            return -1;
        }
        int[][] cnt = new int[3][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int c = 0; c < 3; c++) {
                cnt[c][i] = cnt[c][i + 1];
            }
            cnt[s.charAt(i) - 'a'][i]++;
        }
        int[] left = new int[3];
        if (!check(left, cnt, 0, k)) {
            return -1;
        }
        int ans = n;
        for (int i = 0, j = 0; i < n && j <= n; i++) {
            while (j <= n && check(left, cnt, j, k)) {
                j++;
            }
            ans = Math.min(ans, i + n - j + 1);
            left[s.charAt(i) - 'a']++;
        }
        return ans;
    }

    private boolean check(int[] left, int[][] cnt, int i, int k) {
        for (int c = 0; c < 3; c++) {
            if (left[c] + cnt[c][i] < k) {
                return false;
            }
        }
        return true;
    }
}
