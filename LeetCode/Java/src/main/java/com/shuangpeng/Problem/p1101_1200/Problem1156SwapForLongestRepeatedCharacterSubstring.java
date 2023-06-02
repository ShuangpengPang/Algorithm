package com.shuangpeng.Problem.p1101_1200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1156SwapForLongestRepeatedCharacterSubstring（单字符子串的最大长度）
 * @date 2023/6/2 4:14 PM
 */
public class Problem1156SwapForLongestRepeatedCharacterSubstring {

    public int maxRepOpt1(String text) {
        char[] cs = text.toCharArray();
        int[] cnt = new int[26];
        for (char c : cs) {
            cnt[c - 'a']++;
        }
        int ans = 0, n = cs.length;
        for (int i = 0, j = 0; i < n; i = j) {
            char c = cs[i];
            j = i;
            while (j < n && cs[j] == c) {
                j++;
            }
            int length = j < n || i > 0 ? j - i + 1 : j - i;
            int k = j + 1;
            while (k < n && cs[k] == c) {
                k++;
            }
            ans = Math.max(ans, Math.min(cnt[c - 'a'], length + k - j - 1));
        }
        return ans;
    }
}
