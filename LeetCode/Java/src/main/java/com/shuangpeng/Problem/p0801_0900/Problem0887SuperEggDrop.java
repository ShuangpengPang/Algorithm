package com.shuangpeng.Problem.p0801_0900;

import java.util.Arrays;

public class Problem0887SuperEggDrop {

    public int superEggDrop0(int k, int n) {
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 0; i <= k; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 1; i <= n; ++i) {
            dp[1][i] = i;
        }
        for (int i = 0; i <= k; ++i) {
            dp[i][0] = 0;
        }
        for (int i = 2; i <= k; ++i) {
            for (int j = 1; j <= n; ++j) {
//                for (int t = 1; t <= j; ++t) {
//                    dp[i][j] = Math.min(dp[i][j], 1 + Math.max(dp[i - 1][t - 1], dp[i][j - t]));
//                }
                int left = 1, right = j;
                while (left <= right) {
                    int mid = left + ((right - left) >> 1);
                    if (dp[i - 1][mid - 1] < dp[i][j - mid]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                dp[i][j] = 1 + Math.min(dp[i][j - left + 1], dp[i - 1][left - 1]);
            }
        }
        return dp[k][n];
    }

    public int superEggDrop1(int k, int n) {
        // Right now, dp[i] represents dp(1, i)
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; ++i) {
            dp[i] = i;
        }

        for (int j = 2; j <= k; ++j) {
            // Now, we will develop dp2[i] = dp(j, i)
            int[] dp2 = new int[n + 1];
            int x = 1;
            for (int m = 1; m <= n; ++m) {
                // Let's find dp2[m] = dp(j, m)
                // Increase our optimal x while we can make our answer better.
                // Notice max(dp[x-1], dp2[m-x]) > max(dp[x], dp2[m-x-1])
                // is simply max(T1(x-1), T2(x-1)) > max(T1(x), T2(x)).
                while (x < m && Math.max(dp[x - 1], dp2[m - x]) > Math.max(dp[x], dp2[m - x - 1])) {
                    x++;
                }

                // The final answer happens at this x.
                dp2[m] = 1 + Math.max(dp[x - 1], dp2[m - x]);
            }

            dp = dp2;
        }

        return dp[n];
    }

    public int superEggDrop2(int k, int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; ++i) {
            dp[i] = i;
        }
        for (int i = 2; i <= k; ++i) {
            int[] temp = new int[n + 1];
            Arrays.fill(temp, Integer.MAX_VALUE);
            temp[0] = 0;
            int x = 1;
            for (int j = 1; j <= n; ++j) {
//                for (int t = 1; t <= j; ++t) {
//                    dp[i][j] = Math.min(dp[i][j], 1 + Math.min(dp[i - 1][t - 1], dp[i][j - t]));
//                }
                while (dp[x - 1] < temp[j - x]) {
                    x++;
                }
                temp[j] = 1 + Math.min(dp[x - 1], temp[j - x + 1]);
            }
            dp = temp;
        }
        return dp[n];
    }

    public int superEggDrop3(int k, int n) {
        int[][] dp = new int[n + 1][k + 1];
        // dp[i][j] = 1 + dp[i - 1][j] + dp[i - 1][j - 1];
        // dp[1][x] = 1, dp[x][1] = x;
        for (int i = 1; i <= k; ++i) {
            dp[1][i] = 1;
        }
        for (int i = 0; i <= n; ++i) {
            dp[i][1] = i;
        }
        for (int i = 2; i <= n; ++i) {
            for (int j = 2; j <= k; ++j) {
                dp[i][j] = 1 + dp[i - 1][j] + dp[i - 1][j - 1];
            }
        }
        for (int i = 0; i < n; ++i) {
            if (dp[i][k] >= n) {
                return i;
            }
        }
        return n;
    }

    public int superEggDrop(int k, int n) {
        int[] dp = new int[k + 1];
        int ans = n;
        for (int i = 1; i <= n; ++i) {
            for (int j = k; j > 0; --j) {
                dp[j] += dp[j - 1] + 1;
            }
            if (dp[k] >= n) {
                ans = Math.min(ans, i);
                break;
            }
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem0887SuperEggDrop a = new Problem0887SuperEggDrop();
//        a.superEggDrop(2, 6);
//    }
}
