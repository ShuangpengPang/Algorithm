package com.shuangpeng.Problem.p1001_1100;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1081SmallestSubsequenceOfDistinctCharacters（不同字符的最小子序列）
 * @date 2023/5/22 11:31 AM
 */
public class Problem1081SmallestSubsequenceOfDistinctCharacters {

    public String smallestSubsequence0(String s) {
        List<Integer>[] indices = new List[26];
        int count = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (indices[c] == null) {
                indices[c] = new ArrayList<>();
                count++;
            }
            indices[c].add(i);
        }
        char[] cs = new char[count];
        boolean[] visited = new boolean[26];
        int[] last = new int[26];
        for (int i = 0, idx = 0; i < count; i++) {
            int min = n;
            for (int j = 0; j < 26; j++) {
                List<Integer> list = indices[j];
                if (visited[j] || list == null) {
                    continue;
                }
                min = Math.min(min, list.get(list.size() - 1));
            }
            for (int j = 0; j < 26; j++) {
                List<Integer> list = indices[j];
                if (visited[j] || list == null) {
                    continue;
                }
                while (list.get(last[j]) < idx) {
                    last[j]++;
                }
                if (list.get(last[j]) <= min) {
                    idx = list.get(last[j]) + 1;
                    cs[i] = (char) ('a' + j);
                    visited[j] = true;
                    break;
                }
            }
        }
        return new String(cs);
    }

    public String smallestSubsequence(String s) {
        char[] cs = s.toCharArray();
        int[] cnt = new int[26];
        for (char c : cs) {
            cnt[c - 'a']++;
        }
        int n = cs.length;
        boolean[] add = new boolean[26];
        Deque<Integer> q = new ArrayDeque<>(n);
        for (int i = 0; i < n; i++) {
            int c = cs[i] - 'a';
            if (!add[c]) {
                while (!q.isEmpty() && cs[q.peek()] >= cs[i] && cnt[cs[q.peek()] - 'a'] > 0) {
                    add[cs[q.pop()] - 'a'] = false;
                }
                add[c] = true;
                q.push(i);
            }
            cnt[c]--;
        }
        int m = q.size();
        char[] ans = new char[m];
        for (int i = 0; i < m; i++) {
            ans[i] = cs[q.pollLast()];
        }
        return new String(ans);
    }
}
