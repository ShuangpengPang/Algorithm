package com.shuangpeng.Problem;

import java.util.*;

public class Problem0076MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        if (s == null || t == null || t.length() == 0 || s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (need.containsKey(c)) {
                need.put(c, need.get(c) + 1);
            } else {
                need.put(c, 1);
            }
        }
        Map<Character, Integer> current = new HashMap<>();
        int window = 0;
        int left = 0;
        int right = 0;
        int length = s.length();
        int minLeft = 0;
        int minLength = Integer.MAX_VALUE;
        while (right < length) {
            char c = s.charAt(right);
            if (need.containsKey(c)) {
                if (current.containsKey(c)) {
                    current.put(c, current.get(c) + 1);
                } else {
                    current.put(c, 1);
                }
                if (current.get(c).equals(need.get(c))) {
                    window++;
                }
                while (window == need.size()) {
                    if (right - left + 1 < minLength) {
                        minLength = right - left + 1;
                        minLeft = left;
                    }
                    char leftChar = s.charAt(left);
                    if (current.containsKey(leftChar)) {
                        current.put(leftChar, current.get(leftChar) - 1);
                        if (current.get(leftChar) < need.get(leftChar)) {
                            window--;
                        }
                    }
                    left++;
                }
            }
            right++;
        }
        if (minLength != Integer.MAX_VALUE) {
            return s.substring(minLeft, minLeft + minLength);
        } else {
            return "";
        }
    }
}
