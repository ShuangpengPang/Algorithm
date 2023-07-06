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

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        Map<String, Integer> map = new HashMap<>();
        int[] cnt = new int[26];
        int count = 0, ans = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (++cnt[c] == 1) {
                count++;
            }
            if (i >= minSize) {
                if (--cnt[s.charAt(i - minSize) - 'a'] == 0) {
                    count--;
                }
            }
            if (i >= minSize - 1 && count <= maxLetters) {
                String str = s.substring(i - minSize + 1, i + 1);
                int freq = map.getOrDefault(str, 0) + 1;
                map.put(str, freq);
                ans = Math.max(ans, freq);
            }
        }
        return ans;
    }
}
