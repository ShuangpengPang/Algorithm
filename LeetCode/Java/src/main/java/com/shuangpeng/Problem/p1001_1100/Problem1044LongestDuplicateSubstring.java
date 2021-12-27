package com.shuangpeng.Problem.p1001_1100;

import java.util.*;

public class Problem1044LongestDuplicateSubstring {

    public String longestDupSubstring0(String s) {
        int n = s.length();
        int left = 1, right = n;
        String ans = "";
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            String str = robinKarp(s, mid);
            if (str != null) {
                ans = str;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    private String robinKarp(String s, int length) {
        int n = s.length();
        int M = (int) 1e9 + 9;
        int K = 1337;
        Random random = new Random();
        M = M + random.nextInt(M);
        K = K + random.nextInt(K);
        long hash = 0, unit = 1;
        for (int i = 0; i < length - 1; ++i) {
            hash = (hash * K + s.charAt(i)) % M;
            unit = unit * K % M;
        }
        Map<Long, Integer> map = new HashMap<>();
        for (int i = length - 1; i < n; ++i) {
            hash = (hash * K + s.charAt(i)) % M;
            if (map.containsKey(hash) && check(s, map, hash, i, length)) {
                return s.substring(i - length + 1, i + 1);
            }
            map.put(hash, i);
            hash = (hash - unit * s.charAt(i - length + 1)) % M;
            if (hash < 0) {
                hash += M;
            }
        }
        return null;
    }

    private boolean check(String s, Map<Long, Integer> map, long hash, int i, int length) {
        int j = map.get(hash);
        return s.substring(j - length + 1, j + 1).equals(s.substring(i - length + 1, i + 1));
    }

    public String longestDupSubstring(String s) {
        final int M = (int) 1e9 + 9;
        Random random = new Random();
        int k1 = random.nextInt(75) + 26;
        int k2 = random.nextInt(75) + 26;
        int m1 = random.nextInt(Integer.MAX_VALUE - M + 1) + M;
        int m2 = random.nextInt(Integer.MAX_VALUE - M + 1) + M;
        int n = s.length();
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = s.charAt(i) - 'a';
        }
        int left = 1, right = n - 1;
        int start = -1, length = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int idx = check(arr, k1, k2, m1, m2, mid);
            if (idx != -1) {
                left = mid + 1;
                start = idx;
                length = mid;
            } else {
                right = mid - 1;
            }
        }
        return start == -1 ? "" : s.substring(start, start + length);
    }

    private int check(int[] arr, int k1, int k2, int m1, int m2, int m) {
        int n = arr.length;
        long h1 = 0, h2 = 0;
        for (int i = 0; i < m; ++i) {
            h1 = (h1 * k1 + arr[i]) % m1;
            h2 = (h2 * k2 + arr[i]) % m2;
        }
        Set<Long> seen = new HashSet<>();
        seen.add(h1 * m2 + h2);
        long p1 = pow(k1, m, m1);
        long p2 = pow(k2, m, m2);
        for (int i = 1; i <= n - m; ++i) {
            h1 = (h1 * k1 + arr[i + m - 1] - arr[i - 1] * p1) % m1;
            h2 = (h2 * k2 + arr[i + m - 1] - arr[i - 1] * p2) % m2;
            h1 = h1 < 0 ? h1 + m1 : h1;
            h2 = h2 < 0 ? h2 + m2 : h2;
            long h = h1 * m2 + h2;
            if (seen.contains(h)) {
                return i;
            }
            seen.add(h);
        }
        return -1;
    }

    private long pow(int k, int m, int mod) {
        long cur = k;
        long ans = 1;
        while (m > 0) {
            if ((m & 1) == 1) {
                ans = ans * cur % mod;
            }
            cur = cur * cur % mod;
            m >>= 1;
        }
        return ans;
    }

//    long[] h, p;
//    public String longestDupSubstring(String s) {
//        int P = 1313131, n = s.length();
//        h = new long[n + 10]; p = new long[n + 10];
//        p[0] = 1;
//        for (int i = 0; i < n; i++) {
//            p[i + 1] = p[i] * P;
//            h[i + 1] = h[i] * P + s.charAt(i);
//        }
//        String ans = "";
//        int l = 0, r = n;
//        while (l < r) {
//            int mid = l + r + 1 >> 1;
//            String t = check(s, mid);
//            if (t.length() != 0) l = mid;
//            else r = mid - 1;
//            ans = t.length() > ans.length() ? t : ans;
//        }
//        return ans;
//    }
//    String check(String s, int len) {
//        int n = s.length();
//        Set<Long> set = new HashSet<>();
//        for (int i = 1; i + len - 1 <= n; i++) {
//            int j = i + len - 1;
//            long cur = h[j] - h[i - 1] * p[j - i + 1];
//            if (set.contains(cur)) return s.substring(i - 1, j);
//            set.add(cur);
//        }
//        return "";
//    }

    //   a b c d e f g h i
    //   a   c   e   g
    //   a b e f
    //   c d g h
}
