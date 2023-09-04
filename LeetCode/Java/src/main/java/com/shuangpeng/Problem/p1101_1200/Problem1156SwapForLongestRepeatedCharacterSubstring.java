package com.shuangpeng.Problem.p1101_1200;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1156SwapForLongestRepeatedCharacterSubstring（单字符子串的最大长度）
 * @date 2023/6/2 4:14 PM
 */
public class Problem1156SwapForLongestRepeatedCharacterSubstring {

    public int maxRepOpt10(String text) {
        int n = text.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[text.charAt(i) - 'a']++;
        }
        Set<Integer> set = new HashSet<>(3);
        int ans = 0;
        int[] tmp = new int[26];
        for (int i = 0, j = 0; i < n; i++) {
            int c = text.charAt(i) - 'a';
            tmp[c]++;
            set.add(c);
            while (set.size() > 2 || set.size() == 2 && !check(cnt, tmp, set, i - j + 1)) {
                int ch = text.charAt(j++) - 'a';
                if (--tmp[ch] == 0) {
                    set.remove(ch);
                }
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }

    private boolean check(int[] cnt, int[] tmp, Set<Integer> set, int len) {
        int c1 = 0, c2 = 0, i = 0;
        for (int c : set) {
            if (i == 0) {
                c1 = c;
            } else {
                c2 = c;
            }
            i++;
        }
        return cnt[c1] - tmp[c1] >= 1 && tmp[c1] + 1 == len || cnt[c2] - tmp[c2] >= 1 && tmp[c2] + 1 == len;
    }

    public int maxRepOpt1(String text) {
        int n = text.length(), ans = 0;
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[text.charAt(i) - 'a']++;
        }
        for (int i = 0, p = 0; i < n; i = p) {
            char c = text.charAt(i);
            while (p < n && text.charAt(p) == c) {
                p++;
            }
            int j = p + 1;
            while (j < n && text.charAt(j) == c) {
                j++;
            }
            ans = Math.max(ans, Math.min(cnt[c - 'a'], j - i));
        }
        return ans;
    }
}
