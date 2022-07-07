package com.shuangpeng.competition.第081场双周赛;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: Problem2318NumberOfDistinctRollSequences（不同骰子序列的数目）
 * @Date 2022/7/4 3:44 PM
 * @Version 1.0
 */
public class Problem2318NumberOfDistinctRollSequences {

    // 比赛时写法
    public int distinctSequences(int n) {
        if (n == 1) {
            return 6;
        }
        int M = (int) 1e9 + 7;
        int[][][] dp = new int[n + 1][7][7];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= 6; j++) {
                for (int k = 1; k <= 6; k++) {
                    if (j == k || gcd(j, k) != 1) {
                        continue;
                    }
                    if (i == 2) {
                        dp[i][j][k] = 1;
                    } else {
                        for (int s = 1; s <= 6; s++) {
                            if (s != k) {
                                dp[i][j][k] = (dp[i][j][k] + dp[i - 1][s][j]) % M;
                            }
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                ans = (ans + dp[n][i][j]) % M;
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}

class Problem2318NumberOfDistinctRollSequences0 {

    static List<Integer>[] lists = new List[7];
    static {
        for (int i = 1; i <= 6; i++) {
            lists[i] = new ArrayList<>();
            for (int j = i + 1; j <= 6; j++) {
                if (gcd(i, j) == 1) {
                    lists[i].add(j);
                }
            }
        }
    }

    static int M = (int) 1e9 + 7;

    public int distinctSequences(int n) {
        if (n == 1) {
            return 6;
        }
        int[][][] dp = new int[2][7][7];
        for (int i = 1; i <= 6; i++) {
            for (int j : lists[i]) {
                dp[0][i][j] = 1;
            }
        }
        for (int i = 3; i <= n; i++) {
            int idx = i % 2, p = 1 - idx;
            for (int x = 1; x <= 6; x++) {
                for (int y : lists[x]) {
                    dp[idx][x][y] = 0;
                    for (int j : lists[x]) {
                        if (j != y) {
                            dp[idx][x][y] = (dp[idx][x][y] + dp[p][j][x]) % M;
                        }
                    }
                }
            }
        }
        int index = n % 2;
        int ans = 0;
        for (int i = 1; i <= 6; i++) {
            for (int j : lists[i]) {
                ans = (ans + dp[index][i][j]) % M;
            }
        }
        return ans;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}

class Problem2318NumberOfDistinctRollSequences1 {

    static int M = (int) 1e9 + 7, N = 10000;
    static int[][] dp = new int[N + 1][6];
    static List<Integer>[] lists = new List[6];

    static {
        Arrays.setAll(lists, e -> new ArrayList<>());
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                if (gcd(i + 1, j + 1) == 1) {
                    lists[i].add(j);
                    lists[j].add(i);
                }
            }
        }
        for (int i = 0; i < 6; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 6; j++) {
                long s = 0;
                for (int k : lists[j]) {
                    s += dp[i - 1][k] - dp[i - 2][j];
                }
                if (i > 3) {
                    s += dp[i - 2][j];
                }
                dp[i][j] = (int) ((s % M + M) % M);
            }
        }
    }

    public int distinctSequences(int n) {
        int ans = 0;
        for (int i = 0; i < 6; i++) {
            ans = (ans + dp[n][i]) % M;
        }
        return ans;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}

class Problem2318NumberOfDistinctRollSequences2 {

    static final int M = (int) 1e9 + 7;
    static final int N = (int) 1e4 + 1;
    static int[][][] dp = new int[N][8][8];
    static List<Integer>[] lists = new List[8];

    static {
        Arrays.setAll(lists, e -> new ArrayList<>());
        for (int i = 1; i <= 7; i++) {
            for (int j = i + 1; j <= 7; j++) {
                if (gcd(i, j) == 1) {
                    lists[i].add(j);
                    lists[j].add(i);
                }
            }
        }
    }

    public int distinctSequences(int n) {
        return dfs(n, 7, 7);
    }

    private int dfs(int n, int i, int j) {
        if (dp[n][i][j] != 0) {
            return dp[n][i][j];
        }
        if (n == 0) {
            dp[n][i][j] = 1;
            return 1;
        }
        long s = 0;
        for (int k : lists[i]) {
            if (j != k && k < 7) {
                s += dfs(n - 1, k, i);
            }
        }
        dp[n][i][j] = (int) (s % M);
        return dp[n][i][j];
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
