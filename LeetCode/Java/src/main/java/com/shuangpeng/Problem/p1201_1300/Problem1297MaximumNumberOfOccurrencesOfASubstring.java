package com.shuangpeng.Problem.p1201_1300;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1297MaximumNumberOfOccurrencesOfASubstring（子串的最大出现次数）
 * @date 2023/7/6 2:32 PM
 */
public class Problem1297MaximumNumberOfOccurrencesOfASubstring {

    public int maxFreq0(String s, int maxLetters, int minSize, int maxSize) {
        int[] cnt = new int[26];
        int n = s.length(), count = 0, ans = 0;
        Map<String, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (cnt[s.charAt(i) - 'a']++ == 0) {
                count++;
            }
            if (i >= minSize && --cnt[s.charAt(i - minSize) - 'a'] == 0) {
                count--;
            }
            if (count <= maxLetters && i >= minSize - 1) {
                ans = Math.max(ans, freq.merge(s.substring(i - minSize + 1, i + 1), 1, Integer::sum));
            }
        }
        return ans;
    }

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        long M = (long) 1e9 + 7, b = 27, h = 1;
        for (int i = 0; i < minSize; i++) {
            h = h * b % M;
        }
        Map<Long, Integer> freq = new HashMap<>();
        long num = 0;
        int[] cnt = new int[26];
        int ans = 0, count = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (cnt[c]++ == 0) {
                count++;
            }
            num = (num * b + c + 1) % M;
            if (i >= minSize) {
                int ch = s.charAt(i - minSize) - 'a';
                if (--cnt[ch] == 0) {
                    count--;
                }
                num = ((num - (ch + 1) * h) % M + M) % M;
            }
            if (i >= minSize - 1 && count <= maxLetters) {
                ans = Math.max(ans, freq.merge(num, 1, Integer::sum));
            }
        }
        return ans;
    }
}
