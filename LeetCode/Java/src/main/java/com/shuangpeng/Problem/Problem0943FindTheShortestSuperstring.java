package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem0943FindTheShortestSuperstring {

    public String shortestSuperstring0(String[] words) {
        int n = words.length;
        int M = 1 << n;
        int[][] dp = new int[M][n];
        int[][] parent = new int[M][n];
        int[][] overlap = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int length = Math.min(words[i].length(), words[j].length());
                for (int k = length - 1; k > 0; --k) {
                    if (words[i].endsWith(words[j].substring(0, k))) {
                        overlap[i][j] = k;
                        break;
                    }
                }
            }
        }
        for (int mask = 0; mask < M; ++mask) {
            Arrays.fill(parent[mask], -1);
            for (int i = 0; i < n; ++i) {
                if (((mask >> i) & 1) == 1) {
                    int pMask = mask ^ (1 << i);
                    if (pMask == 0) {
                        continue;
                    }
                    for (int j = 0; j < n; ++j) {
                        if (((pMask >> j) & 1) == 1) {
                            int val = dp[pMask][j] + overlap[j][i];
                            if (val > dp[mask][i]) {
                                dp[mask][i] = val;
                                parent[mask][i] = j;
                            }
                        }
                    }
                }
            }
        }
        int m = 0;
        for (int i = 0; i < n; ++i) {
            if (dp[M - 1][i] > dp[M - 1][m]) {
                m = i;
            }
        }
        int t = 0;
        int mask = M - 1;
        int[] permutation = new int[n];
        boolean[] seen = new boolean[n];
        while (m != -1) {
            permutation[t++] = m;
            seen[m] = true;
            int tm = m;
            m = parent[mask][m];
            mask ^= 1 << tm;
        }
        for (int i = 0; i < (t >> 1); ++i) {
            int temp = permutation[i];
            permutation[i] = permutation[t - i - 1];
            permutation[t - i - 1] = temp;
        }
        for (int i = 0; i < n; ++i) {
            if (!seen[i]) {
                permutation[t++] = i;
            }
        }
        StringBuilder sb = new StringBuilder(words[permutation[0]]);
        for (int i = 1; i < n; ++i) {
            int j = permutation[i];
            int k = permutation[i - 1];
            sb.append(words[j].substring(overlap[k][j]));
        }
        return sb.toString();
    }

    public String shortestSuperstring(String[] A) {
        int N = A.length;

        // Populate overlaps
        int[][] overlaps = new int[N][N];
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j) if (i != j) {
                int m = Math.min(A[i].length(), A[j].length());
                for (int k = m; k >= 0; --k)
                    if (A[i].endsWith(A[j].substring(0, k))) {
                        overlaps[i][j] = k;
                        break;
                    }
            }

        // dp[mask][i] = most overlap with mask, ending with ith element
        int[][] dp = new int[1<<N][N];
        int[][] parent = new int[1<<N][N];
        for (int mask = 0; mask < (1<<N); ++mask) {
            Arrays.fill(parent[mask], -1);

            for (int bit = 0; bit < N; ++bit) if (((mask >> bit) & 1) > 0) {
                // Let's try to find dp[mask][bit].  Previously, we had
                // a collection of items represented by pmask.
                int pmask = mask ^ (1 << bit);
                if (pmask == 0) continue;
                for (int i = 0; i < N; ++i) if (((pmask >> i) & 1) > 0) {
                    // For each bit i in pmask, calculate the value
                    // if we ended with word i, then added word 'bit'.
                    int val = dp[pmask][i] + overlaps[i][bit];
                    if (val > dp[mask][bit]) {
                        dp[mask][bit] = val;
                        parent[mask][bit] = i;
                    }
                }
            }
        }

        // # Answer will have length sum(len(A[i]) for i) - max(dp[-1])
        // Reconstruct answer, first as a sequence 'perm' representing
        // the indices of each word from left to right.

        int[] perm = new int[N];
        boolean[] seen = new boolean[N];
        int t = 0;
        int mask = (1 << N) - 1;

        // p: the last element of perm (last word written left to right)
        int p = 0;
        for (int j = 0; j < N; ++j)
            if (dp[(1<<N) - 1][j] > dp[(1<<N) - 1][p])
                p = j;

        // Follow parents down backwards path that retains maximum overlap
        while (p != -1) {
            perm[t++] = p;
            seen[p] = true;
            int p2 = parent[mask][p];
            mask ^= 1 << p;
            p = p2;
        }

        // Reverse perm
        for (int i = 0; i < t/2; ++i) {
            int v = perm[i];
            perm[i] = perm[t-1-i];
            perm[t-1-i] = v;
        }

        // Fill in remaining words not yet added
        for (int i = 0; i < N; ++i) if (!seen[i])
            perm[t++] = i;

        // Reconstruct final answer given perm
        StringBuilder ans = new StringBuilder(A[perm[0]]);
        for (int i = 1; i < N; ++i) {
            int overlap = overlaps[perm[i-1]][perm[i]];
            ans.append(A[perm[i]].substring(overlap));
        }

        return ans.toString();
    }
}
