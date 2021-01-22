package com.shuangpeng.Problem;

import java.util.*;

public class Problem0395LongestSubstringWithAtLeastKRepeatingCharacters {

    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0 || k < 0) {
            return 0;
        }
        if (k == 0 || k == 1) {
            return s.length();
        }
        return getLongestSubstring(s, k, 0, s.length());
    }

    private int getLongestSubstring(String s, int k, int start, int end) {
        if (end - start < k) {
            return 0;
        }
        int[] hash = new int[26];
        for (int i = start; i < end; i++) {
            hash[s.charAt(i) - 'a']++;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = start; i < end; i++) {
            if (hash[s.charAt(i) - 'a'] < k) {
                list.add(i);
            }
        }
        if (list.isEmpty()) {
            return end - start;
        }
        list.add(end);
        int previous = 0;
        int max = 0;
        for (int index : list) {
            max = Math.max(max, getLongestSubstring(s, k, previous, index));
            previous = index + 1;
        }
        return max;
    }
}
