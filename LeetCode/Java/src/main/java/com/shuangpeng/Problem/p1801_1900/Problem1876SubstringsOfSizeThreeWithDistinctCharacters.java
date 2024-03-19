package com.shuangpeng.Problem.p1801_1900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1876SubstringsOfSizeThreeWithDistinctCharacters（长度为三且各字符不同的子字符串）
 * @date 2024/3/19 3:19 PM
 */
public class Problem1876SubstringsOfSizeThreeWithDistinctCharacters {

    public int countGoodSubstrings(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        int ans = 0;
        for (int i = 0, count = 0; i < n; i++) {
            if (++cnt[s.charAt(i) - 'a'] == 1) {
                count++;
            }
            if (i >= 3 && --cnt[s.charAt(i - 3) - 'a'] == 0) {
                count--;
            }
            if (i >= 2 && count == 3) {
                ans++;
            }
        }
        return ans;
    }
}
