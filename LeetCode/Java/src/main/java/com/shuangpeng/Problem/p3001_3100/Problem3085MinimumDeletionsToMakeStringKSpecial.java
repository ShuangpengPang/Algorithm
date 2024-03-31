package com.shuangpeng.Problem.p3001_3100;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3085MinimumDeletionsToMakeStringKSpecial（成为K特殊字符串需要删除的最少字符数）
 * @date 2024/3/31 9:18 PM
 */
public class Problem3085MinimumDeletionsToMakeStringKSpecial {

    public int minimumDeletions(String word, int k) {
        int[] cnt = new int[26];
        for (char c : word.toCharArray()) {
            cnt[c - 'a']++;
        }
        Arrays.sort(cnt);
        int n = word.length(), ans = n;
        for (int i = 0, j = i, count = 0; j < 26; i++) {
            while (j < 26 && cnt[j] - cnt[i] <= k) {
                count += cnt[j++];
            }
            ans = Math.min(ans, n - count - (26 - j) * (cnt[i] + k));
            count -= cnt[i];
        }
        return ans;
    }
}
