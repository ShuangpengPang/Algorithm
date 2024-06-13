package com.shuangpeng.lcr;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR014CheckInclusion（字符串的排列）
 * @date 2024/6/13 5:50 PM
 */
public class LCR014CheckInclusion {

    public boolean checkInclusion0(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length(), count = 0;
        if (n1 > n2) {
            return false;
        }
        char[] cs1 = s1.toCharArray(), cs2 = s2.toCharArray();
        int[] cnt = new int[26];
        for (char c : cs1) {
            if (cnt[c - 'a']++ == 0) {
                count++;
            }
        }
        for (int i = 0; i < n1 && count != 0; i++) {
            int c = cs2[i] - 'a';
            cnt[c]--;
            if (cnt[c] == 0) {
                count--;
            } else if (cnt[c] == -1) {
                count++;
            }
        }
        for (int i = n1; i < n2 && count != 0; i++) {
            int c1 = cs2[i] - 'a', c2 = cs2[i - n1] - 'a';
            cnt[c1]--;
            if (cnt[c1] == 0) {
                count--;
            } else if (cnt[c1] == -1) {
                count++;
            }
            cnt[c2]++;
            if (cnt[c2] == 0) {
                count--;
            } else if (cnt[c2] == 1) {
                count++;
            }
        }
        return count == 0;
    }

    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        if (n1 > n2) {
            return false;
        }
        int[] cnt = new int[26];
        char[] cs1 = s1.toCharArray(), cs2 = s2.toCharArray();
        for (int i = 0; i < n1; i++) {
            cnt[cs1[i] - 'a']--;
        }
        int left = 0;
        for (int right = 0; right < n2; right++) {
            int c = cs2[right] - 'a';
            cnt[c]++;
            while (cnt[c] > 0) {
                cnt[cs2[left++] - 'a']--;
            }
            if (right - left + 1 == n1) {
                return true;
            }
        }
        return false;
    }
}
