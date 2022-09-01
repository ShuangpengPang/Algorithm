package com.shuangpeng.competition.第301到310场周赛.第301场周赛;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Problem2338CountTheNumberOfIdealArrays（统计理想数组的数目）
 * @Date 2022/7/20 6:09 PM
 * @Version 1.0
 */
public class Problem2338CountTheNumberOfIdealArrays {

    static final int M = (int) 1e9 + 7, N = (int) 1e4, K = 13;
    static int[][] c = new int[N + K][K + 1];
    static List<Integer>[] lists = new List[N + 1];

    static {
        for (int i = 1; i <= N; i++) {
            lists[i] = new ArrayList<Integer>();
            int x = i;
            for (int p = 2; p * p <= x; p++) {
                if (x % p == 0) {
                    int k = 0;
                    while (x % p == 0) {
                        x /= p;
                        k++;
                    }
                    lists[i].add(k);
                }
            }
            if (x > 1) {
                lists[i].add(1);
            }
        }

        c[0][0] = 1;
        for (int i = 1; i < N + K; i++) {
            c[i][0] = 1;
            for (int j = 1; j <= Math.min(i, K); j++) {
                c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % M;
            }
        }
    }

    public int idealArrays(int n, int maxValue) {
        int ans = 0;
        for (int i = 1; i <= maxValue; i++) {
            long count = 1L;
            for (int j : lists[i]) {
                count = (count * c[n + j - 1][j]) % M;
            }
            ans = (int) ((ans + count) % M);
        }
        return ans;
    }
}

