package com.shuangpeng.Problem.p2501_2600;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2514CountAnagrams（统计同位异构字符串数目）
 * @date 2024/1/16 10:45 AM
 */
public class Problem2514CountAnagrams {

    private static long N = (long) 1e9 + 7;

    public int countAnagrams(String s) {
        char[] cs = s.toCharArray();
        int[] cnt = new int[26];
        long a = 1, b = 1, j = 0;
        for (char c : cs) {
            if (c == ' ') {
                Arrays.fill(cnt, 0);
                j = 0;
            } else {
                a = a * ++j % N;
                b = b * ++cnt[c - 'a'] % N;
            }
        }
        return (int) (a * pow(b, N - 2) % N);
    }

    private long pow(long n, long p) {
        long ans = 1;
        while (p > 0) {
            if ((p & 1) == 1) {
                ans = ans * n % N;
            }
            n = n * n % N;
            p >>= 1;
        }
        return ans;
    }
}
