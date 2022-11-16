package com.shuangpeng.Problem.p1901_2000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1916CountWaysToBuildRoomsInAnAntColony（统计为蚁群构筑房间的不同顺序）
 * @date 2022/11/16 11:53 AM
 */
public class Problem1916CountWaysToBuildRoomsInAnAntColony {

    private static final int N = (int) 1e5, M = (int) 1e9 + 7;
    private static final int[] fac = new int[N], inv = new int[N];
    static {
        fac[0] = 1;
        inv[0] = 1;
        for (int i = 1; i < N; i++) {
            fac[i] = (int) ((long) fac[i - 1] * i % M);
            inv[i] = inversePower(fac[i], M - 2);
        }
    }

    public int waysToBuildRooms(int[] prevRoom) {
        int n = prevRoom.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            g[prevRoom[i]].add(i);
        }
        return dfs(g, new int[n], 0);
    }

    private int dfs(List<Integer>[] g, int[] cnt, int x) {
        int ans = 1;
        cnt[x] = 0;
        for (int y : g[x]) {
            ans = (int) ((long) ans * dfs(g, cnt, y) % M * inv[cnt[y]] % M);
            cnt[x] += cnt[y];
        }
        ans = (int) ((long) ans * fac[cnt[x]] % M);
        cnt[x]++;
        return ans;
    }

    private static int inversePower(int a, int p) {
        int ans = 1;
        while (p > 0) {
            if ((p & 1) == 1) {
                ans = (int) ((long) ans * a % M);
            }
            p >>= 1;
            a = (int) ((long) a * a % M);
        }
        return ans;
    }
}