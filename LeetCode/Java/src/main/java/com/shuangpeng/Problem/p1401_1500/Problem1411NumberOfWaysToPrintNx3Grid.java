package com.shuangpeng.Problem.p1401_1500;

import java.util.*;

/**
 * @Description: Problem1411NumberOfWaysToPrintNx3Grid（给Nx3网格图涂色的方案数）
 * @Date 2022/8/12 3:23 PM
 * @Version 1.0
 */
public class Problem1411NumberOfWaysToPrintNx3Grid {

    static List<Integer> states = new ArrayList<>();
    static List<List<Integer>> map = new ArrayList<>();

    static {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    continue;
                }
                for (int k = 0; k < 3; k++) {
                    if (k == j) {
                        continue;
                    }
                    states.add(i | j << 10 | k << 20);
                }
            }
        }
        int mod = (1 << 10) - 1;
        int n = states.size();
        for (int i = 0; i < n; i++) {
            int state1 = states.get(i);
            int a = state1 & mod, b = state1 >> 10 & mod, c = state1 >> 20 & mod;
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int state2 = states.get(j);
                int x = state2 & mod, y = state2 >> 10 & mod, z = state2 >> 20 & mod;
                if (a == x || b == y || c == z) {
                    continue;
                }
                list.add(j);
            }
            map.add(list);
        }
    }

    public int numOfWays0(int n) {
        int m = states.size(), M = (int) 1e9 + 7;
        int[] dp = new int[m];
        Arrays.fill(dp, 1);
        for (int i = 2; i <= n; i++) {
            int[] tmp = new int[m];
            for (int j = 0; j < m; j++) {
                for (int k : map.get(j)) {
                    tmp[j] = (tmp[j] + dp[k]) % M;
                }
            }
            dp = tmp;
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            ans = (ans + dp[i]) % M;
        }
        return ans;
    }

    public int numOfWays(int n) {
        long a = 6, b = 6, M = (long) 1e9 + 7;
        for (int i = 2; i <= n; i++) {
            long c = a;
            a = 2 * (a + b) % M;
            b = (2 * c + 3 * b) % M;
        }
        return (int) ((a + b) % M);
    }
}