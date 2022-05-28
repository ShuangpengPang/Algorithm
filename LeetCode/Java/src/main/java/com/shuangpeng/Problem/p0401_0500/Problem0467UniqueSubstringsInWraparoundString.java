package com.shuangpeng.Problem.p0401_0500;

import java.util.Arrays;

public class Problem0467UniqueSubstringsInWraparoundString {

    public int findSubstringInWraproundString0(String p) {
        int N = 26;
        int[] array = new int[N];
        array[p.charAt(0) - 'a'] = 1;
        int n = p.length();
        int left = 0, right = 1;
        int answer = 1;
        while (right < n) {
            char previous = p.charAt(right - 1);
            char current = p.charAt(right);
            int i = current - 'a';
            if ((current != previous + 1) && (previous != 'z' || current != 'a')) {
                left = right;
            }
            int length = right - left + 1;
            if (length > array[i]) {
                answer += (length - array[i]);
                array[i] = length;
            }
            right++;
        }
        return answer;
    }

    public int findSubstringInWraproundString1(String p) {
        int N = 26;
        int[] firstIndex = new int[N];
        int[] maxLength = new int[N];
        Arrays.fill(firstIndex, -1);
        int n = p.length();
        for (int i = 0, s = 0; i < n; ++i) {
            int j = p.charAt(i) - 'a';
            if ((i == 0 || j == 0 && p.charAt(i - 1) != 'z') || (j > 0 && j != p.charAt(i - 1) - 'a' + 1)) {
                s = i;
            }
            if (firstIndex[j] < s) {
                firstIndex[j] = i;
            }
            for (int k = 0; k < N; ++k) {
                if (firstIndex[k] >= s) {
                    maxLength[k] = Math.max(maxLength[k], i - firstIndex[k] + 1);
                }
            }
        }
        return Arrays.stream(maxLength).sum();
    }

    public int findSubstringInWraproundString2(String p) {
        int[] dp = new int[26];
        int n = p.length();
        for (int i = 0, k = 0; i < n; ++i) {
            int j = p.charAt(i) - 'a';
            if (i > 0 && (j == p.charAt(i - 1) - 'a' + 1 || (j == 0 && p.charAt(i - 1) == 'z'))) {
                ++k;
            } else {
                k = 1;
            }
            dp[j] = Math.max(dp[j], k);
        }
        return Arrays.stream(dp).sum();
    }

    public int findSubstringInWraproundString(String p) {
        int N = 26;
        int[] dp = new int[N];
        int n = p.length();
        for (int i = 0, k = 0; i < n; ++i) {
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) + N) % N == 1) {
                ++k;
            } else {
                k = 1;
            }
            int j = p.charAt(i) - 'a';
            dp[j] = Math.max(dp[j], k);
        }
        int ans = 0;
        for (int i = 0; i < N; ++i) {
            ans += dp[i];
        }
        return ans;
    }
}
