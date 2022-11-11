package com.shuangpeng.Problem.p2101_2200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2127MaximumEmployeesToBeInvitedToAMeeting（参加会议的最多员工数）
 * @date 2022/11/10 6:12 PM
 */
public class Problem2127MaximumEmployeesToBeInvitedToAMeeting {

    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 0; i < n; i++) {
            g[favorite[i]].add(i);
        }
        int[] cnt = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (cnt[i] == 0) {
                if (i == favorite[favorite[i]]) {
                    ans += dfs(g, i, favorite[i], cnt) + dfs(g, favorite[i], i, cnt);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (cnt[i] == 0 && g[i].isEmpty()) {
                ans = Math.max(ans, findCircle(favorite, g, i, cnt, 1));
            }
        }
        for (int i = 0; i < n; i++) {
            if (cnt[i] == 0) {
                int count = 0, j = i;
                while (cnt[j] == 0) {
                    cnt[j] = ++count;
                    j = favorite[j];
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }

    private int dfs(List<Integer>[] g, int x, int p, int[] cnt) {
        cnt[x] = 1;
        int ans = 0;
        for (int y : g[x]) {
            if (y != p) {
                ans = Math.max(ans, dfs(g, y, p, cnt));
            }
        }
        return ans + 1;
    }

    private int findCircle(int[] favorite, List<Integer>[] g, int x, int[] cnt, int idx) {
        int y = favorite[x];
        if (cnt[y] < 0) {
            return cnt[x] = idx + cnt[y] + 1;
        }
        if (cnt[y] > 0) {
            return cnt[x] = cnt[y];
        }
        cnt[x] = -idx;
        return cnt[x] = findCircle(favorite, g, y, cnt, idx + 1);
    }
}
