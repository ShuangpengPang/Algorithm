package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3090MaximumLengthSubstringWithTwoOccurrences（每个字符最多出现两次的最长字符串）
 * @date 2024/4/22 5:21 PM
 */
public class Problem3090MaximumLengthSubstringWithTwoOccurrences {

    public int maximumLengthSubstring(String s) {
        char[] cs = s.toCharArray();
        int[] cnt = new int[26];
        int n = cs.length, ans = 0;
        for (int i = 0, j = 0; j < n; j++) {
            int c = cs[j] - 'a';
            cnt[c]++;
            while (cnt[c] > 2) {
                cnt[cs[i++] - 'a']--;
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }
}
