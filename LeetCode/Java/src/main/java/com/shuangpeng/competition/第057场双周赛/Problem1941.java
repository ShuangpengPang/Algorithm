package com.shuangpeng.competition.第057场双周赛;

public class Problem1941 {

    public boolean areOccurrencesEqual(String s) {
        int[] arr = new int[26];
        int n = s.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            arr[s.charAt(i) - 'a']++;
            count = Math.max(count, arr[s.charAt(i) - 'a']);
        }
        for (int i = 0; i < 26; i++) {
            if (arr[i] > 0 && arr[i] != count) {
                return false;
            }
        }
        return true;
    }
}
