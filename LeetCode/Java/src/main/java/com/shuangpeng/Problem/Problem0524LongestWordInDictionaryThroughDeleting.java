package com.shuangpeng.Problem;

import java.util.Arrays;
import java.util.List;

public class Problem0524LongestWordInDictionaryThroughDeleting {

    public String findLongestWord0(String s, List<String> dictionary) {
        dictionary.sort((a, b) -> {
            if (a.length() != b.length()) {
                return b.length() - a.length();
            }
            int n = a.length();
            for (int i = 0; i < n; ++i) {
                if (a.charAt(i) != b.charAt(i)) {
                    return a.charAt(i) - b.charAt(i);
                }
            }
            return 0;
        });
        int n1 = s.length();
        for (String str : dictionary) {
            int n2 = str.length();
            int i = 0, j = 0;
            while (i < n2 && j < n1) {
                if (s.charAt(j) == str.charAt(i)) {
                    ++i;
                }
                ++j;
            }
            if (i == n2) {
                return str;
            }
        }
        return "";
    }

    public String findLongestWord(String s, List<String> dictionary) {
        final int N = 26;
        int n = s.length();
        int[][] dp = new int[n][N];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], n);
        }
        dp[n - 1][s.charAt(n - 1) - 'a'] = n - 1;
        for (int i = n - 2; i >= 0; --i) {
            int c = s.charAt(i) - 'a';
            for (int j = 0; j < N; ++j) {
                dp[i][j] = dp[i + 1][j];
            }
            dp[i][c] = i;
        }
        String ans = "";
        for (String str : dictionary) {
            int n2 = str.length();
            int i = 0, j = 0;
            while (i < n && j < n2) {
                int c = str.charAt(j) - 'a';
                int k = dp[i][c];
                if (k < n) {
                    i = k + 1;
                    j++;
                } else {
                    break;
                }
            }
            if (j == n2 && (n2 > ans.length() || (n2 == ans.length() && str.compareTo(ans) < 0))) {
                ans = str;
            }
        }
        return ans;
    }
}
