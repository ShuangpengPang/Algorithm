package com.shuangpeng.Problem.p1301_1400;

/**
 * @Description: Problem1392LongestHappyPrefix（最长快乐前缀）
 * @Date 2022/8/11 2:03 PM
 * @Version 1.0
 */
public class Problem1392LongestHappyPrefix {

    public String longestPrefix0(String s) {
        int n = s.length();
        int[] prefix = new int[n];
        for (int i = 1; i < n; i++) {
            int j = i - 1;
            while (j >= 0 && s.charAt(i) != s.charAt(prefix[j])) {
                j = prefix[j] - 1;
            }
            prefix[i] = j >= 0 ? prefix[j] + 1 : 0;
        }
        return s.substring(0, prefix[n - 1]);
    }

    public String longestPrefix(String s) {
        int n = s.length(), M = (int) 1e9 + 7;
        int maxLen = 0;
        long h1 = 0L, h2 = 0L, u = 1L;
        for (int i = 0; i < n - 1; i++) {
            int j = s.charAt(i) - 'a', k = s.charAt(n - i - 1) - 'a';
            h1 = (h1 * 26 + j) % M;
            h2 = (h2 + k * u) % M;
            u = (u * 26) % M;
            if (h1 == h2) {
                maxLen = i + 1;
            }
        }
        return s.substring(0, maxLen);
    }
}



