package com.shuangpeng.Problem.p2801_2900;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2900LongestUnequalAdjacentGroupsSubsequenceI（最长相邻不相等子序列I）
 * @date 2023/12/25 10:21 PM
 */
public class Problem2900LongestUnequalAdjacentGroupsSubsequenceI {

    public List<String> getWordsInLongestSubsequence0(int n, String[] words, int[] groups) {
        List<String> ans = new ArrayList<>();
        ans.add(words[0]);
        for (int i = 1, g = groups[0]; i < n; i++) {
            if (groups[i] != g) {
                ans.add(words[i]);
                g = groups[i];
            }
        }
        return ans;
    }

    public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i == 0 || groups[i] != groups[i - 1]) {
                ans.add(words[i]);
            }
        }
        return ans;
    }
}
