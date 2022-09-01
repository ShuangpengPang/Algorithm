package com.shuangpeng.competition.第290到300场周赛.第297场周赛;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description: Problem2306NamingACompany（公司命名）
 * @Date 2022/6/29 1:52 PM
 * @Version 1.0
 */
public class Problem2306NamingACompany {

    public long distinctNames0(String[] ideas) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : ideas) {
            String str = s.substring(1);
            map.put(str, map.getOrDefault(str, 0) | 1 << (s.charAt(0) - 'a'));
        }
        int N = 26;
        int[][] cnt = new int[N][N];
        long ans = 0;
        for (int h : map.values()) {
            for (int i = 0; i < N; i++) {
                if ((h & (1 << i)) == 0) {
                    for (int j = 0; j < N; j++) {
                        if ((h & (1 << j)) != 0) {
                            cnt[i][j]++;
                        }
                    }
                } else {
                    for (int j = 0; j < N; j++) {
                        if ((h & (1 << j)) == 0) {
                            ans += cnt[i][j];
                        }
                    }
                }
            }
        }
        return ans << 1;
    }

    public long distinctNames1(String[] ideas) {
        int N = 26;
        Set<String>[] sets = new Set[N];
        for (int i = 0; i < N; i++) {
            sets[i] = new HashSet<>();
        }
        for (String s : ideas) {
            sets[s.charAt(0) - 'a'].add(s.substring(1));
        }
        long ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int m = 0;
                for (String s : sets[i]) {
                    if (sets[j].contains(s)) {
                        m++;
                    }
                }
                ans += (long) (sets[i].size() - m) * (sets[j].size() - m);
            }
        }
        return ans << 1;
    }

    public long distinctNames(String[] ideas) {
        int N = 26;
        int[][] cnt = new int[N][N];
        int[] size = new int[N];
        Map<String, Integer> map = new HashMap<>();
        for (String s : ideas) {
            int i = s.charAt(0) - 'a';
            size[i]++;
            String str = s.substring(1);
            int mask = map.getOrDefault(str, 0);
            if (mask > 0) {
                for (int j = 0; j < N; j++) {
                    if ((mask >> j & 1) != 0) {
                        cnt[i][j]++;
                        cnt[j][i]++;
                    }
                }
            }
            map.put(str, mask | 1 << i);
        }
        long ans = 0;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                ans += (long) (size[i] - cnt[i][j]) * (size[j] - cnt[j][i]);
            }
        }
        return ans << 1;
    }
}
