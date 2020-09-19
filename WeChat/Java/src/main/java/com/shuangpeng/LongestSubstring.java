package com.shuangpeng;

// 题目链接：https://mp.weixin.qq.com/s/qnFV9Odi2jzf-ZsAsM6t0A
// LeetCode链接：https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class LongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        int max = 0;
        int left = 0;
        int right = 0;
        char[] results = new char[128];
        while (right < s.length()) {
            char c = s.charAt(right);
            results[c]++;
            while (results[c] > 1) {
                char leftChar = s.charAt(left);
                if (results[leftChar] > 0) {
                    results[leftChar]--;
                }
                left++;
            }
            right++;
            if (right - left > max) {
                max = right - left;
            }
        }
        return max;
    }
}
