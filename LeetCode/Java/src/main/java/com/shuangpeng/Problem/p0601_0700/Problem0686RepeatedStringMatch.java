package com.shuangpeng.Problem.p0601_0700;

import com.shuangpeng.competition.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Problem0686RepeatedStringMatch {

    public int repeatedStringMatch0(String a, String b) {
        return dfs(a, b);
    }

    private int dfs(String a, String b) {
        if (b.isEmpty()) {
            return 0;
        }
        int n1 = a.length(), n2 = b.length();
        String aa = a + a;
        int idx = b.indexOf(a);
        if (idx == -1) {
            return a.indexOf(b) != -1 ? 1 : (aa.indexOf(b) != -1 ? 2 : -1);
        }
        List<Integer> list = new ArrayList<>();
        int p = 0;
        while (p < n2) {
            p = b.indexOf(a, p);
            if (p == -1) {
                break;
            }
            list.add(p);
            ++p;
        }
        int ans = Integer.MAX_VALUE;
        int size = list.size();
        for (int i = 0; i < size && list.get(i) < n1; ++i) {
            ans = Math.min(ans, getCount(a, b, i, list));
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int getCount(String a, String b, int idx, List<Integer> list) {
        int n1 = a.length(), n2 = b.length();
        int p = list.get(idx);
        if (p >= n1 || !a.endsWith(b.substring(0, p))) {
            return Integer.MAX_VALUE;
        }
        int size = list.size();
        int ans = p == 0 ? 1 : 2;
        int next = p + n1;
        int i = idx;
        while (i < size && list.get(i) <= next) {
            int j = list.get(i);
            if (j == next) {
                ++ans;
                next = j + n1;
            }
            ++i;
        }
        if (next == n2) {
            return ans;
        }
        if (n2 - next > n1) {
            return Integer.MAX_VALUE;
        }
        return a.startsWith(b.substring(next)) ? ans + 1 : Integer.MAX_VALUE;
    }

    public int repeatedStringMatch1(String a, String b) {
        if (b.isEmpty()) {
            return 0;
        }
        int idx = robinKarp(a, b);
        if (idx == -1) {
            return -1;
        }
        int n1 = a.length(), n2 = b.length();
        if (n1 - idx >= n2) {
            return 1;
        }
        return (n2 + idx - 1) / n1 + 1;
    }

    private int robinKarp(String haystack, String needle) {
        int n1 = haystack.length(), n2 = needle.length();
        int M = (int) 1e9 + 9;
        int K = 1337;
        Random random = new Random();
        M = random.nextInt(M) + M;
        K = random.nextInt(K) + K;
        long targetHash = 0;
        for (int i = 0; i < n2; ++i) {
            targetHash = (targetHash * K + needle.charAt(i)) % M;
        }
        long hash = 0, extra = 1;
        for (int i = 0; i < n2 - 1; ++i) {
            hash = (hash * K + haystack.charAt(i % n1)) % M;
            extra = extra * K % M;
        }
        for (int i = n2 - 1; i - n2 + 1 < n1; ++i) {
            hash = (hash * K + haystack.charAt(i % n1)) % M;
            if (hash == targetHash) {
                return i - n2 + 1;
            }
            hash = (hash - haystack.charAt(i - n2 + 1) * extra) % M;
            hash = (hash + M) % M;
        }
        return -1;
    }

    public int repeatedStringMatch(String a, String b) {
        int n1 = a.length(), n2 = b.length();
        if (b.isEmpty()) {
            return 0;
        }
        int[] next = new int[n2];
        next[0] = -1;
        for (int i = 1; i < n2; ++i) {
            int j = next[i - 1];
            while (j > 0 && b.charAt(j) != b.charAt(i - 1)) {
                j = next[j];
            }
            next[i] = j + 1;
        }
        next[0] = 0;
        int i = 0;
        int j = 0;
        int n = n1 + n2;
        while (i < n - 1 && j < n2) {
            int m = i % n1;
            while (j > 0 && a.charAt(m) != b.charAt(j)) {
                j = next[j];
            }
            if (a.charAt(m) == b.charAt(j)) {
                ++j;
            }
            ++i;
        }
        if (j < n2) {
            return -1;
        }
        return (i - 1) / n1 + 1;
    }

    public int repeatedStringMatch(String a, String b) {
        int an = a.length(), bn = b.length();
        int index = strStr(a, b);
        if (index == -1) {
            return -1;
        }
        if (an - index >= bn) {
            return 1;
        }
        return (bn + index - an - 1) / an + 2;
    }

    public int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        if (m == 0) {
            return 0;
        }
        int[] pi = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        for (int i = 0, j = 0; i - j < n; i++) { // b 开始匹配的位置是否超过第一个叠加的 a
            while (j > 0 && haystack.charAt(i % n) != needle.charAt(j)) { // haystack 是循环叠加的字符串，所以取 i % n
                j = pi[j - 1];
            }
            if (haystack.charAt(i % n) == needle.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

//    public static void main(String[] args) {
//        Problem0686RepeatedStringMatch a = new Problem0686RepeatedStringMatch();
//        int j = a.repeatedStringMatch("abcabcabcabc", "abac");
//        int i = 1;
//    }
}
