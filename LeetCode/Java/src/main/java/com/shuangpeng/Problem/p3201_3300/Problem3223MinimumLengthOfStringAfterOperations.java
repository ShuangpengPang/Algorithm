package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3223MinimumLengthOfStringAfterOperations（操作后字符串的最短长度）
 * @date 2024/7/25 4:52 PM
 */
public class Problem3223MinimumLengthOfStringAfterOperations {

    public int minimumLength(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) {
                ans += 2 - (cnt[i] & 1);
            }
        }
        return ans;
    }
}
