package com.shuangpeng.Problem.p1101_1200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1147LongestChunkedPalindromeDecomposition（段式回文）
 * @date 2023/4/12 10:09 AM
 */
public class Problem1147LongestChunkedPalindromeDecomposition {

    public int longestDecomposition0(String text) {
        char[] cs = text.toCharArray();
        return dfs(cs, 0, new int[(cs.length + 1) >> 1]);
    }

    private int dfs(char[] cs, int start, int[] memo) {
        int n = cs.length, mid = n >> 1;
        int end = n - start - 1;
        if (start >= end) {
            return start == end ? 1 : 0;
        }
        if (memo[start] != 0) {
            return memo[start];
        }
        int ans = 1;
        for (int i = start; i < mid; i++) {
            if (check(cs, start, i)) {
                ans = Math.max(ans, dfs(cs, i + 1, memo) + 2);
            }
        }
        memo[start] = ans;
        return ans;
    }

    private boolean check(char[] cs, int start, int end) {
        int n = end - start + 1;
        for (int i = start, j = cs.length - i - n; i <= end; i++, j++) {
            if (cs[i] != cs[j]) {
                return false;
            }
        }
        return true;
    }
}

class Problem1147LongestChunkedPalindromeDecomposition0 {

    public int longestDecomposition(String text) {
        char[] cs = text.toCharArray();
        int n = cs.length, h = (n + 1) >> 1, m = n >> 1;
        int[] dp = new int[h + 1];
        for (int i = h - 1; i >= 0; i--) {
            int ans = 1;
            for (int j = i; j < m; j++) {
                if (check(cs, i, n - j - 1, j - i + 1)) {
                    ans = Math.max(ans, dp[j + 1] + 2);
                    break;
                }
            }
            dp[i] = ans;
        }
        return dp[0];
    }

    private boolean check(char[] cs, int s1, int s2, int n) {
        for (int i = 0; i < n; i++) {
            if (cs[s1 + i] != cs[s2 + i]) {
                return false;
            }
        }
        return true;
    }
}

class Problem1147LongestChunkedPalindromeDecomposition1 {

    public int longestDecomposition0(String text) {
        char[] cs = text.toCharArray();
        int n = cs.length, m = n >> 1, h = (n - 1) >> 1;
        int start = 0, ans = 0;
        while (start <= h) {
            int i = start;
            while (i < m) {
                if (check(cs, start, n - i - 1, i - start + 1)) {
                    break;
                }
                i++;
            }
            ans += i < m ? 2 : 1;
            start = i + 1;
        }
        return ans;
    }

    private boolean check(char[] cs, int s1, int s2, int n) {
        for (int i = 0; i < n; i++) {
            if (cs[s1 + i] != cs[s2 + i]) {
                return false;
            }
        }
        return true;
    }

    public int longestDecomposition(String text) {
        char[] cs = text.toCharArray();
        int n = cs.length, ans = 0;
        int i = 0, j = 0;
        while (j < n - j - 1) {
            boolean match = true;
            for (int l = i, r = n - j - 1; l <= j; l++, r++) {
                if (cs[l] != cs[r]) {
                    match = false;
                    break;
                }
            }
            j++;
            if (match) {
                ans += 2;
                i = j;
            }
        }
        if (i <= (n - 1) >> 1) {
            ans++;
        }
        return ans;
    }

//    public int longestDecomposition(String text) {
//        char[] cs = text.toCharArray();
//        int n = cs.length;
//        long m1 = (long) 1e7, m2 = (long) 1e9;
//        long b1 = 29, b2 = 31;
//        long[] sum1 = new long[n + 1], sum2 = new long[n + 1];
//        for (int i = 0, p = 1; i < n; i++) {
//            sum1[i + 1] = (sum1[i] + p * (cs[i] - 'a' + 1)) %
//        }
//    }
}

//class Solution {
//    long[] pre1;
//    long[] pre2;
//    long[] pow1;
//    long[] pow2;
//    static final int MOD1 = 1000000007;
//    static final int MOD2 = 1000000009;
//    int base1, base2;
//    Random random = new Random();
//
//    public int longestDecomposition(String text) {
//        init(text);
//        int n = text.length();
//        int res = 0;
//        int l = 0, r = n - 1;
//        while (l <= r) {
//            int len = 1;
//            while (l + len - 1 < r - len + 1) {
//                if (Arrays.equals(getHash(l, l + len - 1), getHash(r - len + 1, r))) {
//                    res += 2;
//                    break;
//                }
//                ++len;
//            }
//            if (l + len - 1 >= r - len + 1) {
//                ++res;
//            }
//            l += len;
//            r -= len;
//        }
//        return res;
//    }
//
//    public void init(String s) {
//        base1 = 1000000 + random.nextInt(9000000);
//        base2 = 1000000 + random.nextInt(9000000);
//        while (base2 == base1) {
//            base2 = 1000000 + random.nextInt(9000000);
//        }
//        int n = s.length();
//        pow1 = new long[n];
//        pow2 = new long[n];
//        pre1 = new long[n + 1];
//        pre2 = new long[n + 1];
//        pow1[0] = pow2[0] = 1;
//        pre1[1] = pre2[1] = s.charAt(0);
//        for (int i = 1; i < n; i ++) {
//            pow1[i] = (pow1[i - 1] * base1) % MOD1;
//            pow2[i] = (pow2[i - 1] * base2) % MOD2;
//            pre1[i + 1] = (pre1[i] * base1 + s.charAt(i)) % MOD1;
//            pre2[i + 1] = (pre2[i] * base2 + s.charAt(i)) % MOD2;
//        }
//    }
//
//    public long[] getHash(int l, int r) {
//        return new long[]{(pre1[r + 1] - ((pre1[l] * pow1[r + 1 - l]) % MOD1) + MOD1) % MOD1, (pre2[r + 1] - ((pre2[l] * pow2[r + 1 - l]) % MOD2) + MOD2) % MOD2};
//    }
//}
