package com.shuangpeng.Problem.p1101_1200;

import java.util.Arrays;
import java.util.List;

public class Problem1125SmallestSufficientTeam {

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
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
}
