package com.shuangpeng.Problem.p0901_1000;

/**
 * @Description:（不同的子序列II）
 * @Date 2022/10/14 5:38 PM
 **/
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

    public int distinctSubseqII2(String s) {
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

    public int distinctSubseqII3(String s) {
        int n = s.length(), N = 26, M = (int) 1e9 + 7;
        int[] dp = new int[N];
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = 0; j < N; j++) {
                sum += dp[j];
            }
            dp[s.charAt(i) - 'a'] = (int) ((sum + 1) % M);
        }
        long ans = 0;
        for (int i = 0; i < N; i++) {
            ans += dp[i];
        }
        return (int) (ans % M);
    }

    public int distinctSubseqII(String s) {
        int n = s.length(), ans = 0, M = (int) 1e9 + 7;
        int[] dp = new int[26];
        for (int i = 0; i < n; i++) {
            int j = s.charAt(i) - 'a';
            int t = dp[j];
            dp[j] = (ans + 1) % M;
            ans = (((ans << 1) + 1) % M - t + M) % M;
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem0940DistinctSubsequencesII a = new Problem0940DistinctSubsequencesII();
//        a.distinctSubseqII("aba");
//    }
}
