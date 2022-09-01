package com.shuangpeng.competition.第290到300场周赛.第298场周赛;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Problem2311LongestBinarySubsequenceLessThanOrEqualToK（小于等于K的最长二进制子序列）
 * @Date 2022/6/30 7:31 PM
 * @Version 1.0
 */
public class Problem2311LongestBinarySubsequenceLessThanOrEqualToK {

    public int longestSubsequence0(String s, int k) {
        List<Integer> list = new ArrayList<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int j = s.charAt(i) - '0';
            int size = list.size();
            if (size == 0) {
                list.add(j);
                continue;
            }
            if (list.get(size - 1) * 2 + j <= k) {
                list.add(list.get(size - 1) * 2 + j);
            }
            for (int t = size - 2; t >= 0; t--) {
                int num = list.get(t) * 2 + j;
                if (num <= k) {
                    list.set(t + 1, Math.min(num, list.get(t + 1)));
                }
            }
            list.set(0, Math.min(list.get(0), j));
        }
        return list.size();
    }

    public int longestSubsequence1(String s, int k) {
        int m = 32 - Integer.numberOfLeadingZeros(k);
        int n = s.length();
        if (n < m) {
            return n;
        }
        int ans = Integer.parseInt(s.substring(n - m), 2) <= k ? m : m - 1;
        for (int i = 0; i < n - m; i++) {
            if (s.charAt(i) == '0') {
                ans++;
            }
        }
        return ans;
    }

    public int longestSubsequence2(String s, int k) {
        int n = s.length(), m = 32 - Integer.numberOfLeadingZeros(k);
        if (n < m) return n;
        int ans = Integer.parseInt(s.substring(n - m), 2) <= k ? m : m - 1;
        return ans + (int) s.substring(0, n - m).chars().filter(c -> c == '0').count();
    }

    public int longestSubsequence(String s, int k) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) =='0') {
                ans++;
            }
        }
        long num = 0;
        for (int i = 0; i < n && num <= k; i++) {
            if (s.charAt(n - i - 1) == '1') {
                num += 1L << i;
                if (num <= k) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
