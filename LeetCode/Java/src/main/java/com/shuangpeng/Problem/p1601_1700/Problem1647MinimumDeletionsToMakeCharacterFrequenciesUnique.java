package com.shuangpeng.Problem.p1601_1700;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1647MinimumDeletionsToMakeCharacterFrequenciesUnique（字符频次唯一的最小删除次数）
 * @date 2023/9/9 5:13 PM
 */
public class Problem1647MinimumDeletionsToMakeCharacterFrequenciesUnique {

    public int minDeletions0(String s) {
        int n = s.length(), ans = 0;
        int[] cnt = new int[26];
        int maxFreq = 0;
        for (int i = 0; i < n; i++) {
            maxFreq = Math.max(maxFreq, ++cnt[s.charAt(i) - 'a']);
        }
        int[] count = new int[maxFreq + 1];
        for (int c : cnt) {
            count[c]++;
        }
        for (int i = maxFreq; i > 0; i--) {
            if (count[i] > 1) {
                int c = count[i] - 1;
                ans += c;
                count[i - 1] += c;
            }
        }
        return ans;
    }

    public int minDeletions(String s) {
        int n = s.length(), ans = 0;
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 26; i++) {
            while (cnt[i] > 0 && !set.add(cnt[i])) {
                cnt[i]--;
                ans++;
            }
        }
        return ans;
    }
}
