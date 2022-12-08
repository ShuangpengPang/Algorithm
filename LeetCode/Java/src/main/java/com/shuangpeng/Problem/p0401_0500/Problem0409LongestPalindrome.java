package com.shuangpeng.Problem.p0401_0500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0409LongestPalindrome（最长回文串）
 * @date 2022/12/8 7:25 PM
 */
public class Problem0409LongestPalindrome {

    public int longestPalindrome0(String s) {
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

    public int longestPalindrome1(String s) {
        int n = s.length();
        boolean[] cnt1 = new boolean[26], cnt2 = new boolean[26];
        int ans = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            boolean isLowerCase = Character.isLowerCase(c);
            int j = isLowerCase ? c - 'a' : c - 'A';
            boolean[] p = isLowerCase ? cnt1 : cnt2;
            p[j] = !p[j];
            if (p[j]) {
                cnt++;
            } else {
                ans += 2;
                cnt--;
            }
        }
        return ans + (cnt > 0 ? 1 : 0);
    }

    public int longestPalindrome(String s) {
        int n = s.length();
        int[] cnt = new int[52];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= 'a') {
                cnt[c - 'a' + 26]++;
            } else {
                cnt[c - 'A']++;
            }
        }
        int ans = 0;
        for (int count : cnt) {
            ans += count &~ 1;
        }
        return ans < n ? ans + 1 : ans;
    }
}

class Solution {
    public int longestPalindrome(String s) {
        int[] cArr = new int[52];
        for (char c : s.toCharArray()) {
            if (c >= 'a')  cArr[c - 'G']++;
            else cArr[c - 'A']++;
        }
        int ans = 0;
        for (int count : cArr)  ans += (count &~ 1);
        return s.length() > ans ? ans + 1 : ans;
    }
}