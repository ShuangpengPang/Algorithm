package com.shuangpeng.competition.第258场周赛;

public class Problem2002 {

    public int maxProduct0(String s) {
        int n = s.length();
        final int M = 1 << n;
        int[] dp = new int[M];
        for (int m = 0; m < M; ++m) {
            for (int i = 0; i < n; ++i) {
                if ((m & (1 << i)) == 0) {
                    maxLength(s, dp, m | (1 << i));
                }
            }
        }
        int ans = 0;
        for (int m = 1; m < M; ++m) {
            ans = Math.max(ans, dp[m] * dp[M - m - 1]);
        }
        return ans;
    }

    private void maxLength(String s, int[] dp, int m) {
        int n = s.length();
        dp[m] = Math.max(dp[m], 1);
        for (int i = 0; i < n; ++i) {
            if ((m & (1 << i)) != 0) {
                char c1 = s.charAt(i);
                for (int j = i + 1; j < n; ++j) {
                    if ((m & (1 << j)) != 0 && s.charAt(j) == c1) {
                        dp[m] = Math.max(dp[m], 2 + dp[m & ((1 << j) - (1 << (i + 1)))]);
                    }
                }
            }
        }
    }

    private boolean check(char[] s, int state) {
        int left = 0, right = s.length - 1;

        // 检查 state 对应的子序列是不是回文串
        while (left < right) {
            // 将 left 和 right 对应上 「状态所对应的字符」 位置
            while (left < right && (state >> left & 1) == 0) {
                left++;
            }
            while (left < right && (state >> right & 1) == 0) {
                right--;
            }
            if (s[left] != s[right]) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public int maxProduct(String s) {
        int n = s.length(), m = 1 << n;
        char[] str = s.toCharArray();
        int[] count = new int[m];

        // 记录所有合法状态的字符串长度
        for (int i = 1; i < m; i++) {
            if (check(str, i)) {
                count[i] = Integer.bitCount(i);
            }
        }

        int res = 0;
        // 对 s 的每个子序列进行子集枚举
        for (int i = 1; i < m; i++) {
            int split = i >> 1;
            // 由于 j 与 i ^ j 是互补的关系，即 j ^ i ^ j = i
            // 因此只需枚举到 i / 2 就可以了
            for (int j = (i - 1) & i; j > split; j = (j - 1) & i) {
                // 假设 i 代表字符串 "etcdec"，j 为 i 的子集
                // 且 j = "ete" and i ^ j = "cdc" 时，记录答案
                res = Math.max(res, count[j] * count[i ^ j]);
            }
        }

        return res;
    }
}
