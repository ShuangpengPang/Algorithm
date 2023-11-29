package com.shuangpeng.Problem.p2501_2600;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2516TakeKOfEachCharacterFromLeftAndRight（每种字符至少取K个）
 * @date 2023/11/28 11:38 PM
 */
public class Problem2516TakeKOfEachCharacterFromLeftAndRight {

    public int takeCharacters0(String s, int k) {
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

    public int takeCharacters1(String s, int k) {
        if (k == 0) {
            return 0;
        }
        int n = s.length(), count = 0;
        if (n < 3 * k) {
            return -1;
        }
        int[] cnt = new int[3];
        Arrays.fill(cnt, -k);
        for (int i = 0; i < n; i++) {
            if (++cnt[s.charAt(i) - 'a'] == 0) {
                count++;
            }
        }
        if (count != 3) {
            return -1;
        }
        int ans = n;
        for (int i = 0, j = 0; i < n && j < n; i++) {
            while (j < n && count == 3 && cnt[s.charAt(j) - 'a'] > 0) {
                cnt[s.charAt(j++) - 'a']--;
            }
            if (count == 3) {
                ans = Math.min(ans, n - j + i);
            }
            if (++cnt[s.charAt(i) - 'a'] == 0) {
                count++;
            }
        }
        return ans;
    }

    public int takeCharacters(String s, int k) {
        int n = s.length();
        if (n < 3 * k) {
            return -1;
        }
        int[] cnt = new int[3];
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 3; i++) {
            if (cnt[i] < k) {
                return -1;
            }
            cnt[i] -= k;
        }
        int max = 0;
        for (int i = 0, j = 0, c = 0; j < n; j++) {
            if (cnt[s.charAt(j) - 'a']-- == 0) {
                c++;
            }
            if (c != 0 && ++cnt[s.charAt(i++) - 'a'] == 0) {
                c--;
            }
            max = Math.max(max, j - i + 1);
        }
        return n - max;
    }
}
