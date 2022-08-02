package com.shuangpeng.Problem.p1301_1400;

import java.util.Arrays;

/**
 * @Description: Problem1320MinimumDistanceToTypeAWordUsingTwoFingers（二指输入的最小距离）
 * @Date 2022/8/1 4:57 PM
 * @Version 1.0
 */
public class Problem1320MinimumDistanceToTypeAWordUsingTwoFingers {

    static final int N = 26, INF = Integer.MAX_VALUE >> 1;
    static int[][] dis = new int[N][N];
    static {
        for (int i = 0; i < N; i++) {
            int x = i / 6, y = i % 6;
            for (int j = i + 1; j < N; j++) {
                int d = Math.abs(x - j / 6) + Math.abs(y - j % 6);
                dis[i][j] = d;
                dis[j][i] = d;
            }
        }
    }

    public int minimumDistance(String word) {
        char[] chars = word.toCharArray();
        int n = chars.length;
        int[][][] dp = new int[n][N][N];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }
        int f = chars[0] - 'A';
        for (int i = 0; i < N; i++) {
            dp[0][f][i] = 0;
            dp[0][i][f] = 0;
        }
        for (int i = 1; i < n; i++) {
            int c1 = chars[i - 1] - 'A', c2 = chars[i] - 'A';
            for (int j = 0; j < N; j++) {
                if (j == c2) {
                    continue;
                }
                if (j != c2) {
                    int d = dis[c1][c2];
                    dp[i][c2][j] = Math.min(dp[i][c2][j], dp[i - 1][c1][j] + d);
                    dp[i][j][c2] = Math.min(dp[i][j][c2], dp[i - 1][j][c1] + d);
                }
                if (j == c1) {
                    for (int k = 0; k < N; k++) {
                        int d = dis[k][c2];
                        dp[i][c2][j] = Math.min(dp[i][c2][j], dp[i - 1][k][j] + d);
                        dp[i][j][c2] = Math.min(dp[i][j][c2], dp[i - 1][j][k] + d);
                    }
                }
            }
        }
        int c = chars[n - 1] - 'A';
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            ans = Math.min(ans, dp[n - 1][c][i]);
            ans = Math.min(ans, dp[n - 1][i][c]);
        }
        return ans;
    }
}

class Problem1320MinimumDistanceToTypeAWordUsingTwoFingers0 {

    static final int N = 26, INF = Integer.MAX_VALUE >> 1;
    static int[][] dis = new int[N][N];
    static {
        for (int i = 0; i < N; i++) {
            int x = i / 6, y = i % 6;
            for (int j = i + 1; j < N; j++) {
                int d = Math.abs(x - j / 6) + Math.abs(y - j % 6);
                dis[i][j] = d;
                dis[j][i] = d;
            }
        }
    }

    public int minimumDistance(String word) {
        char[] chars = word.toCharArray();
        int n = chars.length;
        int[][][] dp = new int[2][N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[0][i], INF);
        }
        int f = chars[0] - 'A';
        for (int i = 0; i < N; i++) {
            dp[0][f][i] = 0;
            dp[0][i][f] = 0;
        }
        for (int i = 1; i < n; i++) {
            int cur = i & 1, pre = cur ^ 1;
            for (int j = 0; j < N; j++) {
                Arrays.fill(dp[cur][j], INF);
            }
            int c1 = chars[i - 1] - 'A', c2 = chars[i] - 'A';
            for (int j = 0; j < N; j++) {
                if (j == c2) {
                    continue;
                }
                dp[cur][c2][j] = Math.min(dp[cur][c2][j], dp[pre][c1][j] + dis[c1][c2]);
                dp[cur][j][c2] = Math.min(dp[cur][j][c2], dp[pre][j][c1] + dis[c1][c2]);
                if (j == c1) {
                    for (int k = 0; k < N; k++) {
                        dp[cur][c2][j] = Math.min(dp[cur][c2][j], dp[pre][k][j] + dis[k][c2]);
                        dp[cur][j][c2] = Math.min(dp[cur][j][c2], dp[pre][j][k] + dis[k][c2]);
                    }
                }
            }
        }
        int idx = (n - 1) & 1;
        int c = chars[n - 1] - 'A';
        int ans = INF;
        for (int i = 0; i < N; i++) {
            ans = Math.min(ans, dp[idx][c][i]);
            ans = Math.min(ans, dp[idx][i][c]);
        }
        return ans;
    }
}

class Problem1320MinimumDistanceToTypeAWordUsingTwoFingers1 {

    static final int N = 26, INF = Integer.MAX_VALUE >> 1;
    static int[][] dis = new int[N][N];
    static {
        for (int i = 0; i < N; i++) {
            int x = i / 6, y = i % 6;
            for (int j = i + 1; j < N; j++) {
                int d = Math.abs(x - j / 6) + Math.abs(y - j % 6);
                dis[i][j] = d;
                dis[j][i] = d;
            }
        }
    }

    public int minimumDistance(String word) {
        char[] chars = word.toCharArray();
        int n = chars.length;
        int[][] dp = new int[2][N];
        for (int i = 1; i < n; i++) {
            int cur = i & 1, pre = cur ^ 1;
            for (int j = 0; j < N; j++) {
                Arrays.fill(dp[cur], INF);
            }
            int c1 = chars[i - 1] - 'A', c2 = chars[i] - 'A';
            for (int j = 0; j < N; j++) {
                if (j == c2) {
                    continue;
                }
                dp[cur][j] = Math.min(dp[cur][j], dp[pre][j] + dis[c1][c2]);
                if (j == c1) {
                    for (int k = 0; k < N; k++) {
                        dp[cur][j] = Math.min(dp[cur][j], dp[pre][k] + dis[k][c2]);
                    }
                }
            }
        }
        int idx = (n - 1) & 1;
        int ans = INF;
        for (int i = 0; i < N; i++) {
            ans = Math.min(ans, dp[idx][i]);
        }
        return ans;
    }
}
