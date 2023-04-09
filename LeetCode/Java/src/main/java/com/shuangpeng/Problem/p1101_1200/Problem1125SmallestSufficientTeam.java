package com.shuangpeng.Problem.p1101_1200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 最小的必要团队
 * @date 2023/4/9 8:33 PM
 **/
public class Problem1125SmallestSufficientTeam {

    public int[] smallestSufficientTeam0(String[] req_skills, List<List<String>> people) {
        int n = req_skills.length;
        int size = people.size();
        int[] masks = new int[size];
        for (int i = 0; i < size; ++i) {
            List<String> list = people.get(i);
            int mask = 0;
            for (String str : list) {
                for (int j = 0; j < n; ++j) {
                    if (str.equals(req_skills[j])) {
                        mask |= 1 << j;
                        break;
                    }
                }
            }
            masks[i] = mask;
        }
        int M = 1 << n;
        int[] dp = new int[M];
        int[] pre = new int[M];
        int[] last = new int[M];
        final int INF = Integer.MAX_VALUE;
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int m = 0; m < M; ++m) {
            if (dp[m] == INF) {
                continue;
            }
            for (int i = 0; i < size; ++i) {
                if (m != masks[i]) {
                    int j = m | masks[i];
                    if (dp[j] > dp[m] + 1) {
                        dp[j] = dp[m] + 1;
                        pre[j] = m;
                        last[j] = i;
                    }
                }
            }
        }
        int count = dp[M - 1];
        int[] ans = new int[count];
        int s = M - 1;
        for (int i = 0; i < count; ++i) {
            ans[i] = last[s];
            s = pre[s];
        }
        return ans;
    }

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n = req_skills.length, m = people.size(), N = 1 << n;
        int[] hash = new int[m];
        for (int i = 0; i < m; i++) {
            List<String> list = people.get(i);
            int h = 0;
            for (String s : list) {
                for (int j = 0; j < n; j++) {
                    if (req_skills[j].equals(s)) {
                        h |= 1 << j;
                        break;
                    }
                }
            }
            hash[i] = h;
        }
        int[][] dp = new int[N][2];
        for (int i = 1; i < N; i++) {
            dp[i][0] = m + 1;
            for (int j = 0; j < m; j++) {
                int h = hash[j], k = i & (i ^ h);
                if (dp[i][0] > dp[k][0] + 1) {
                    dp[i][0] = dp[k][0] + 1;
                    dp[i][1] = j;
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        int index = N - 1;
        while (index != 0) {
            ans.add(dp[index][1]);
            index = index & (index ^ hash[dp[index][1]]);
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
