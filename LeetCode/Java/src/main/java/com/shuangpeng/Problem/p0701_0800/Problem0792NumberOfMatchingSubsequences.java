package com.shuangpeng.Problem.p0701_0800;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0792NumberOfMatchingSubsequences（匹配子序列的单词数）
 * @date 2022/11/17 10:08 AM
 */
public class Problem0792NumberOfMatchingSubsequences {

    public int numMatchingSubseq(String s, String[] words) {
        int n = s.length();
        int[][] next = new int[n + 1][26];
        Arrays.fill(next[n], -1);
        for (int i = n - 1; i >= 0; i--) {
            next[i] = next[i + 1].clone();
            next[i][s.charAt(i) - 'a'] = i + 1;
        }
        int ans = 0;
        for (String w : words) {
            int m = w.length(), idx = 0;
            for (int i = 0; i < m && idx != -1; i++) {
                idx = next[idx][w.charAt(i) - 'a'];
            }
            if (idx != -1) {
                ans++;
            }
        }
        return ans;
    }
}
