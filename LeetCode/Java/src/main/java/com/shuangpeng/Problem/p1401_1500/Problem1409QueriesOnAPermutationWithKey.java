package com.shuangpeng.Problem.p1401_1500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1409QueriesOnAPermutationWithKey（查询带键的排列）
 * @date 2023/8/22 3:57 PM
 */
public class Problem1409QueriesOnAPermutationWithKey {

    public int[] processQueries(int[] queries, int m) {
        int n = queries.length, N = n + m;
        int[] tree = new int[N + 1], map = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            int x = i + n;
            map[i] = x;
            update(tree, x, 1);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int num = queries[i], x = map[num];
            update(tree, x, -1);
            ans[i] = query(tree, x);
            map[num] = n - i;
            update(tree, n - i, 1);
        }
        return ans;
    }

    private void update(int[] tree, int x, int value) {
        int n = tree.length - 1;
        while (x <= n) {
            tree[x] += value;
            x += x & -x;
        }
    }

    private int query(int[] tree, int x) {
        int cnt = 0;
        while (x != 0) {
            cnt += tree[x];
            x ^= x & -x;
        }
        return cnt;
    }
}
