package com.shuangpeng.competition.第081场双周赛;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Problem2318NumberOfDistinctRollSequences（不同骰子序列的数目）
 * @Date 2022/7/4 3:44 PM
 * @Version 1.0
 */
public class Problem2318NumberOfDistinctRollSequences {

    static List<Integer>[] lists = new List[7];
    static {
        for (int i = 1; i <= 6; i++) {
            lists[i] = new ArrayList<>();
            for (int j = 1; j <= 6; j++) {
                if (i != j && gcd(i, j) == 1) {
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
