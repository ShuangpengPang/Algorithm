package com.shuangpeng.Problem.p1101_1200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public int[] smallestSufficientTeam1(String[] req_skills, List<List<String>> people) {
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

    public int[] smallestSufficientTeam2(String[] req_skills, List<List<String>> people) {
        int n = req_skills.length, N = 1 << n, m = people.size();
        Map<String, Integer> map = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            map.put(req_skills[i], i);
        }
        List<Integer>[][] dp = new List[2][N];
        Arrays.setAll(dp, i -> new List[N]);
        dp[0][0] = dp[1][0] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer>[] cur = dp[i & 1], pre = dp[(i & 1) ^ 1];
            int h = 0;
            for (String s : people.get(i)) {
                h |= 1 << map.get(s);
            }
            for (int j = 0; j < N; j++) {
                if (pre[j] == null) {
                    continue;
                }
                int k = j | h;
                if (cur[k] == null || cur[k].size() > pre[j].size() + 1) {
                    if (cur[k] == null) {
                        cur[k] = new ArrayList<>();
                    }
                    cur[k].clear();
                    cur[k].addAll(pre[j]);
                    cur[k].add(i);
                }
            }
            for (int j = 0; j < N; j++) {
                if (cur[j] == null) {
                    continue;
                }
                if (pre[j] == null) {
                    pre[j] = new ArrayList<>();
                }
                pre[j].clear();
                pre[j].addAll(cur[j]);
            }
        }
        return dp[(m - 1) & 1][N - 1].stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] smallestSufficientTeam3(String[] req_skills, List<List<String>> people) {
        int n = req_skills.length, N = 1 << n;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(req_skills[i], i);
        }
        List<Integer>[] dp = new List[N];
        dp[0] = new ArrayList<>();
        for (int i = 0; i < people.size(); i++) {
            int h = 0;
            for (String s : people.get(i)) {
                h |= 1 << map.get(s);
            }
            for (int j = 0; j < N; j++) {
                if (dp[j] != null) {
                    int k = j | h;
                    if (dp[k] == null || dp[k].size() > dp[j].size() + 1) {
                        if (dp[k] == null) {
                            dp[k] = new ArrayList<>();
                        }
                        dp[k].clear();
                        dp[k].addAll(dp[j]);
                        dp[k].add(i);
                    }
                }
            }
        }
        return dp[N - 1].stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n = req_skills.length, m = people.size(), N = 1 << n;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(req_skills[i], i);
        }
        Set<Integer>[] dp = new Set[N];
        Arrays.setAll(dp, i -> new HashSet<>());
        for (int i = 0; i < m; i++) {
            List<String> skills = people.get(i);
            int cur = 0;
            for (String s : skills) {
                cur |= 1 << map.get(s);
            }
            for (int j = 0; j < N; j++) {
                int k = cur | j;
                if (dp[k].isEmpty() || dp[k].size() > dp[j].size() + 1) {
                    dp[k].clear();
                    dp[k].addAll(dp[j]);
                    dp[k].add(i);
                }
            }
        }
        return dp[N - 1].stream().mapToInt(Integer::intValue).toArray();
    }
}
