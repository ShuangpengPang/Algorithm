package com.shuangpeng.Problem.p3301_3400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3331FindSubtreeSizesAfterChanges（修改后子树的大小）
 * @date 2025/2/25 6:08 PM
 */
public class Problem3331FindSubtreeSizesAfterChanges {

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            g[parent[i]].add(i);
        }
        int[] ans = new int[n];
        dfs(g, new int[26], s.toCharArray(), ans, 0);
        return ans;
    }

    private int[] dfs(List<Integer>[] g, int[] p, char[] cs, int[] ans, int root) {
        int ch = cs[root] - 'a';
        p[ch]++;
        int[] cnt = new int[26];
        cnt[ch] = 1;
        for (int c : g[root]) {
            int[] t = dfs(g, p, cs, ans, c);
            for (int i = 0; i < 26; i++) {
                cnt[i] += t[i];
            }
        }
        for (int i = 0; i < 26; i++) {
            if (i == ch) {
                continue;
            }
            if (p[i] == 0) {
                cnt[ch] += cnt[i];
                cnt[i] = 0;
            }
        }
        ans[root] = cnt[ch];
        p[ch]--;
        return cnt;
    }
}
