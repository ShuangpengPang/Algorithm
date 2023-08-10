package com.shuangpeng.Problem.p1301_1400;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1371FindTheLongestSubstringContainingVowelsInEvenCounts（每个元音包含偶数次的最长子字符串）
 * @date 2023/8/10 2:43 PM
 */
public class Problem1371FindTheLongestSubstringContainingVowelsInEvenCounts {

    public int findTheLongestSubstring(String s) {
        String vowels = "aeiou";
        int n = s.length(), hash = 0, ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(hash, -1);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (vowels.contains("" + c)) {
                hash ^= 1 << c - 'a';
            }
            int j = i;
            ans = Math.max(ans, i - map.computeIfAbsent(hash, k -> j));
        }
        return ans;
    }
}
