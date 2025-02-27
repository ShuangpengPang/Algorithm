package com.shuangpeng.Problem.p3301_3400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3335TotalCharactersInStringAfterTransformationsI（字符串转换后的长度I）
 * @date 2025/2/27 3:52 PM
 */
public class Problem3335TotalCharactersInStringAfterTransformationsI {

    public int lengthAfterTransformations(String s, int t) {
        int n = s.length(), N = (int) 1e9 + 7;
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (int i = 0; i < t; i++) {
            int c = cnt[25];
            for (int j = 24; j >= 0; j--) {
                cnt[j + 1] = cnt[j];
            }
            cnt[0] = c;
            cnt[1] = (cnt[1] + c) % N;
        }
        int ans = 0;
        for (int c : cnt) {
            ans = (ans + c) % N;
        }
        return ans;
    }
}
