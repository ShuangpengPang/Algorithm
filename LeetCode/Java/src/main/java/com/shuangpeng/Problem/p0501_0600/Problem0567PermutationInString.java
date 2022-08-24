package com.shuangpeng.Problem.p0501_0600;

/**
 * @Description: Problem0567PermutationInString（字符串的排列）
 * @Date 2022/8/24 10:57 AM
 * @Version 1.0
 */
public class Problem0567PermutationInString {

    public boolean checkInclusion0(String s1, String s2) {
        int[] cnt = new int[26];
        int count = 0;
        int n1 = s1.length(), n2 = s2.length();
        if (n1 > n2) {
            return false;
        }
        for (int i = 0; i < n1; i++) {
            if (cnt[s1.charAt(i) - 'a']++ == 0) {
                count++;
            }
        }
        for (int i = 0; i < n2; i++) {
            int j = s2.charAt(i) - 'a';
            cnt[j]--;
            if (cnt[j] == 0) {
                count--;
            } else if (cnt[j] == -1) {
                count++;
            }
            if (i >= n1) {
                int k = s2.charAt(i - n1) - 'a';
                cnt[k]++;
                if (cnt[k] == 0) {
                    count--;
                } else if (cnt[k] == 1) {
                    count++;
                }
            }
            if (count == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        if (n1 > n2) {
            return false;
        }
        int[] cnt = new int[26];
        int count = 0;
        for (int i = 0; i < n1; i++) {
            if (cnt[s1.charAt(i) - 'a']++ == 0) {
                count++;
            }
        }
        for (int i = 0; i < n2; i++) {
            if (--cnt[s2.charAt(i) - 'a'] == 0) {
                count--;
            }
            if (i >= n1) {
                if (cnt[s2.charAt(i - n1) - 'a']++ == 0) {
                    count++;
                }
            }
            if (count == 0) {
                return true;
            }
        }
        return false;
    }
}
