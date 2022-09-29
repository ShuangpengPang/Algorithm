package com.shuangpeng.Problem.p1701_1800;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Problem1735CountWaysToMakeArrayWithProduct（生成乘积数组的方案数）
 * @Date 2022/9/28 6:22 PM
 * @Version 1.0
 */
public class Problem1735CountWaysToMakeArrayWithProduct {

    static int N = (int) 1e4 + 1, M = (int) 1e9 + 7, K = 14;
    static int[][] c = new int[N][K];
    static List<Integer>[] lists = new List[N];
    static {
        c[0][0] = 1;
        for (int i = 1; i < N; i++) {
            c[i][0] = 1;
            for (int j = 1; j < K; j++) {
                c[i][j] = (c[i - 1][j] + c[i][j - 1]) % M;
            }
        }
        lists[1] = new ArrayList<>();
        for (int i = 2; i < N; i++) {
            if (lists[i] == null) {
                for (int j = i; j < N; j += i) {
                    if (lists[j] == null) {
                        lists[j] = new ArrayList<>();
                    }
                    int cnt = 0;
                    int k = j;
                    while (k % i == 0) {
                        cnt++;
                        k /= i;
                    }
                    lists[j].add(cnt);
                }
            }
        }
    }

    public int[] waysToFillArray(int[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int m = queries[i][0], num = queries[i][1];
            int res = 1;
            for (int k : lists[num]) {
                res = (int) ((long) res * c[m][k] % M);
            }
            ans[i] = res;
        }
        return ans;
    }
}


