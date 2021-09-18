package com.shuangpeng.Problem;

public class Problem0940DistinctSubsequencesII {

    public int distinctSubseqII0(String s) {
        final int N = 26;
        final int M = (int) 1e9 + 7;
        int n = s.length();
        int[] dp = new int[N];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int idx = s.charAt(i) - 'a';
            int temp = dp[idx];
            dp[idx] = ans + 1;
            if (dp[idx] >= M) {
                dp[idx] -= M;
            }
            ans += dp[idx] - temp;
            if (ans >= M) {
                ans -= M;
            } else if (ans < 0) {
                ans += M;
            }
        }
        return ans;
    }

    public int distinctSubseqII1(String s) {
        final int N = 26;
        final int M = (int) 1e9 + 7;
        int n = s.length();
        int[] dp = new int[n + 1];
        int[] last = new int[N];
        for (int i = 1; i <= n; ++i) {
            int j = s.charAt(i - 1) - 'a';
            if (last[j] == 0) {
                dp[i] = (dp[i - 1] << 1) + 1;
            } else {
                dp[i] = (dp[i - 1] << 1) - dp[last[j] - 1];
            }
            if (dp[i] >= M) {
                dp[i] -= M;
            } else if (dp[i] < 0) {
                dp[i] += M;
            }
            last[j] = i;
        }
        return dp[n];
    }

    public int distinctSubseqII(String s) {
        int n = s.length();
        final int N = 26;
        final int M = (int) 1e9 + 7;
        int[] last = new int[N];
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; ++i) {
            int j = s.charAt(i - 1) - 'a';
            if (last[j] > 0) {
                dp[i] = (dp[i - 1] << 1) - dp[last[j] - 1];
            } else {
                dp[i] = dp[i - 1] << 1;
            }
            if (dp[i] >= M) {
                dp[i] -= M;
            } else if (dp[i] < 0) {
                dp[i] += M;
            }
            last[j] = i;
        }
        return dp[n] - 1;
    }

    public int maxProduct(String s) {
        int n = s.length();
        int M = 1 << n;
        char[] chars = s.toCharArray();
        int[] dp = new int[M];
        for (int i = 1; i < M; ++i) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; ++j) {
                if ((i & (1 << j)) != 0) {
                    sb.append(chars[j]);
                }
            }
            dp[i] = manacher(sb);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int j = M - i - 1;
            ans = Math.max(ans, dp[i] * dp[j]);
        }
        return ans;
    }

    private int manacher(StringBuilder s) {
        int n = s.length();
        int maxLength = 0;
        for (int i = 0; i < n; ++i) {
            int j = 0;
            while (i - j >= 0 && i + j < n && s.charAt(i - j) == s.charAt(i + j)) {
                j++;
            }
            j--;
            maxLength = Math.max(maxLength, (j << 1) + 1);
        }
        for (int i = 0; i < n - 1; ++i) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                int j = 0;
                while (i - j >= 0 && i + j + 1 < n && s.charAt(i - j) == s.charAt(i + j + 1)) {
                    j++;
                }
                j--;
                maxLength = Math.max(maxLength, (j + 1) << 1);
            }
        }
        return maxLength;
    }

//    public static void main(String[] args) {
//        Problem0940DistinctSubsequencesII a = new Problem0940DistinctSubsequencesII();
//        a.distinctSubseqII("aba");
//    }
}
