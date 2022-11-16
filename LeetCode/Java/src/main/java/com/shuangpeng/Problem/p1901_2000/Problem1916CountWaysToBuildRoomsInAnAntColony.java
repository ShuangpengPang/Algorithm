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
    private static final int[] c = new int[N];
    static {
        c[0] = 1;
        for (int i = 1; i < N; i++) {
            c[i] = (int) ((long) c[i - 1] * i % M);
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
        int count = 0, p = 1, a = 1;
        for (int y : g[x]) {
            p = (int) ((long) p * dfs(g, cnt, y) % M);
            count += cnt[y];
            a = (int) ((long) a * c[cnt[y]] % M);
        }
        cnt[x] = count + 1;
        return (int) ((long) p * c[count] % M * inversePower(a, M - 2) % M);
    }

    private int inversePower(int a, int p) {
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
