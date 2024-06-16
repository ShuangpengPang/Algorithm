package com.shuangpeng.lcr;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR015FindAnagrams（找到字符串中所有字母异位词）
 * @date 2024/6/16 9:50 PM
 */
public class LCR015FindAnagrams {

    public List<Integer> findAnagrams(String s, String p) {
        char[] cs1 = s.toCharArray(), cs2 = p.toCharArray();
        int n1 = cs1.length, n2 = cs2.length;
        List<Integer> ans = new ArrayList<>();
        if (n1 < n2) {
            return ans;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n2; i++) {
            cnt[cs2[i] - 'a']--;
        }
        for (int i = 0, j = 0; i < n1; i++) {
            int c = cs1[i] - 'a';
            cnt[c]++;
            while (cnt[c] > 0) {
                cnt[cs1[j++] - 'a']--;
            }
            if (i - j + 1 == n2) {
                ans.add(j);
            }
        }
        return ans;
    }
}
