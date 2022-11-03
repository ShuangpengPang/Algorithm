package com.shuangpeng.Problem.p1601_1700;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: Problem1668MaximumRepeatingSubstring（最大重复子字符串）
 * @Date 2022/11/3 10:17 AM
 * @Version 1.0
 */
public class Problem1668MaximumRepeatingSubstring {

    public int maxRepeating0(String sequence, String word) {
        int n = sequence.length(), m = word.length();
        Deque<Integer> q = new ArrayDeque<>();
        int ans = 0;
        for (int i = n - m; i >= 0; i--) {
            if (check(sequence, word, i)) {
                int k = 1 + (q.size() == m ? q.peekFirst() : 0);
                q.offerLast(k);
                ans = Math.max(ans, k);
            } else {
                q.offerLast(0);
            }
            if (q.size() > m) {
                q.pollFirst();
            }
        }
        return ans;
    }

    private boolean check(String sequence, String word, int idx) {
        int n = word.length();
        for (int i = 0; i < n; i++) {
            if (sequence.charAt(i + idx) != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public int maxRepeating(String sequence, String word) {
        int n = sequence.length(), m = word.length();
        int[] fail = new int[m];
        for (int i = 1; i < m; i++) {
            char c = word.charAt(i);
            int j = fail[i - 1];
            while (j > 0 && word.charAt(j) != c) {
                j = fail[j - 1];
            }
            if (word.charAt(j) == c) {
                fail[i] = j + 1;
            }
        }
        int j = 0;
        int[] dp = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            char c = sequence.charAt(i);
            while (j > 0 && word.charAt(j) != c) {
                j = fail[j - 1];
            }
            if (word.charAt(j) == c) {
                j++;
            }
            if (j == m) {
                dp[i] = 1 + (i >= m ? dp[i - m] : 0);
                ans = Math.max(ans, dp[i]);
                j = fail[m - 1];
            }
        }
        return ans;
    }
}