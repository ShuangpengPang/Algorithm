package com.shuangpeng.Problem.p3001_3100;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3014MinimumNumberOfPushesToTypeWordI（输入单词需要的最少按键次数I）
 * @date 2024/4/23 3:51 PM
 */
public class Problem3014MinimumNumberOfPushesToTypeWordI {

    public int minimumPushes0(String word) {
        char[] cs = word.toCharArray();
        int[] cnt = new int[26];
        for (char c : cs) {
            cnt[c - 'a']++;
        }
        Arrays.sort(cnt);
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            ans += cnt[i] * ((26 - i + 7) / 8);
        }
        return ans;
    }

    public int minimumPushes(String word) {
        int n = word.length();
        int cnt = n / 8, m = n % 8;
        return 4 * (cnt + 1) * cnt + (cnt + 1) * m;
    }
}
