package com.shuangpeng.Problem.p3001_3100;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3035MaximumPalindromesAfterOperations（回文字符串的最大数量）
 * @date 2024/2/26 3:31 PM
 */
public class Problem3035MaximumPalindromesAfterOperations {

    public int maxPalindromesAfterOperations(String[] words) {
        int[] cnt = new int[26];
        for (String w : words) {
            for (char c : w.toCharArray()) {
                cnt[c - 'a']++;
            }
        }
        Arrays.sort(words, Comparator.comparingInt(a -> a.length()));
        int ans = 0;
        for (String w : words) {
            int length = w.length(), m = length >> 1;
            for (int i = 0; i < 26 && m > 0; i++) {
                int c = Math.min(m, cnt[i] >> 1);
                m -= c;
                cnt[i] -= c << 1;
            }
            if (m > 0) {
                break;
            }
            if ((length & 1) == 0) {
                ans++;
                continue;
            }
            int index = -1;
            for (int i = 0; i < 26; i++) {
                if ((cnt[i] & 1) == 1) {
                    index = i;
                    break;
                } else if (cnt[i] > 0) {
                    index = i;
                }
            }
            cnt[index]--;
            ans++;
        }
        return ans;
    }
}
