package com.shuangpeng.Problem.p0901_1000;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0916WordSubsets（单词子集）
 * @date 2022/11/25 11:16 PM
 */
public class Problem0916WordSubsets {

    public List<String> wordSubsets(String[] words1, String[] words2) {
        int[] cnt = new int[26];
        for (String w : words2) {
            int[] tmp = getCount(w);
            for (int i = 0; i < 26; i++) {
                cnt[i] = Math.max(cnt[i], tmp[i]);
            }
        }
        List<String> ans = new ArrayList<>();
        for (String w : words1) {
            int[] tmp = getCount(w);
            boolean valid = true;
            for (int i = 0; i < 26; i++) {
                if (cnt[i] > tmp[i]) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                ans.add(w);
            }
        }
        return ans;
    }

    private int[] getCount(String w) {
        int[] cnt = new int[26];
        int n = w.length();
        for (int i = 0; i < n; i++) {
            cnt[w.charAt(i) - 'a']++;
        }
        return cnt;
    }
}
