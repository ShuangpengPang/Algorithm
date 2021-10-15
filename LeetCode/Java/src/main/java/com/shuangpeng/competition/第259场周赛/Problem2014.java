package com.shuangpeng.competition.第259场周赛;

import java.util.ArrayList;
import java.util.List;

public class Problem2014 {

    public String longestSubsequenceRepeatedK(String s, int k) {
        final int N = 26;
        int n = s.length();
        int[] count = new int[N];
        for (int i = 0; i < n; ++i) {
            ++count[s.charAt(i) - 'a'];
        }
        List<Character> valid = new ArrayList<>();
        for (int i = N - 1; i >= 0; --i) {
            if (count[i] >= k) {
                valid.add((char) (i + 'a'));
            }
        }
        dfs(s, new StringBuilder(), valid, count, k);
        return ans;
    }

    String ans = "";
    private void dfs(String s, StringBuilder sb, List<Character> valid, int[] count, int k) {
        for (char c : valid) {
            if (count[c - 'a'] >= k) {
                sb.append(c);
                if (check(s, sb, k)) {
                    if (sb.length() > ans.length()) {
                        ans = sb.toString();
                    }
                    count[c - 'a'] -= k;
                    dfs(s, sb, valid, count, k);
                    count[c - 'a'] += k;
                }
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    private boolean check(String s, StringBuilder sb, int k) {
        int idx = 0;
        for (int i = 0; i < k; ++i) {
            for (int j = 0; j < sb.length(); ++j) {
                int index = s.indexOf(sb.charAt(j), idx);
                if (index == -1) {
                    return false;
                }
                idx = index + 1;
            }
        }
        return true;
    }
}
