package com.shuangpeng.lcr;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR016LengthOfLongestSubstring（无重复字符的最长子串）
 * @date 2024/6/16 9:08 PM
 */
public class LCR016LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        char[] cs = s.toCharArray();
        int ans = 0;
        for (int f = 0, i = 0, n = cs.length; i < n; i++) {
            while (set.contains(cs[i])) {
                set.remove(cs[f++]);
            }
            set.add(cs[i]);
            ans = Math.max(ans, i - f + 1);
        }
        return ans;
    }
}
