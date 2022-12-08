package com.shuangpeng.Problem.p0401_0500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0409LongestPalindrome（最长回文串）
 * @date 2022/12/8 7:25 PM
 */
public class Problem0409LongestPalindrome {

    public int longestPalindrome(String s) {
        int[] cnt1 = new int[26], cnt2 = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                cnt1[c - 'a']++;
            } else {
                cnt2[c - 'A']++;
            }
        }
        boolean flag = false;
        int cnt = 0;
        for (int i = 0; i < 26; i++) {
            cnt += (cnt1[i] >> 1) + (cnt2[i] >> 1);
            if ((cnt1[i] & 1) == 1 || (cnt2[i] & 1) == 1) {
                flag = true;
            }
        }
        return (cnt << 1) + (flag ? 1 : 0);
    }
}
