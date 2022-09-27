package com.shuangpeng.interview;

/**
 * @Description: Question0102CheckPermutation（面试题0102 判定是否互为字符重排）
 * @Date 2022/9/27 11:31 AM
 * @Version 1.0
 */
public class Question0102CheckPermutation {

    public boolean CheckPermutation0(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int n = s1.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            cnt[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (cnt[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int n = s1.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            if (cnt[s2.charAt(i) - 'a']-- == 0) {
                return false;
            }
        }
        return true;
    }
}
