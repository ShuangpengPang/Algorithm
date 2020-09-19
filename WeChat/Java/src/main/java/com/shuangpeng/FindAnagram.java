package com.shuangpeng;

import java.util.ArrayList;
import java.util.List;

// 题目链接：https://mp.weixin.qq.com/s/qnFV9Odi2jzf-ZsAsM6t0A
// LeetCode链接：https://leetcode.com/problems/find-all-anagrams-in-a-string/
public class FindAnagram {
    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || p == null || s.length() < p.length()) {
            return new ArrayList<>();
        }
        char[] need = new char[26];
        char[] window = new char[26];
        int pLen = p.length();
        int count = 0;
        for (int i = 0; i < pLen; i++) {
            int index = p.charAt(i) - 'a';
            if (need[index] == 0) {
                count++;
            }
            need[index]++;
        }
        int left = 0;
        int matches = 0;
        int right = 0;
        List<Integer> results = new ArrayList<>();
        int sLen = s.length();
        while (right < sLen) {
            int index = s.charAt(right) - 'a';
            if (need[index] > 0) {
                window[index]++;
                if (window[index] == need[index]) {
                    matches++;
                }
            }
            right++;
            while (right - left >= pLen) {
                if (matches == count && right - left == pLen) {
                    results.add(left);
                }
                int leftIndex = s.charAt(left) - 'a';
                if (need[leftIndex] > 0) {
                    if (need[leftIndex] == window[leftIndex]) {
                        matches--;
                    }
                    window[leftIndex]--;
                }
                left++;
            }
        }
        return results;
    }
}
