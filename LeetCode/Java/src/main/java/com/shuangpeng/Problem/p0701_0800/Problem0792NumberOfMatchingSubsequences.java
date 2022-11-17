package com.shuangpeng.Problem.p0701_0800;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0792NumberOfMatchingSubsequences（匹配子序列的单词数）
 * @date 2022/11/17 10:08 AM
 */
public class Problem0792NumberOfMatchingSubsequences {

    public int numMatchingSubseq0(String s, String[] words) {
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

    public int numMatchingSubseq1(String s, String[] words) {
        int n = s.length();
        List<Integer>[] indices = new List[26];
        Arrays.setAll(indices, i -> new ArrayList<>());
        for (int i = 0; i < n; i++) {
            indices[s.charAt(i) - 'a'].add(i);
        }
        int ans = 0;
        for (String w : words) {
            int m = w.length(), idx = -1;
            boolean valid = true;
            for (int i = 0; i < m; i++) {
                List<Integer> list = indices[w.charAt(i) - 'a'];
                int left = 0, right = list.size() - 1;
                while (left <= right) {
                    int mid = left + (right - left >> 1);
                    if (list.get(mid) <= idx) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                if (left == list.size()) {
                    valid = false;
                    break;
                } else {
                    idx = list.get(left);
                }
            }
            if (valid) {
                ans++;
            }
        }
        return ans;
    }

    public int numMatchingSubseq(String s, String[] words) {
        Queue<int[]>[] p = new Queue[26];
        Arrays.setAll(p, i -> new ArrayDeque<>());
        int n = s.length(), m = words.length;
        for (int i = 0; i < m; i++) {
            p[words[i].charAt(0) - 'a'].offer(new int[]{i, 0});
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            Queue<int[]> q = p[s.charAt(i) - 'a'];
            for (int j = q.size() - 1; j >= 0; j--) {
                int[] arr = q.poll();
                if (arr[1] == words[arr[0]].length() - 1) {
                    ans++;
                } else {
                    arr[1]++;
                    p[words[arr[0]].charAt(arr[1]) - 'a'].offer(arr);
                }
            }
        }
        return ans;
    }
}